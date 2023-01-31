package com.stp.ventas.vista;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

import com.stp.ventas.dominio.FactEmpresa;
import com.stp.ventas.dominio.FactImpuestos;
import com.stp.ventas.dominio.FactImpuestosProductos;
import com.stp.ventas.dominio.PosProducto;
import com.stp.ventas.dominio.PosTipoProducto;
import com.stp.ventas.eao.PosProductoEAO;
import com.stp.ventas.servicios.ServicioMantenedorFactEmpresa;
import com.stp.ventas.servicios.ServicioMantenedorImpuesto;
import com.stp.ventas.servicios.ServicioMantenedorImpuestoProducto;
import com.stp.ventas.servicios.ServicioMantenedorTipoProducto;
import com.stp.ventas.servicios.ServicioMantenimientoProducto;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PostConstructListener;
import librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import librerias.vista.beans.oyentes.PostTransaccionListener;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanMantenedorProducto
		extends BeanMantenedorGenerico<ServicioMantenimientoProducto, Long, PosProducto, PosProductoEAO> {
	@EJB
	private ServicioMantenimientoProducto servicioMantenedor;

	@Inject
	private ServicioMantenedorFactEmpresa lServicioUsuarioEmpresa;

	@EJB
	private ServicioMantenedorTipoProducto lServicioTipoProd;

	private List<FactEmpresa> lListaEmpresaCombo;

	private Long lEmpresaID;

	private Long lTipoProducto;

	private List<PosTipoProducto> lTiposProductos;

	private byte[] imagen;

	private List<FactImpuestos> lListaImpuestoIva;

	private Long idImpuestoIva;

	private FactImpuestos impuestoIvaSelecionado;

	private FactImpuestos impuestoICESeleccionado;

	private List<FactImpuestos> lListaImpuestoICE;

	private Long idImpuestoICE;

	@EJB
	private ServicioMantenedorImpuestoProducto lServicioImpuestoProd;

	@EJB
	private ServicioMantenedorImpuesto lServicioImpuesto;

	public BeanMantenedorProducto() {
		super(PosProducto.class);

		addPostConstructuListener(new PostConstructListener() {
			@Override
			public void metodoPostConstruct() {
				impuestoIvaSelecionado = null;
				impuestoICESeleccionado = null;

				lEmpresaID = 0L;
				lTipoProducto = 0L;
				lListaEmpresaCombo = obtenerEmpresaUsuario();
				lTiposProductos = new ArrayList<>();

				String lQuery = "select * from ONIX_IMPUESTOS where lTipoImpuesto = '2' and estado = 'A'";
				HashMap<String, Object> lParametros = new HashMap<>();
				lListaImpuestoIva = lServicioImpuesto.ejecutarQueryNativoObjeto(lQuery, lParametros,
						FactImpuestos.class);

				String lQueryICE = "select * from ONIX_IMPUESTOS where lTipoImpuesto = '3' and estado = 'A'";
				HashMap<String, Object> lParametrosICE = new HashMap<>();
				lListaImpuestoICE = lServicioImpuesto.ejecutarQueryNativoObjeto(lQueryICE, lParametrosICE,
						FactImpuestos.class);

			}
		});

		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				lEmpresaID = 0L;
				lTipoProducto = 0L;
				lTiposProductos = new ArrayList<>();

				try {

					lServicioImpuestoProd.eliminarTipoImpuesto(entidadRegistrar.getId(),
							impuestoIvaSelecionado.getlTipoImpuesto());
					FactImpuestosProductos lImpProd = new FactImpuestosProductos();
					lImpProd.setlImpuesto(impuestoIvaSelecionado);
					lImpProd.setlTipoImpuesto(impuestoIvaSelecionado.getlTipoImpuesto());
					lImpProd.setlProducto(entidadRegistrar);
					lImpProd.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
					lImpProd.setEstado("A");
					lImpProd.setFechaRegistro(new Date());
					lImpProd.setObservacion("REGISTRO DE PRODUCTO");

					lServicioImpuestoProd.guardarActualizar(lImpProd);

					if (impuestoICESeleccionado != null) {

						lServicioImpuestoProd.eliminarTipoImpuesto(entidadRegistrar.getId(),
								impuestoIvaSelecionado.getlTipoImpuesto());
						FactImpuestosProductos lImpProdICE = new FactImpuestosProductos();
						lImpProdICE.setlImpuesto(impuestoICESeleccionado);
						lImpProdICE.setlTipoImpuesto(impuestoICESeleccionado.getlTipoImpuesto());
						lImpProdICE.setlProducto(entidadRegistrar);
						lImpProdICE.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());
						lImpProdICE.setEstado("A");
						lImpProdICE.setFechaRegistro(new Date());
						lImpProdICE.setObservacion("REGISTRO DE PRODUCTO");

						lServicioImpuestoProd.guardarActualizar(lImpProdICE);

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				impuestoIvaSelecionado = null;
				impuestoICESeleccionado = null;

			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {
			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				if (impuestoIvaSelecionado == null)
					throw new ErrorAccionesPreTransaccion("Debe seleccionar el IVA");

				FactEmpresa lEmpresa = new FactEmpresa();
				lEmpresa.setId(lEmpresaID);
				PosTipoProducto lTipoProd = new PosTipoProducto();
				lTipoProd.setId(lTipoProducto);
				entidadRegistrar.setlEmpresa(lEmpresa);
				entidadRegistrar.setTipoProducto(lTipoProd);
				if (impuestoIvaSelecionado != null)
					entidadRegistrar.setIdImpuestoIVA(impuestoIvaSelecionado.getId());
				if (impuestoICESeleccionado != null)
					entidadRegistrar.setIdImpuestoICE(impuestoICESeleccionado.getId());

				if (imagen != null) {

					entidadRegistrar.setImagenReferencia(imagen);

				} else {
					System.out.println("No registra avatar");
				}

			}
		});

		addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {
			@Override
			public void cargarListaEntidades() {
				HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
				lParametrosEmpresa.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
				String lQuery = "select *  from POS_PRODUCTO where ID_EMPRESA  in ( "
						+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b "
						+ "where a.usuario = :usuario " + "and a.estado = 'A' " + "and b.id_usuario = a.id "
						+ "and b.estado = 'A') " + "and estado = 'A'";
				List<PosProducto> lListaTipo = servicioMantenedor.ejecutarQueryNativoObjeto(lQuery, lParametrosEmpresa,
						PosProducto.class);
				setListaEntidades(lListaTipo);
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<PosProducto, Long>() {

			@Override
			public void postSeleccionRegistro(PosProducto pEntidadSeleccionada) {

				lEmpresaID = pEntidadSeleccionada.getlEmpresa().getId();
				lTipoProducto = pEntidadSeleccionada.getTipoProducto().getId();
				idImpuestoIva = pEntidadSeleccionada.getIdImpuestoIVA();
				idImpuestoICE = pEntidadSeleccionada.getIdImpuestoICE();

				lTiposProductos = new ArrayList<>();
				HashMap<String, Object> lParametrosTipo = new HashMap<>();
				lParametrosTipo.put("idEmpresa", lEmpresaID);
				String lQuery = "select * from POS_TIPO_PRODUCTO where ID_EMPRESA = :idEmpresa and estado = 'A'";
				lTiposProductos = lServicioTipoProd.ejecutarQueryNativoObjeto(lQuery, lParametrosTipo,
						PosTipoProducto.class);
				if (lTiposProductos.isEmpty())
					addMensajeAdvertencia("No existen tipos de productos configurados para la empresa seleccionada");
			}
		});

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServicioMantenimientoProducto getServicioMantenedor() {
		// TODO Auto-generated method stub
		return servicioMantenedor;
	}

	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	private List<FactEmpresa> obtenerEmpresaUsuario() {
		HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
		lParametrosEmpresa.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
		String lQuery = "select * from onix_empresa where id in ( "
				+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b " + "where a.usuario = :usuario "
				+ "and a.estado = 'A' " + "and b.id_usuario = a.id " + "and b.estado = 'A') " + "and estado = 'A'";
		List<FactEmpresa> lListaEmpresa = lServicioUsuarioEmpresa.ejecutarQueryNativoObjeto(lQuery, lParametrosEmpresa,
				FactEmpresa.class);
		return lListaEmpresa;
	}

	public List<FactEmpresa> getlListaEmpresaCombo() {
		return lListaEmpresaCombo;
	}

	public void setlListaEmpresaCombo(List<FactEmpresa> lListaEmpresaCombo) {
		this.lListaEmpresaCombo = lListaEmpresaCombo;
	}

	public Long getlEmpresaID() {
		return lEmpresaID;
	}

	public void setlEmpresaID(Long lEmpresaID) {
		this.lEmpresaID = lEmpresaID;
	}

	public List<PosTipoProducto> getlTiposProductos() {
		return lTiposProductos;
	}

	public void setlTiposProductos(List<PosTipoProducto> lTiposProductos) {
		this.lTiposProductos = lTiposProductos;
	}

	public Long getlTipoProducto() {
		return lTipoProducto;
	}

	public void setlTipoProducto(Long lTipoProducto) {
		this.lTipoProducto = lTipoProducto;
	}

	public void buscarTipos(AjaxBehaviorEvent evento) {

		lTiposProductos = new ArrayList<>();
		HashMap<String, Object> lParametrosTipo = new HashMap<>();
		lParametrosTipo.put("idEmpresa", lEmpresaID);
		String lQuery = "select * from POS_TIPO_PRODUCTO where ID_EMPRESA = :idEmpresa and estado = 'A'";
		lTiposProductos = lServicioTipoProd.ejecutarQueryNativoObjeto(lQuery, lParametrosTipo, PosTipoProducto.class);
		if (lTiposProductos.isEmpty())
			addMensajeAdvertencia("No existen tipos de productos configurados para la empresa seleccionada");

	}

	public void calcularIVA(AjaxBehaviorEvent evento) {
		if (entidadRegistrar.getlPrecioUnitario() == null || entidadRegistrar.getlPrecioUnitario() <= 0) {
			addMensajeAdvertencia("Debe ingresar un valor positivo en el campo precio unitario");
			return;
		}

		impuestoIvaSelecionado = lServicioImpuesto.obtenerObjtoPK(idImpuestoIva, FactImpuestos.class);

		entidadRegistrar.setlPorcentajeIva(impuestoIvaSelecionado.getlPorcentaje());

		entidadRegistrar
				.setlPrecioIVA(entidadRegistrar.getlPrecioUnitario() * (entidadRegistrar.getlPorcentajeIva() / 100)
						+ entidadRegistrar.getlPrecioUnitario());
	}

	public void calcularICE(AjaxBehaviorEvent evento) {
		if (entidadRegistrar.getlPrecioUnitario() == null || entidadRegistrar.getlPrecioUnitario() <= 0) {
			addMensajeAdvertencia("Debe ingresar un valor positivo en el campo precio unitario");
			return;
		}

		impuestoICESeleccionado = lServicioImpuesto.obtenerObjtoPK(idImpuestoICE, FactImpuestos.class);

		entidadRegistrar.setlPorcentajeICE(impuestoICESeleccionado.getlPorcentaje());

		entidadRegistrar
				.setlPrecioICE(entidadRegistrar.getlPrecioUnitario() * (entidadRegistrar.getlPorcentajeICE() / 100)
						+ entidadRegistrar.getlPrecioUnitario());
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

	public List<FactImpuestos> getlListaImpuestoIva() {
		return lListaImpuestoIva;
	}

	public void setlListaImpuestoIva(List<FactImpuestos> lListaImpuestoIva) {
		this.lListaImpuestoIva = lListaImpuestoIva;
	}

	public Long getIdImpuestoIva() {
		return idImpuestoIva;
	}

	public void setIdImpuestoIva(Long idImpuestoIva) {
		this.idImpuestoIva = idImpuestoIva;
	}

	public List<FactImpuestos> getlListaImpuestoICE() {
		return lListaImpuestoICE;
	}

	public void setlListaImpuestoICE(List<FactImpuestos> lListaImpuestoICE) {
		this.lListaImpuestoICE = lListaImpuestoICE;
	}

	public Long getIdImpuestoICE() {
		return idImpuestoICE;
	}

	public void setIdImpuestoICE(Long idImpuestoICE) {
		this.idImpuestoICE = idImpuestoICE;
	}

}
