package com.stp.ventas.vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.SelectEvent;

import com.stp.ventas.dominio.FactCabecera;
import com.stp.ventas.dominio.FactCabeceraImpuesto;
import com.stp.ventas.dominio.FactCliente;
import com.stp.ventas.dominio.FactDetalle;
import com.stp.ventas.dominio.FactDetalleImpuesto;
import com.stp.ventas.dominio.FactEmpresa;
import com.stp.ventas.dominio.FactEstablecimiento;
import com.stp.ventas.dominio.FactFormaPagos;
import com.stp.ventas.dominio.FactFormaPagosFactura;
import com.stp.ventas.dominio.FactImpuestosProductos;
import com.stp.ventas.dominio.FactPuntosEmision;
import com.stp.ventas.dominio.PosProducto;
import com.stp.ventas.eao.FactCabeceraEAO;
import com.stp.ventas.ride.GenerarComprobantePDF;
import com.stp.ventas.servicios.ServicioMantenedorDetalleImpuesto;
import com.stp.ventas.servicios.ServicioMantenedorEstablecimiento;
import com.stp.ventas.servicios.ServicioMantenedorFactCabecera;
import com.stp.ventas.servicios.ServicioMantenedorFactCliente;
import com.stp.ventas.servicios.ServicioMantenedorFactDetalle;
import com.stp.ventas.servicios.ServicioMantenedorFactEmpresa;
import com.stp.ventas.servicios.ServicioMantenedorFactPuntoEmision;
import com.stp.ventas.servicios.ServicioMantenedorFormaPago;
import com.stp.ventas.servicios.ServicioMantenedorFormaPagoFactura;
import com.stp.ventas.servicios.ServicioMantenedorImpuestoFactura;
import com.stp.ventas.servicios.ServicioMantenimientoProducto;
import com.stp.ventas.util.Constantes;
import com.stp.ventas.util.GestorClaveAcceso;

import librerias.exceptions.ErrorServicioNegocio;
import librerias.exceptions.ErrorValidacionGeneral;
import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.oyentes.PostConstructListener;

@ManagedBean
@ViewScoped
public class BeanProcesoFacturacion
		extends BeanMantenedorGenerico<ServicioMantenedorFactCabecera, Long, FactCabecera, FactCabeceraEAO> {

	@EJB
	private ServicioMantenedorFactCabecera lServicioCabecera;

	

	@Inject
	private ServicioMantenedorFactEmpresa lServicioUsuarioEmpresa;

	@EJB
	private ServicioMantenedorEstablecimiento lServicioEstablecimiento;

	@EJB
	private ServicioMantenedorFactPuntoEmision lMantenedorEmision;

	@EJB
	private ServicioMantenedorFactCliente lServicioMantenedorCliente;

	@EJB
	private ServicioMantenimientoProducto lServicioProducto;

	@EJB
	private ServicioMantenedorFactDetalle lServicioDetalle;

	@EJB
	private ServicioMantenedorFormaPagoFactura lServicioFormaPagoFact;

	@EJB
	private ServicioMantenedorDetalleImpuesto lServicioDetalleImpuesto;

	@EJB
	private ServicioMantenedorImpuestoFactura lServicioImpuestoFact;

	@EJB
	private ServicioMantenedorFormaPagoFactura lServicioPagoFactura;

	@EJB
	private ServicioMantenedorFormaPago lServicioFormaPago;

	private List<FactFormaPagos> lFormasPago;

	

	private FactCliente lClienteSeleccionado;

	private List<FactEmpresa> lListaEmpresas;
	private List<FactEstablecimiento> lListaEstablecimiento;
	private List<FactPuntosEmision> lListaPuntosEmision;

	private FactEmpresa lEmpresaSeleccionada;
	private FactEstablecimiento lEstablecimientoSelecionado;
	private FactPuntosEmision lPuntoEmisionSelecionado;

	private Long idEmpresSel;
	private Long idSucSel;
	private Long idPtoSel;

	private boolean paso1;
	private boolean paso2;
	private boolean paso3;
	private boolean paso4;
	private boolean paso5;

	private String lIdentificacionCliente;

	private String lIndiceActivo;

	private boolean lPresentarMensaje;

	@Inject
	private BeanControlFactura lControlFactura;

	private FactDetalle lFactDetalleActual;

	private FactDetalle lFactDetalleSeleccion;

	private PosProducto lProducto;

	private List<PosProducto> lListaProductosEmpresa;

	private FactFormaPagosFactura lFormaPagoFactura;

	private Long lIdFormaPago;

	private SimpleDateFormat lFormatoFecha;

	public BeanProcesoFacturacion() {
		super(FactCabecera.class);
		paso1 = true;
		paso2 = false;
		paso3 = false;
		paso4 = false;
		paso5 = false;

		lFormatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		lFormaPagoFactura = new FactFormaPagosFactura();
		lFormaPagoFactura.setPlazo(0L);
		lClienteSeleccionado = new FactCliente();
		lListaProductosEmpresa = new ArrayList<>();
		lPresentarMensaje = true;
		addPostConstructuListener(new PostConstructListener() {

			@Override
			public void metodoPostConstruct() {
				lFormasPago = lServicioFormaPago.listaObjetosActivos(FactFormaPagos.class);
				lFormatoFecha = new SimpleDateFormat();
				if (lControlFactura.getlCabeceraFactura() == null) {
					metodoInicialNormal();
				} else {
					metodoRetomarEstadoFactura();
					cargarDetallesFactura();
				}
			}

		});
	}

	private void metodoRetomarEstadoFactura() {

		entidadRegistrar = lControlFactura.getlCabeceraFactura();

		if (entidadRegistrar.getEstadoProceso().equals("FI")) {
			addError("La factura se encuentra en un estado finalizado, no es posible retomar la factura "
					+ entidadRegistrar.getSecuencial());
			metodoInicialNormal();
			return;
		}

		switch (entidadRegistrar.getEstadoProceso()) {
		case "DE":
			lIndiceActivo = "1";
			this.paso1 = false;
			this.paso2 = true;
			break;
		case "DC":
			lIndiceActivo = "2";
			this.paso1 = false;
			this.paso2 = false;
			this.paso3 = true;
			cargarProductosEmpresaSeleccionada();
			break;
		case "DP":
			lIndiceActivo = "3";
			this.paso1 = false;
			this.paso2 = false;
			this.paso3 = false;
			this.paso4 = true;
			cargarProductosEmpresaSeleccionada();
			cargarDetallesFactura();
			cargarFormasPago();
			ajustarComponentesVisuales();
			break;
		default:
			break;
		}

	}

	private void ajustarComponentesVisuales() {
		lFormaPagoFactura = new FactFormaPagosFactura();
		lFormaPagoFactura.setPlazo(0L);
		Double totalFormaPago = 0D;
		entidadRegistrar.setTotalFormaPago(0D);
		for (FactFormaPagosFactura lform : entidadRegistrar.getlFormaPagos())
			totalFormaPago += lform.getlTotal();

		entidadRegistrar.setTotalFormaPago(totalFormaPago);

		if (totalFormaPago == entidadRegistrar.getImporteTotal())
			lFormaPagoFactura.setlTotal(0D);
		else
			lFormaPagoFactura.setlTotal(entidadRegistrar.getImporteTotal() - totalFormaPago);

	}

	private void cargarFormasPago() {
		String lQuery = "select * from ONIX_FORMAS_PAGO_FACTURA where ID_FACTURA = :idFactura";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idFactura", entidadRegistrar.getId());
		List<FactFormaPagosFactura> lListaFormaPago = lServicioFormaPagoFact.ejecutarQueryNativoObjeto(lQuery,
				lParametros, FactFormaPagosFactura.class);
		entidadRegistrar.setlFormaPagos(lListaFormaPago);

	}

	private void cargarProductosEmpresaSeleccionada() {
		String lQuery = "select * from POS_PRODUCTO where ID_EMPRESA = :idEmpresa and estado = 'A'";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idEmpresa", entidadRegistrar.getlEmpresa().getId());
		lListaProductosEmpresa = lServicioProducto.ejecutarQueryNativoObjeto(lQuery, lParametros, PosProducto.class);

		if (lListaProductosEmpresa.isEmpty())
			addMensajeAdvertencia("No tienes productos configurados para la empresa "
					+ entidadRegistrar.getlEmpresa().getlRazonSocial());

	}

	private void metodoInicialNormal() {
		lIndiceActivo = "0";
		HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
		lParametrosEmpresa.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
		String lQuery = "select * from onix_empresa where id in ( "
				+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b " + "where a.usuario = :usuario "
				+ "and a.estado = 'A' " + "and b.id_usuario = a.id " + "and b.estado = 'A') " + "and estado = 'A'";
		lListaEmpresas = lServicioUsuarioEmpresa.ejecutarQueryNativoObjeto(lQuery, lParametrosEmpresa,
				FactEmpresa.class);
		if (lListaEmpresas.isEmpty()) {
			addMensajeAdvertencia("No tienes empresas asignadas");
			return;
		}

		if (lListaEmpresas.size() > 0) {
			lEmpresaSeleccionada = lListaEmpresas.get(0);
			lListaEstablecimiento = listarEstblecimiento(lEmpresaSeleccionada.getId());

			if (lListaEstablecimiento.isEmpty()) {
				addMensajeAdvertencia("No tienes establecimientos configurados para la empresa "
						+ lEmpresaSeleccionada.getlRazonSocial());
				return;
			}

			if (lListaEstablecimiento.size() > 0) {
				lEstablecimientoSelecionado = lListaEstablecimiento.get(0);
				lListaPuntosEmision = listaPuntosEstablecimiento(lEstablecimientoSelecionado.getId());

				if (lListaPuntosEmision.isEmpty()) {
					addMensajeAdvertencia("No tienes puntos de emisión configurados  para la empresa "
							+ lEmpresaSeleccionada.getlRazonSocial());
					return;
				}

				if (lListaPuntosEmision.size() > 0)
					lPuntoEmisionSelecionado = lListaPuntosEmision.get(0);

			}
		}
	}

	private List<FactPuntosEmision> listaPuntosEstablecimiento(Long idEstablcimiento) {
		HashMap<String, Object> lParametro = new HashMap<>();
		lParametro.put("idEstablecimiento", idEstablcimiento);
		String lquery = "select * from ONIX_PUNTO_EMISION where ID_ESTABLECIMIENTO = :idEstablecimiento and estado = 'A'";

		return lMantenedorEmision.ejecutarQueryNativoObjeto(lquery, lParametro, FactPuntosEmision.class);
	}

	private List<FactEstablecimiento> listarEstblecimiento(Long idEmpresa) {
		HashMap<String, Object> lParametro = new HashMap<>();
		lParametro.put("idEmpresa", idEmpresa);
		String lquery = "select * from ONIX_ESTABLECIMIENTO where ID_EMPRESA = :idEmpresa and estado = 'A'";
		return lServicioEstablecimiento.ejecutarQueryNativoObjeto(lquery, lParametro, FactEstablecimiento.class);
	}

	public void establimientosEmpresa(AjaxBehaviorEvent evento) {
		lListaEstablecimiento = new ArrayList<>();
		lListaPuntosEmision = new ArrayList<>();
		lEmpresaSeleccionada = lServicioUsuarioEmpresa.obtenerObjtoPK(idEmpresSel, FactEmpresa.class);

		lEstablecimientoSelecionado = new FactEstablecimiento();
		lPuntoEmisionSelecionado = new FactPuntosEmision();

		lListaEstablecimiento = listarEstblecimiento(lEmpresaSeleccionada.getId());
		if (lListaEstablecimiento.isEmpty()) {
			addMensajeAdvertencia("No tienes establecimientos configurados para la empresa "
					+ lEmpresaSeleccionada.getlRazonSocial());
			return;
		}

		lEstablecimientoSelecionado = lListaEstablecimiento.get(0);

		lListaPuntosEmision = listaPuntosEstablecimiento(lEstablecimientoSelecionado.getId());
		if (lListaPuntosEmision.isEmpty()) {
			addMensajeAdvertencia("No tienes puntos de emision configurados para la empresa "
					+ lEmpresaSeleccionada.getlRazonSocial());
			return;
		}

		lPuntoEmisionSelecionado = lListaPuntosEmision.get(0);

	}

	public void secuenciaEmisionEmpresa(AjaxBehaviorEvent evento) {
		lPuntoEmisionSelecionado = lMantenedorEmision.obtenerObjtoPK(idPtoSel, FactPuntosEmision.class);
	}

	public void puntosEmisionEmpresa(AjaxBehaviorEvent evento) {
		lListaPuntosEmision = new ArrayList<>();
		lPuntoEmisionSelecionado = new FactPuntosEmision();

		lEstablecimientoSelecionado = lServicioEstablecimiento.obtenerObjtoPK(idSucSel, FactEstablecimiento.class);
		lListaPuntosEmision = listaPuntosEstablecimiento(lEstablecimientoSelecionado.getId());
		if (lListaPuntosEmision.isEmpty()) {
			addMensajeAdvertencia("No tienes puntos de emision configurados para la empresa "
					+ lEmpresaSeleccionada.getlRazonSocial());
			return;
		}
		lPuntoEmisionSelecionado = lListaPuntosEmision.get(0);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void cargarListaEtiquetas() {
	}

	@Override
	protected ServicioMantenedorFactCabecera getServicioMantenedor() {
		return lServicioCabecera;
	}

	public FactEmpresa getlEmpresaSeleccionada() {
		return lEmpresaSeleccionada;
	}

	public void setlEmpresaSeleccionada(FactEmpresa lEmpresaSeleccionada) {
		this.lEmpresaSeleccionada = lEmpresaSeleccionada;
	}

	public FactEstablecimiento getlEstablecimientoSelecionado() {
		return lEstablecimientoSelecionado;
	}

	public void setlEstablecimientoSelecionado(FactEstablecimiento lEstablecimientoSelecionado) {
		this.lEstablecimientoSelecionado = lEstablecimientoSelecionado;
	}

	public FactPuntosEmision getlPuntoEmisionSelecionado() {
		return lPuntoEmisionSelecionado;
	}

	public void setlPuntoEmisionSelecionado(FactPuntosEmision lPuntoEmisionSelecionado) {
		this.lPuntoEmisionSelecionado = lPuntoEmisionSelecionado;
	}

	public List<FactEmpresa> getlListaEmpresas() {
		return lListaEmpresas;
	}

	public void setlListaEmpresas(List<FactEmpresa> lListaEmpresas) {
		this.lListaEmpresas = lListaEmpresas;
	}

	public List<FactEstablecimiento> getlListaEstablecimiento() {
		return lListaEstablecimiento;
	}

	public void setlListaEstablecimiento(List<FactEstablecimiento> lListaEstablecimiento) {
		this.lListaEstablecimiento = lListaEstablecimiento;
	}

	public List<FactPuntosEmision> getlListaPuntosEmision() {
		return lListaPuntosEmision;
	}

	public void setlListaPuntosEmision(List<FactPuntosEmision> lListaPuntosEmision) {
		this.lListaPuntosEmision = lListaPuntosEmision;
	}

	public String getlIndiceActivo() {
		return lIndiceActivo;
	}

	public void setlIndiceActivo(String lIndiceActivo) {
		this.lIndiceActivo = lIndiceActivo;
	}

	public Long getIdEmpresSel() {
		return idEmpresSel;
	}

	public void setIdEmpresSel(Long idEmpresSel) {
		this.idEmpresSel = idEmpresSel;
	}

	public Long getIdSucSel() {
		return idSucSel;
	}

	public void setIdSucSel(Long idSucSel) {
		this.idSucSel = idSucSel;
	}

	public Long getIdPtoSel() {
		return idPtoSel;
	}

	public void setIdPtoSel(Long idPtoSel) {
		this.idPtoSel = idPtoSel;
	}

	public boolean islPresentarMensaje() {
		return lPresentarMensaje;
	}

	public void setlPresentarMensaje(boolean lPresentarMensaje) {
		this.lPresentarMensaje = lPresentarMensaje;
	}

	public boolean isPaso1() {
		return paso1;
	}

	public void setPaso1(boolean paso1) {
		this.paso1 = paso1;
	}

	public boolean isPaso2() {
		return paso2;
	}

	public void setPaso2(boolean paso2) {
		this.paso2 = paso2;
	}

	public boolean isPaso3() {
		return paso3;
	}

	public void setPaso3(boolean paso3) {
		this.paso3 = paso3;
	}

	public boolean isPaso4() {
		return paso4;
	}

	public void setPaso4(boolean paso4) {
		this.paso4 = paso4;
	}

	public boolean isPaso5() {
		return paso5;
	}

	public void setPaso5(boolean paso5) {
		this.paso5 = paso5;
	}

	public void procesoIncialFacturacion() {

		entidadRegistrar.setAmbiente("2");
		entidadRegistrar.setTipoEmision("1");
		entidadRegistrar.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
		entidadRegistrar.setCodDoc("01");
		entidadRegistrar.setClaveAcceso("");
		entidadRegistrar.setDireccionComprador("");
		entidadRegistrar.setDirEstablecimiento(lEstablecimientoSelecionado.getDireccion());
		entidadRegistrar.setDirMatriz(lEmpresaSeleccionada.getlDireccion());
		entidadRegistrar.setEstab(lEstablecimientoSelecionado.getCodigo());
		entidadRegistrar.setEstadoProceso("DE");
		entidadRegistrar.setFechaRegistro(new Date());
		entidadRegistrar.setIdentificacionComprador("");
		entidadRegistrar.setlEmpresa(lEmpresaSeleccionada);
		entidadRegistrar.setlPuntoEmision(lPuntoEmisionSelecionado);
		entidadRegistrar.setMoneda("DOLAR");
		entidadRegistrar.setNombreComercial(lEmpresaSeleccionada.getlRazonSocial());
		entidadRegistrar.setObligadoContabilidad(lEmpresaSeleccionada.getObligadoLlevarContabilidad());
		entidadRegistrar.setObservacion("DATOS EMPRESA");
		entidadRegistrar.setPtoEmi(lPuntoEmisionSelecionado.getCodigo());
		entidadRegistrar.setRazonSocial(lEmpresaSeleccionada.getlRazonSocial());
		entidadRegistrar.setRuc(lEmpresaSeleccionada.getlIdentificacion());
		entidadRegistrar.setNoResolucion(lEmpresaSeleccionada.getNumeroContribuyenteEspecial());
		entidadRegistrar.setFechaEmision(new Date());

		entidadRegistrar
				.setSecuencial(StringUtils.leftPad("" + lPuntoEmisionSelecionado.getSecuenciaFactura(), 9, "0"));

		lFormatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		entidadRegistrar.setClaveAcceso(GestorClaveAcceso.generarClaveDeAcceso(
				lFormatoFecha.format(entidadRegistrar.getFechaEmision()).replace("/", ""),
				Constantes.TIPO_COMPROBANTE_FACTURA, entidadRegistrar.getlEmpresa().getlIdentificacion(),
				entidadRegistrar.getAmbiente(), new Long(entidadRegistrar.getEstab() + entidadRegistrar.getPtoEmi()),
				new Long(entidadRegistrar.getSecuencial()), 12345678L, entidadRegistrar.getTipoEmision()));

		try {

			lServicioCabecera.guardarActualizar(entidadRegistrar);
			lPuntoEmisionSelecionado = lMantenedorEmision.obtenerObjtoPK(lPuntoEmisionSelecionado.getId(),
					FactPuntosEmision.class);
			lPuntoEmisionSelecionado.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			lPuntoEmisionSelecionado.setFechaActualizacion(new Date());
			lPuntoEmisionSelecionado.setSecuenciaFactura(lPuntoEmisionSelecionado.getSecuenciaFactura() + 1);
			lMantenedorEmision.guardarActualizar(lPuntoEmisionSelecionado);
			this.paso1 = false;
			this.paso2 = true;
			lIndiceActivo = "1";
		} catch (Exception e) {
			addMensaje("Error al realizar la transacción");
			e.printStackTrace();
		}

	}

	public FactCliente getlClienteSeleccionado() {
		return lClienteSeleccionado;
	}

	public void setlClienteSeleccionado(FactCliente lClienteSeleccionado) {
		this.lClienteSeleccionado = lClienteSeleccionado;
	}

	public void buscarCliente(AjaxBehaviorEvent pEvento) {

		try {
			HashMap<String, Object> lParametro = new HashMap<>();
			lParametro.put("idCliente", lIdentificacionCliente);
			lParametro.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
			String lquery = "select * from ONIX_CLIENTES where identificacioncomprador = :idCliente and estado = 'A' and ID_EMPRESA  in ( "
					+ " select b.id_empresa from onix_usuarios a, onix_usuario_empresa b "
					+ " where a.usuario = :usuario " + " and a.estado = 'A' " + " and b.id_usuario = a.id "
					+ " and b.estado = 'A' ) ";

			List<FactCliente> lResultadoConsulta = lServicioMantenedorCliente.ejecutarQueryNativoObjeto(lquery,
					lParametro, FactCliente.class);

			if (lResultadoConsulta.isEmpty()) {
				lClienteSeleccionado = new FactCliente();
				lClienteSeleccionado.setEstado("A");
				lClienteSeleccionado.setFechaRegistro(new Date());
				lClienteSeleccionado.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
				lClienteSeleccionado.setObservacion("NUEVO REGISTRO");
				lClienteSeleccionado.setlEmpresa(lEmpresaSeleccionada);
				lClienteSeleccionado.setIdentificacionComprador(lIdentificacionCliente);
			} else {
				lClienteSeleccionado = lResultadoConsulta.get(0);
				lClienteSeleccionado.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
				lClienteSeleccionado.setFechaActualizacion(new Date());
			}
		} catch (Exception e) {
			addMensajeAdvertencia("Error al realizar la búsqueda del usuario");
			e.printStackTrace();
		}

	}

	public void buscarClienteActualizar(AjaxBehaviorEvent pEvento) {
		lIdentificacionCliente = lClienteSeleccionado.getIdentificacionComprador();
		buscarCliente(pEvento);
	}

	public void procesoCrearActualizarCliente() {

		try {
			lServicioMantenedorCliente.guardarActualizar(lClienteSeleccionado);
			entidadRegistrar.setlCliente(lClienteSeleccionado);
			entidadRegistrar.setIdentificacionComprador(lClienteSeleccionado.getIdentificacionComprador());
			entidadRegistrar.setDireccionComprador(lClienteSeleccionado.getDireccionComprador());
			entidadRegistrar.setRazonSocialComprador(lClienteSeleccionado.getRazonSocialComprador());
			entidadRegistrar.setObservacion("DATOS CLIENTE");
			entidadRegistrar.setEstadoProceso("DC");
			entidadRegistrar.setFechaActualizacion(new Date());
			entidadRegistrar.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			lServicioCabecera.guardarActualizar(entidadRegistrar);

			this.paso1 = false;
			this.paso2 = false;
			this.paso3 = true;
			lIndiceActivo = "2";
			cargarProductosEmpresaSeleccionada();

		} catch (Exception e) {
			addMensajeAdvertencia("Imposible realizar la transacción");
			e.printStackTrace();
		}

	}

	public void procesoFinalizarDatosProductos() {

		try {
			entidadRegistrar.setObservacion("DATOS PRODUCTO");
			entidadRegistrar.setEstadoProceso("DP");
			entidadRegistrar.setFechaActualizacion(new Date());
			entidadRegistrar.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			lServicioCabecera.guardarActualizar(entidadRegistrar);

			this.paso1 = false;
			this.paso2 = false;
			this.paso3 = false;
			this.paso4 = true;
			lIndiceActivo = "3";
			cargarFormasPago();
			ajustarComponentesVisuales();

		} catch (Exception e) {
			addMensajeAdvertencia("Imposible realizar la transacción");
			e.printStackTrace();
		}

	}

	public void procesoFinalizarFactura() {

		try {
			entidadRegistrar.setObservacion("PROCESO FACTURACION FINALIZADO");
			entidadRegistrar.setEstadoProceso("FI");
			entidadRegistrar.setFechaActualizacion(new Date());
			entidadRegistrar.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			lServicioCabecera.guardarActualizar(entidadRegistrar);

			this.paso1 = false;
			this.paso2 = false;
			this.paso3 = false;
			this.paso4 = false;
			this.paso5 = true;
			lIndiceActivo = "4";

			lControlFactura.setlCabeceraFactura(entidadRegistrar);

			

		} catch (Exception e) {
			addMensajeAdvertencia("Imposible realizar la transacción");
			e.printStackTrace();
		}

	}

	

	public String getlIdentificacionCliente() {
		return lIdentificacionCliente;
	}

	public void setlIdentificacionCliente(String lIdentificacionCliente) {
		this.lIdentificacionCliente = lIdentificacionCliente;
	}

	public List<PosProducto> getlListaProductosEmpresa() {
		return lListaProductosEmpresa;
	}

	public void setlListaProductosEmpresa(List<PosProducto> lListaProductosEmpresa) {
		this.lListaProductosEmpresa = lListaProductosEmpresa;
	}

	public void seleccionarProducto(SelectEvent<PosProducto> event) {

		lProducto = event.getObject();
		String lQuery = "select * from ONIX_DET_FACTURA where ID_FACTURA = :idFactura and ID_PRODUCTO = :idProducto";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idFactura", entidadRegistrar.getId());
		lParametros.put("idProducto", lProducto.getId());
		List<FactDetalle> lListaDetalle = lServicioDetalle.ejecutarQueryNativoObjeto(lQuery, lParametros,
				FactDetalle.class);

		if (lListaDetalle.isEmpty()) {
			lFactDetalleActual = new FactDetalle();
			lFactDetalleActual.setlProducto(lProducto);
			lFactDetalleActual.setlCabecera(entidadRegistrar);
			lFactDetalleActual.setCantidad(1L);
			lFactDetalleActual.setAuditoria(JsfUtil.getNombreUsuarioAutenticado());
			lFactDetalleActual.setCodigoPrincipal(lProducto.getlCodigoProducto());
			lFactDetalleActual.setCodigoAuxiliar(lProducto.getlCodigoAuxiliar());
			lFactDetalleActual.setDetallesAdicionales(lProducto.getlDetallesAdicionales());
			lFactDetalleActual.setDescripcion(lProducto.getlNombre());
			lFactDetalleActual.setDescuento(0D);
			lFactDetalleActual.setEstado("A");
			lFactDetalleActual.setFechaRegistro(new Date());
			lFactDetalleActual.setObservacion("REGISTRO DE DETALLE DE FACTURA");
			lFactDetalleActual.setPrecioTotalSinImpuesto(lProducto.getlPrecioUnitario());
			lFactDetalleActual.setPrecioUnitario(lProducto.getlPrecioUnitario());
			lFactDetalleActual.setPrecioTotalConImpuesto(lFactDetalleActual.getPrecioTotalSinImpuesto() +

					((lFactDetalleActual.getPrecioTotalSinImpuesto()
							* lFactDetalleActual.getlProducto().getlPorcentajeIva()) / 100)

			);
		} else {
			lFactDetalleActual = lListaDetalle.get(0);
			lFactDetalleActual.setlProducto(lProducto);
			lFactDetalleActual.setAuditoria(JsfUtil.getNombreUsuarioAutenticado());
			lFactDetalleActual.setEstado("A");
			lFactDetalleActual.setFechaActualizacion(new Date());
			lFactDetalleActual.setObservacion("ACTUALIZACION DE DETALLE DE FACTURA");
			lFactDetalleActual.setCodigoPrincipal(lProducto.getlCodigoProducto());
			lFactDetalleActual.setCodigoAuxiliar(lProducto.getlCodigoAuxiliar());
			lFactDetalleActual.setDetallesAdicionales(lProducto.getlDetallesAdicionales());
			lFactDetalleActual.setDescripcion(lProducto.getlNombre());
			lFactDetalleActual.setPrecioUnitario(lProducto.getlPrecioUnitario());
			lFactDetalleActual.setPrecioTotalSinImpuesto(
					lFactDetalleActual.getCantidad() * lFactDetalleActual.getPrecioUnitario());
			lFactDetalleActual.setPrecioTotalConImpuesto(lFactDetalleActual.getPrecioTotalSinImpuesto() +

					((lFactDetalleActual.getPrecioTotalSinImpuesto()
							* lFactDetalleActual.getlProducto().getlPorcentajeIva()) / 100)

			);
		}
	}

	public FactDetalle getlFactDetalleActual() {
		return lFactDetalleActual;
	}

	public void setlFactDetalleActual(FactDetalle lFactDetalleActual) {
		this.lFactDetalleActual = lFactDetalleActual;
	}

	public PosProducto getlProducto() {
		return lProducto;
	}

	public void setlProducto(PosProducto lProducto) {
		this.lProducto = lProducto;
	}

	public void cargarDetallesFactura() {
		String lQuery = "select * from ONIX_DET_FACTURA where ID_FACTURA = :idFactura";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idFactura", entidadRegistrar.getId());
		List<FactDetalle> lListaDetalles = lServicioDetalle.ejecutarQueryNativoObjeto(lQuery, lParametros,
				FactDetalle.class);
		entidadRegistrar.setlListaDetalles(lListaDetalles);
	}

	public void calcularSubtotal(AjaxBehaviorEvent evento) {

		if (lFactDetalleActual.getCantidad() <= 0) {
			addMensajeAdvertencia("Debe Ingresar una cantidad mayor a cero");
			return;
		}

		lFactDetalleActual
				.setPrecioTotalSinImpuesto(lFactDetalleActual.getCantidad() * lFactDetalleActual.getPrecioUnitario());
		lFactDetalleActual.setPrecioTotalConImpuesto(lFactDetalleActual.getPrecioTotalSinImpuesto() +

				((lFactDetalleActual.getPrecioTotalSinImpuesto()
						* lFactDetalleActual.getlProducto().getlPorcentajeIva()) / 100)

		);
	}

	public void guardarActualizarDetalle() {

		if (lFactDetalleActual.getCantidad() <= 0) {
			addMensajeAdvertencia("Debe Ingresar una cantidad mayor a cero");
			return;
		}

		try {
			lServicioDetalle.guardarActualizar(lFactDetalleActual);
			eliminarImpuestosDetalles(lFactDetalleActual);
			eliminarImpuestosFacturas();
			calcularImpuestosDetalles(lFactDetalleActual);
			cargarDetallesFactura();
			calcularImpuestosFacturas();
		} catch (Exception e) {
			addMensajeAdvertencia("Error al realizar la transacción");
			e.printStackTrace();
		}
	}

	public void eliminarDetalleFactura(ActionEvent lEvento) {
		FactDetalle lDet = (FactDetalle) lEvento.getComponent().getAttributes().get("DETALLE");
		eliminarImpuestosDetalles(lDet);
		eliminarDetalleFactura(lDet);
		eliminarImpuestosFacturas();
		cargarDetallesFactura();
		calcularImpuestosFacturas();
	}

	public void eliminarFormaPago(ActionEvent lEvento) {
		FactFormaPagosFactura lFormaPagoFact = (FactFormaPagosFactura) lEvento.getComponent().getAttributes()
				.get("FORMA");
		eliminarFormaPagoFact(lFormaPagoFact);
		cargarFormasPago();
		ajustarComponentesVisuales();
	}

	private void eliminarFormaPagoFact(FactFormaPagosFactura lFormaPagoFact) {
		lServicioFormaPagoFact.eliminarFormaPagoFact(lFormaPagoFact);

	}

	private void eliminarDetalleFactura(FactDetalle lDet) {
		lServicioDetalle.eliminarDetalle(lDet);

	}

	private void eliminarImpuestosFacturas() {

		lServicioImpuestoFact.eliminarImpuestoFactura(entidadRegistrar);

	}

	public void eliminarImpuestosDetalles(FactDetalle lFactDetalleActual) {
		lServicioDetalleImpuesto.eliminarImpuestoDetalle(lFactDetalleActual);
	}

	public void calcularImpuestosDetalles(FactDetalle lFactDetalleActual) {

		List<FactImpuestosProductos> lListaImpuesto = lFactDetalleActual.getlProducto().getlListaImpuestos();

		for (FactImpuestosProductos lImp : lListaImpuesto) {

			FactDetalleImpuesto lImpuestoDetalle = new FactDetalleImpuesto();
			lImpuestoDetalle.setAuditoria(JsfUtil.getNombreUsuarioAutenticado());
			lImpuestoDetalle.setBaseImponible(lFactDetalleActual.getPrecioTotalSinImpuesto());
			lImpuestoDetalle.setCodigoPorcentaje(lImp.getlImpuesto().getlCodigoImpuesto());
			lImpuestoDetalle.setCodigoTipoImpuesto(lImp.getlImpuesto().getlTipoImpuesto());
			lImpuestoDetalle.setEstado("A");
			lImpuestoDetalle.setFechaRegistro(new Date());
			lImpuestoDetalle.setlDetalleFactura(lFactDetalleActual);
			lImpuestoDetalle.setObservacion("REGISTRO IMPUESTO");
			lImpuestoDetalle.setTarifa(lImp.getlImpuesto().getlPorcentaje());
			lImpuestoDetalle.setValor(
					(lFactDetalleActual.getPrecioTotalSinImpuesto() * lImp.getlImpuesto().getlPorcentaje()) / 100);
			try {
				lServicioDetalleImpuesto.guardarActualizar(lImpuestoDetalle);
			} catch (ErrorServicioNegocio | ErrorValidacionGeneral e) {
				e.printStackTrace();
			}
		}
	}

	public void calcularImpuestosFacturas() {

		try {

			List<FactDetalle> lListaDetalles = entidadRegistrar.getlListaDetalles();
			FactCabeceraImpuesto lImpuestoIva12 = new FactCabeceraImpuesto();
			FactCabeceraImpuesto lImpuestoIva0 = new FactCabeceraImpuesto();
			FactCabeceraImpuesto lImpuestoIva14 = new FactCabeceraImpuesto();
			FactCabeceraImpuesto lImpuestoIvaNOI = new FactCabeceraImpuesto();
			FactCabeceraImpuesto lImpuestoExtentoIva = new FactCabeceraImpuesto();

			inicalizarImpuestoFactura(lImpuestoIva0);
			inicalizarImpuestoFactura(lImpuestoIva12);
			inicalizarImpuestoFactura(lImpuestoIva14);
			inicalizarImpuestoFactura(lImpuestoIvaNOI);
			inicalizarImpuestoFactura(lImpuestoExtentoIva);
			Double subtotal = 0D;
			Double totalImporte = 0D;
			Double totalImpuesto = 0D;
			Double subTotal12 = 0D;
			Double subTotal0 = 0D;

			String lQuery = "select * from ONIX_DETALLE_IMPUESTOS where ID_DETALLE = :idDet ";
			HashMap<String, Object> lParametros = new HashMap<>();

			for (FactDetalle det : lListaDetalles) {
				lParametros.put("idDet", det.getId());
				List<FactDetalleImpuesto> lDetallesImp = lServicioDetalleImpuesto.ejecutarQueryNativoObjeto(lQuery,
						lParametros, FactDetalleImpuesto.class);
				for (FactDetalleImpuesto detImp : lDetallesImp) {
					if (detImp.getCodigoTipoImpuesto().equals("2")) {
						subtotal = subtotal + detImp.getBaseImponible();
						totalImpuesto = totalImpuesto + detImp.getValor();
						switch (detImp.getCodigoPorcentaje()) {
						case "0":
							complementoImpuestoCabecera(lImpuestoIva0, detImp);
							subTotal0 = subTotal0 + detImp.getValor();
							break;
						case "2":
							complementoImpuestoCabecera(lImpuestoIva12, detImp);
							subTotal12 = subTotal12 + detImp.getValor();
							break;
						case "3":
							complementoImpuestoCabecera(lImpuestoIva14, detImp);
							break;
						case "6":
							complementoImpuestoCabecera(lImpuestoIvaNOI, detImp);
							break;
						case "7":
							complementoImpuestoCabecera(lImpuestoExtentoIva, detImp);
							break;
						default:
							break;
						}

					} else if (detImp.getCodigoTipoImpuesto().equals("3")) {

					}
				}
			}
			totalImporte = subtotal + totalImpuesto;

			if (lImpuestoIva0.getValor() > 0) {
				lServicioImpuestoFact.guardarActualizar(lImpuestoIva0);
			}

			if (lImpuestoIva12.getValor() > 0) {
				lServicioImpuestoFact.guardarActualizar(lImpuestoIva12);
			}

			if (lImpuestoIva14.getValor() > 0) {
				lServicioImpuestoFact.guardarActualizar(lImpuestoIva14);
			}

			if (lImpuestoIvaNOI.getValor() > 0) {
				lServicioImpuestoFact.guardarActualizar(lImpuestoIvaNOI);
			}

			if (lImpuestoExtentoIva.getValor() > 0) {
				lServicioImpuestoFact.guardarActualizar(lImpuestoExtentoIva);
			}

			entidadRegistrar.setTotalImpuesto(totalImpuesto);
			entidadRegistrar.setSubTotal0(subTotal0);
			entidadRegistrar.setSubTotal12(subTotal12);
			entidadRegistrar.setTotalSinImpuestos(subtotal);
			entidadRegistrar.setImporteTotal(totalImporte);
			lServicioCabecera.guardarActualizar(entidadRegistrar);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void complementoImpuestoCabecera(FactCabeceraImpuesto lImpuestoIva, FactDetalleImpuesto detImp) {
		lImpuestoIva.setCodigo(detImp.getCodigoTipoImpuesto());
		lImpuestoIva.setCodigoPorcentaje(detImp.getCodigoPorcentaje());
		lImpuestoIva.setTarifa(detImp.getTarifa());
		lImpuestoIva.setBaseImponible(lImpuestoIva.getBaseImponible() + detImp.getBaseImponible());
		lImpuestoIva.setValor(lImpuestoIva.getValor() + detImp.getValor());
	}

	private void inicalizarImpuestoFactura(FactCabeceraImpuesto lImpuestoIva) {
		lImpuestoIva.setAuditoria(JsfUtil.getNombreUsuarioAutenticado());
		lImpuestoIva.setEstado("A");
		lImpuestoIva.setFechaRegistro(new Date());
		lImpuestoIva.setlFactura(entidadRegistrar);
		lImpuestoIva.setObservacion("REGISTRO DETALLES DE IMPUESTO FACT");
		lImpuestoIva.setValor(0D);
		lImpuestoIva.setBaseImponible(0D);
	}

	public FactDetalle getlFactDetalleSeleccion() {
		return lFactDetalleSeleccion;
	}

	public void setlFactDetalleSeleccion(FactDetalle lFactDetalleSeleccion) {
		this.lFactDetalleSeleccion = lFactDetalleSeleccion;
	}

	public void irCliente() {

		if (entidadRegistrar.getEstadoProceso().equals("FI"))
			return;

		paso1 = false;
		paso2 = false;
		paso3 = false;
		paso4 = false;
		paso5 = false;

		lIndiceActivo = "1";
		this.paso2 = true;
	}

	public void irProd() {
		if (entidadRegistrar.getEstadoProceso().equals("FI"))
			return;
		paso1 = false;
		paso2 = false;
		paso3 = false;
		paso4 = false;
		paso5 = false;

		lIndiceActivo = "2";
		this.paso3 = true;
	}

	public void seleccionarDetalle(SelectEvent<FactDetalle> event) {

		lFactDetalleSeleccion = event.getObject();
		SelectEvent<PosProducto> evento = new SelectEvent<PosProducto>(event.getComponent(), event.getBehavior(),
				lFactDetalleSeleccion.getlProducto());
		seleccionarProducto(evento);
	}

	public List<FactFormaPagos> getlFormasPago() {
		return lFormasPago;
	}

	public void setlFormasPago(List<FactFormaPagos> lFormasPago) {
		this.lFormasPago = lFormasPago;
	}

	public FactFormaPagosFactura getlFormaPagoFactura() {
		return lFormaPagoFactura;
	}

	public void setlFormaPagoFactura(FactFormaPagosFactura lFormaPagoFactura) {
		this.lFormaPagoFactura = lFormaPagoFactura;
	}

	public Long getlIdFormaPago() {
		return lIdFormaPago;
	}

	public void setlIdFormaPago(Long lIdFormaPago) {
		this.lIdFormaPago = lIdFormaPago;
	}

	public void procesoAgregar() {

		if (lFormaPagoFactura.getlTotal() <= 0) {
			addMensajeAdvertencia("Debe ingresar un valor mayor a cero");
			return;
		}

		if ((lFormaPagoFactura.getlTotal() + entidadRegistrar.getTotalFormaPago()) > entidadRegistrar
				.getImporteTotal()) {
			addMensajeAdvertencia(
					"Debe ingresar un valor menor, la suma de los valores de forma de pago es mayor al importe total");
			return;
		}
		try {
			FactFormaPagos lFormaPago = new FactFormaPagos();
			lFormaPago.setId(lIdFormaPago);
			lFormaPagoFactura.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			lFormaPagoFactura.setEstado("A");
			lFormaPagoFactura.setFechaRegistro(new Date());
			lFormaPagoFactura.setObservacion("REGISTRO DESDE LA WEB");
			lFormaPagoFactura.setlFactura(entidadRegistrar);
			lFormaPagoFactura.setlFormaPago(lFormaPago);

			lServicioFormaPagoFact.guardarActualizar(lFormaPagoFactura);

			cargarFormasPago();
			ajustarComponentesVisuales();

		} catch (Exception e) {
			addError("Imposible realizar el registro de forma de pago");
			e.printStackTrace();
		}
	}

	public void actualizarFechaEmision(SelectEvent evento) {
		try {

			entidadRegistrar.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			entidadRegistrar.setFechaActualizacion(new Date());
			lFormatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			entidadRegistrar.setClaveAcceso(GestorClaveAcceso.generarClaveDeAcceso(
					lFormatoFecha.format(entidadRegistrar.getFechaEmision()).replace("/", ""),
					Constantes.TIPO_COMPROBANTE_FACTURA, entidadRegistrar.getlEmpresa().getlIdentificacion(),
					entidadRegistrar.getAmbiente(),
					new Long(entidadRegistrar.getEstab() + entidadRegistrar.getPtoEmi()),
					new Long(entidadRegistrar.getSecuencial()), 12345678L, entidadRegistrar.getTipoEmision()));

			lServicioCabecera.guardarActualizar(entidadRegistrar);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
