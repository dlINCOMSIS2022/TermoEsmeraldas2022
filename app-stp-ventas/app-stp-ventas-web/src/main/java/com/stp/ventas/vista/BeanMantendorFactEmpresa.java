package com.stp.ventas.vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.servicio.aplicacion.mantenimiento.ServicioMantenedorUsuario;
import com.stp.ventas.dominio.FactEmpresa;
import com.stp.ventas.dominio.FactEstablecimiento;
import com.stp.ventas.dominio.FactPuntosEmision;
import com.stp.ventas.dominio.FactUsuarioEmpresa;
import com.stp.ventas.eao.FactEmpresaEAO;
import com.stp.ventas.servicios.ServicioMantenedorEstablecimiento;
import com.stp.ventas.servicios.ServicioMantenedorFactEmpresa;
import com.stp.ventas.servicios.ServicioMantenedorFactPuntoEmision;
import com.stp.ventas.servicios.ServicioMantenedorFactUsurioEmpresa;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.PostConstructListener;
import librerias.vista.beans.oyentes.PostInicializacionEntidad;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanMantendorFactEmpresa
		extends BeanMantenedorGenerico<ServicioMantenedorFactEmpresa, Long, FactEmpresa, FactEmpresaEAO> {

	private static final String JPG = ".jpg";

	private static final String SEPARADOR = "/";

	private static final String P12 = ".p12";

	private Date lFechaLimiteVigenciaCertificado;

	private String lClave;

	private DualListModel<String> listaSeleccionUsuario;

	private byte[] imagen;

	private List<FactEstablecimiento> lListaEstablecimiento;

	private List<FactPuntosEmision> lListaPuntos;

	private Long idEstablecimientoSel;

	@EJB
	private ServicioMantenedorEstablecimiento lServicioEstablecimiento;

	@EJB
	private ServicioMantenedorFactUsurioEmpresa lServicioUsuarioEmpresa;

	@EJB
	private ServicioMantenedorFactPuntoEmision lMantenedorEmision;

	public List<FactEstablecimiento> getlListaEstablecimiento() {
		return lListaEstablecimiento;
	}

	public void setlListaEstablecimiento(List<FactEstablecimiento> lListaEstablecimiento) {
		this.lListaEstablecimiento = lListaEstablecimiento;
	}

	@EJB
	private ServicioMantenedorUsuario lServicioUsuario;

	public BeanMantendorFactEmpresa() {
		super(FactEmpresa.class);
		lClave = "";
		addPostConstructuListener(new PostConstructListener() {

			public void metodoPostConstruct() {
				lListaEstablecimiento = new ArrayList<>();
				listaSeleccionUsuario = new DualListModel<>();
				lFechaLimiteVigenciaCertificado = new Date();
				entidadRegistrar = new FactEmpresa();
				lClave = "";
				System.out.println("pruebas");
			}
		});

		addPostEventoInicializacion(new PostInicializacionEntidad() {

			@Override
			public void eventoPostInicializacion() {
				lListaEstablecimiento = new ArrayList<>();
				entidadRegistrar = new FactEmpresa();
				lFechaLimiteVigenciaCertificado = new Date();
				lClave = "";
			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {

			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				entidadRegistrar.setlFormatoCertificado("pk12");
				entidadRegistrar.setlRutaCertificado("D:/resultado_test/cert_key.p12");
				entidadRegistrar.setlIdSuscriptor(1L);

			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {
			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				if (entidadRegistrar.getId() == null) {
					FactEmpresa lEmpresa = lServicioMantenedorFactEmpresa.obtenerObjetoPropiedad("lIdentificacion",
							entidadRegistrar.getlIdentificacion(), FactEmpresa.class);
					if (lEmpresa != null)
						throw new ErrorAccionesPreTransaccion("La empresa con identificación : "
								+ lEmpresa.getlIdentificacion() + ", ya se encuentra registrada");
				}

				if (imagen != null) {

					entidadRegistrar.setImagenReferencia(imagen);

				} else {
					System.out.println("No registra avatar");
				}

			}
		});

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private ServicioMantenedorFactEmpresa lServicioMantenedorFactEmpresa;

	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Empresas");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	protected ServicioMantenedorFactEmpresa getServicioMantenedor() {
		return lServicioMantenedorFactEmpresa;
	}

	public void subirCertificado(ActionEvent lEvento) {
		entidadRegistrar = (FactEmpresa) lEvento.getComponent().getAttributes().get("EMPRESA");
	}

	public void subirLogo(ActionEvent lEvento) {
		entidadRegistrar = (FactEmpresa) lEvento.getComponent().getAttributes().get("EMPRESA");
	}

	public void asignarUsuarios(ActionEvent lEvento) {
		entidadRegistrar = (FactEmpresa) lEvento.getComponent().getAttributes().get("EMPRESA");

		cargarUsuariosAsignados();

	}

	public Date getlFechaLimiteVigenciaCertificado() {
		return lFechaLimiteVigenciaCertificado;
	}

	public void setlFechaLimiteVigenciaCertificado(Date lFechaLimiteVigenciaCertificado) {
		this.lFechaLimiteVigenciaCertificado = lFechaLimiteVigenciaCertificado;
	}

	public String getlClave() {
		return lClave;
	}

	public void setlClave(String lClave) {
		this.lClave = lClave;
	}

	public DualListModel<String> getListaSeleccionUsuario() {
		return listaSeleccionUsuario;
	}

	public void setListaSeleccionUsuario(DualListModel<String> listaSeleccionUsuario) {
		this.listaSeleccionUsuario = listaSeleccionUsuario;
	}

	public void controlTransferencia(TransferEvent pEvento) {

		List<String> lUsuariosTransferidos = (List<String>) pEvento.getItems();
		try {
			if (pEvento.isAdd()) {
				agregarUsuariosEmpresa(lUsuariosTransferidos);
			} else {
				if (pEvento.isRemove()) {
					eliminarUsuariosEmpresa(lUsuariosTransferidos);
				}
			}
			cargarUsuariosAsignados();
		} catch (Exception e) {
			e.printStackTrace();
			addError(e.getMessage());

		}
	}

	private void eliminarUsuariosEmpresa(List<String> lUsuariosTransferidos) {

		FactEmpresa lEmpresa = lServicioMantenedorFactEmpresa.obtenerObjtoPK(entidadRegistrar.getId(),
				FactEmpresa.class);
		for (String lUsuario : lUsuariosTransferidos) {
			OmsUsuario lUsuarioDB = lServicioUsuario.obtenerObjetoPropiedad("usuario", lUsuario, OmsUsuario.class);
			lServicioUsuarioEmpresa.eliminarAsignacion(lUsuarioDB, lEmpresa);
		}
	}

	private void agregarUsuariosEmpresa(List<String> lUsuariosTransferidos) {
		try {
			FactEmpresa lEmpresa = lServicioMantenedorFactEmpresa.obtenerObjtoPK(entidadRegistrar.getId(),
					FactEmpresa.class);
			for (String lUsuario : lUsuariosTransferidos) {
				OmsUsuario lUsuarioDB = lServicioUsuario.obtenerObjetoPropiedad("usuario", lUsuario, OmsUsuario.class);
				FactUsuarioEmpresa lUsuarioEmpresa = new FactUsuarioEmpresa();
				lUsuarioEmpresa.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
				lUsuarioEmpresa.setlUsuario(lUsuarioDB);
				lUsuarioEmpresa.setEstado("A");
				lUsuarioEmpresa.setFechaRegistro(new Date());
				lUsuarioEmpresa.setlEmpresa(lEmpresa);
				lUsuarioEmpresa.setObservacion("REGISTRO DE USUARIOS EMPRESA");
				lServicioUsuarioEmpresa.guardarActualizar(lUsuarioEmpresa);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addError("No es posible continuar con la asignación");
		}

	}

	private void cargarUsuariosAsignados() {
		List<String> lListaUsuariosAsignados = lServicioUsuarioEmpresa
				.obtenerUsuariosAsignados(entidadRegistrar.getId());
		List<String> lListaUsuariosPorAsignar = lServicioUsuarioEmpresa
				.obtenerUsuariosPorAsignar(entidadRegistrar.getId());
		listaSeleccionUsuario = new DualListModel<>(lListaUsuariosPorAsignar, lListaUsuariosAsignados);
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public void subir(FileUploadEvent event) {
		System.out.println("Subir archivo");
		imagen = event.getFile().getContent();
	}

	public void sucursal(ActionEvent evento) {
		entidadRegistrar = (FactEmpresa) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());
		listarEstblecimiento();

	}

	private void listarEstblecimiento() {
		HashMap<String, Object> lParametro = new HashMap<>();
		lParametro.put("idEmpresa", entidadRegistrar.getId());
		String lquery = "select * from ONIX_ESTABLECIMIENTO where ID_EMPRESA = :idEmpresa and estado = 'A'";
		lListaEstablecimiento = lServicioEstablecimiento.ejecutarQueryNativoObjeto(lquery, lParametro,
				FactEstablecimiento.class);
	}

	public void puntosEmison(ActionEvent evento) {
		entidadRegistrar = (FactEmpresa) evento.getComponent().getAttributes().get(getNombreAtributoCambioEstado());

		listarEstblecimiento();
		lListaPuntos = new ArrayList<>();
		if (lListaEstablecimiento.size() == 0) {
			addMensajeAdvertencia(
					"Por favor ingrese los establecimientos para la empresa " + entidadRegistrar.getlRazonSocial());
			return;
		}

		FactEstablecimiento lEstablecimientoDefecto = lListaEstablecimiento.get(0);
		idEstablecimientoSel = lEstablecimientoDefecto.getId();
		listaPuntosEstablecimiento(idEstablecimientoSel);

	}

	private void listaPuntosEstablecimiento(Long idEstablcimiento) {
		HashMap<String, Object> lParametro = new HashMap<>();
		lParametro.put("idEstablecimiento", idEstablcimiento);
		String lquery = "select * from ONIX_PUNTO_EMISION where ID_ESTABLECIMIENTO = :idEstablecimiento and estado = 'A'";

		lListaPuntos = lMantenedorEmision.ejecutarQueryNativoObjeto(lquery, lParametro, FactPuntosEmision.class);
	}

	public ServicioMantenedorEstablecimiento getlServicioEstablecimiento() {
		return lServicioEstablecimiento;
	}

	public void setlServicioEstablecimiento(ServicioMantenedorEstablecimiento lServicioEstablecimiento) {
		this.lServicioEstablecimiento = lServicioEstablecimiento;
	}

	public void agregarEstablecimiento() {
		if (lListaEstablecimiento == null)
			lListaEstablecimiento = new ArrayList<>();

		if (lListaEstablecimiento.size() > 0) {
			FactEstablecimiento lActual = lListaEstablecimiento.get(lListaEstablecimiento.size() - 1);
			if (lActual.getId() != null)
				lListaEstablecimiento.add(new FactEstablecimiento());
		} else {
			lListaEstablecimiento.add(new FactEstablecimiento());
		}

	}

	public void onRowCancel(RowEditEvent event) {
		FactEstablecimiento lEstable = (FactEstablecimiento) event.getObject();
		if (lEstable.getId() == null) {
			listarEstblecimiento();
		} else {
			lEstable.setEstado("I");
			lEstable.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			lEstable.setFechaActualizacion(new Date());
			try {
				lServicioEstablecimiento.guardarActualizar(lEstable);
				listarEstblecimiento();
			} catch (Exception e) {
				e.printStackTrace();
				addMensajeAdvertencia("Imposible realizar la transacción");
			}
		}
	}

	public void onRowEdit(RowEditEvent event) {
		FactEstablecimiento lEstable = (FactEstablecimiento) event.getObject();

		if (lEstable.getId() == null) {
			lEstable.setlEmpresa(entidadRegistrar);
			lEstable.setEstado("A");
			lEstable.setFechaRegistro(new Date());
			lEstable.setObservacion("REGISTRO DE ESTABLECIMIENTO");
		} else {
			lEstable.setFechaActualizacion(new Date());
		}
		lEstable.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());

		try {
			lServicioEstablecimiento.guardarActualizar(lEstable);
			listarEstblecimiento();
		} catch (Exception e) {
			e.printStackTrace();
			addMensajeAdvertencia("Imposible realizar la transacción");
		}
	}

	public void agregarPunto() {
		if (lListaPuntos == null)
			lListaPuntos = new ArrayList<>();

		if (lListaPuntos.size() > 0) {
			FactPuntosEmision lActual = lListaPuntos.get(lListaPuntos.size() - 1);
			if (lActual.getId() != null)
				lListaPuntos.add(new FactPuntosEmision());
		} else {
			lListaPuntos.add(new FactPuntosEmision());
		}

	}

	public void buscarPuntos(AjaxBehaviorEvent evento) {
		listaPuntosEstablecimiento(idEstablecimientoSel);
	}

	public void onRowEditPunto(RowEditEvent event) {

		FactPuntosEmision lPunto = (FactPuntosEmision) event.getObject();

		if (lPunto.getId() == null) {
			FactEstablecimiento lEstablecimientoPunto = new FactEstablecimiento();
			lEstablecimientoPunto.setId(idEstablecimientoSel);

			lPunto.setlEstblecimiento(lEstablecimientoPunto);
			lPunto.setEstado("A");
			lPunto.setFechaRegistro(new Date());
			lPunto.setObservacion("REGISTRO DE ESTABLECIMIENTO");
		} else {
			lPunto.setFechaActualizacion(new Date());
		}
		lPunto.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());

		try {
			lMantenedorEmision.guardarActualizar(lPunto);
			listaPuntosEstablecimiento(idEstablecimientoSel);
		} catch (Exception e) {
			e.printStackTrace();
			addMensajeAdvertencia("Imposible realizar la transacción");
		}
	}

	public void onRowCancelPunto(RowEditEvent event) {
		FactPuntosEmision lEmision = (FactPuntosEmision) event.getObject();
		if (lEmision.getId() == null) {
			listaPuntosEstablecimiento(idEstablecimientoSel);
		} else {
			lEmision.setEstado("I");
			lEmision.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
			lEmision.setFechaActualizacion(new Date());
			try {
				lMantenedorEmision.guardarActualizar(lEmision);
				listaPuntosEstablecimiento(idEstablecimientoSel);
			} catch (Exception e) {
				e.printStackTrace();
				addMensajeAdvertencia("Imposible realizar la transacción");
			}
		}
	}

	public List<FactPuntosEmision> getlListaPuntos() {
		return lListaPuntos;
	}

	public void setlListaPuntos(List<FactPuntosEmision> lListaPuntos) {
		this.lListaPuntos = lListaPuntos;
	}

	public Long getIdEstablecimientoSel() {
		return idEstablecimientoSel;
	}

	public void setIdEstablecimientoSel(Long idEstablecimientoSel) {
		this.idEstablecimientoSel = idEstablecimientoSel;
	}

}
