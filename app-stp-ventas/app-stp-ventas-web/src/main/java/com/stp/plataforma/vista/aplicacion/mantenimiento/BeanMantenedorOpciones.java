package com.stp.plataforma.vista.aplicacion.mantenimiento;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import com.stp.plataforma.dominio.aplicacion.OmgIcono;
import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.eao.aplicacion.OmsOpcioneEAO;
import com.stp.plataforma.servicio.aplicacion.mantenimiento.ServicioMantenedorIcono;
import com.stp.plataforma.servicio.aplicacion.mantenimiento.ServicioMantenedorOpcion;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.PostCierreDialogo;
import librerias.vista.beans.oyentes.PostConstructListener;
import librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import librerias.vista.beans.oyentes.PostTransaccionListener;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

// TODO: Auto-generated Javadoc 
/**
 * The Class BeanMantenedorOpciones.
 */
@ManagedBean
@ViewScoped
public class BeanMantenedorOpciones
		extends BeanMantenedorGenerico<ServicioMantenedorOpcion, Long, OmsOpcione, OmsOpcioneEAO> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The servicio mantenedor. */
	@EJB
	private ServicioMantenedorOpcion servicioMantenedor;
	
	/** The servicio icono. */
	@EJB
	private ServicioMantenedorIcono servicioIcono;
	
	/** The lista iconos. */
	private List<OmgIcono> listaIconos;
	
	/** The l lista opciones padre. */
	private List<OmsOpcione> lListaOpcionesPadre;

	/** The l lista completa opciones. */
	private List<OmsOpcione> lListaCompletaOpciones;

	/** The l tipo opcion. */
	private String lTipoOpcion;

	/** The l id opcion. */
	private Long lIdOpcion;
	
	/** The icono seleccionado. */
	private OmgIcono iconoSeleccionado;

	/** The l campos submenu. */
	private boolean lCamposSubmenu;
	
	/** The l campos opcion. */
	private boolean lCamposOpcion;

	/**
	 * Instantiates a new bean mantenedor opciones.
	 */
	public BeanMantenedorOpciones() {
		super( OmsOpcione.class);
		lCamposSubmenu = false;
		lCamposOpcion = false;
		iconoSeleccionado = new OmgIcono();
		
		addPostEventoCierroDialogo(new PostCierreDialogo() {
			@Override
			public void eventosCierreDialogo() {
				lCamposSubmenu = false;
				lCamposOpcion = false;
			}
		});
		
		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<OmsOpcione, Long>() {

			public void postSeleccionRegistro(OmsOpcione pEntidadSeleccionada) {

				if (pEntidadSeleccionada.getAccion() != null) {
					lCamposOpcion = true;
					lCamposSubmenu = true;
					lListaOpcionesPadre = servicioMantenedor
							.listaOpcionesSubmenu(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
					lIdOpcion = pEntidadSeleccionada.getModuloPadre().getId();
				} else {
					if (pEntidadSeleccionada.getModuloPadre() != null) {
						lCamposSubmenu = true;
						lCamposOpcion = false;
						lListaOpcionesPadre = servicioMantenedor
								.listaOpcionesPadre(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
						lIdOpcion = pEntidadSeleccionada.getModuloPadre().getId();
					} else {
						lCamposSubmenu = false;
						lCamposOpcion = false;
						entidadRegistrar.setModuloPadre(null);
					}
				}

			}
		});
		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				lListaOpcionesPadre = servicioMantenedor
						.listaOpcionesPadre(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
				// lListaSubmenu =
				// servicioMantenedor.listaOpcionesSubmenu(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
				lListaCompletaOpciones = servicioMantenedor
						.listaOpciones(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));

				listaIconos = servicioIcono.listaObjetosActivos(OmgIcono.class);
			}
		});

		addPostConstructuListener(new PostConstructListener() {

			@Override
			public void metodoPostConstruct() {
				lListaOpcionesPadre = servicioMantenedor
						.listaOpcionesPadre(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
				lListaCompletaOpciones = servicioMantenedor
						.listaOpciones(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
				listaIconos = servicioIcono.listaObjetosActivos(OmgIcono.class);
			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {

			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				
				
				
				if (lIdOpcion != null) {
					OmsOpcione lOpcion = new OmsOpcione();
					lOpcion.setId(lIdOpcion);
					entidadRegistrar.setModuloPadre(lOpcion);
				}
				entidadRegistrar.setOrientacion("V");
				entidadRegistrar.setTipo("P");
				entidadRegistrar.setObservacion("CREACIÃ“N DE OPCION DESDE LA CAPA WEB");
			}
		});
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite los Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Opciones registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Opción");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#getServicioMantenedor()
	 */
	@Override
	protected ServicioMantenedorOpcion getServicioMantenedor() {
		return servicioMantenedor;
	}

	/**
	 * Gets the l lista opciones padre.
	 *
	 * @return the l lista opciones padre
	 */
	public List<OmsOpcione> getlListaOpcionesPadre() {
		return lListaOpcionesPadre;
	}

	/**
	 * Sets the l lista opciones padre.
	 *
	 * @param lListaOpcionesPadre the new l lista opciones padre
	 */
	public void setlListaOpcionesPadre(List<OmsOpcione> lListaOpcionesPadre) {
		this.lListaOpcionesPadre = lListaOpcionesPadre;
	}

	/**
	 * Gets the l tipo opcion.
	 *
	 * @return the l tipo opcion
	 */
	public String getlTipoOpcion() {
		return lTipoOpcion;
	}

	/**
	 * Sets the l tipo opcion.
	 *
	 * @param lTipoOpcion the new l tipo opcion
	 */
	public void setlTipoOpcion(String lTipoOpcion) {
		this.lTipoOpcion = lTipoOpcion;
	}

	/**
	 * Gets the l lista completa opciones.
	 *
	 * @return the l lista completa opciones
	 */
	public List<OmsOpcione> getlListaCompletaOpciones() {
		return lListaCompletaOpciones;
	}

	/**
	 * Sets the l lista completa opciones.
	 *
	 * @param lListaCompletaOpciones the new l lista completa opciones
	 */
	public void setlListaCompletaOpciones(List<OmsOpcione> lListaCompletaOpciones) {
		this.lListaCompletaOpciones = lListaCompletaOpciones;
	}

	/**
	 * Presentar campos adicionales.
	 */
	public void presentarCamposAdicionales(AjaxBehaviorEvent evento) {
		switch (lTipoOpcion) {
		case "SUBMENU":
			lCamposSubmenu = true;
			lCamposOpcion = false;
			lListaOpcionesPadre = servicioMantenedor.listaOpcionesPadre(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
			break;
		case "OPCION":
			lCamposOpcion = true;
			lCamposSubmenu = true;
			lListaOpcionesPadre = servicioMantenedor
					.listaOpcionesSubmenu(obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
			break;
		default:
			lCamposSubmenu = false;
			lCamposOpcion = false;
			break;
		}
	}

	/**
	 * Gets the l campos submenu.
	 *
	 * @return the l campos submenu
	 */
	public boolean getlCamposSubmenu() {
		return this.lCamposSubmenu;
	}

	/**
	 * Sets the l campos submenu.
	 *
	 * @param lCamposSubmenu the new l campos submenu
	 */
	public void setlCamposSubmenu(boolean lCamposSubmenu) {
		this.lCamposSubmenu = lCamposSubmenu;
	}

	/**
	 * Gets the l campos opcion.
	 *
	 * @return the l campos opcion
	 */
	public boolean getlCamposOpcion() {
		return this.lCamposOpcion;
	}

	/**
	 * Sets the l campos opcion.
	 *
	 * @param lCamposOpcion the new l campos opcion
	 */
	public void setlCamposOpcion(boolean lCamposOpcion) {
		this.lCamposOpcion = lCamposOpcion;
	}

	/**
	 * Gets the l id opcion.
	 *
	 * @return the l id opcion
	 */
	public Long getlIdOpcion() {
		return lIdOpcion;
	}

	/**
	 * Sets the l id opcion.
	 *
	 * @param lIdOpcion the new l id opcion
	 */
	public void setlIdOpcion(Long lIdOpcion) {
		this.lIdOpcion = lIdOpcion;
	}

	/**
	 * Gets the lista iconos.
	 *
	 * @return the lista iconos
	 */
	public List<OmgIcono> getListaIconos() {
		return listaIconos;
	} 
	
	/**
	 * Sets the lista iconos.
	 *
	 * @param listaIconos the new lista iconos
	 */
	public void setListaIconos(List<OmgIcono> listaIconos) {
		this.listaIconos = listaIconos;
	}
	
	/**
	 * Gets the icono seleccionado.
	 *
	 * @return the icono seleccionado
	 */
	public OmgIcono getIconoSeleccionado() {
		return iconoSeleccionado;
	}
	
	/**
	 * Sets the icono seleccionado.
	 *
	 * @param iconoSeleccionado the new icono seleccionado
	 */
	public void setIconoSeleccionado(OmgIcono iconoSeleccionado) {
		this.iconoSeleccionado = iconoSeleccionado;
	}
	
	public void buscarOpcionCreada(AjaxBehaviorEvent pEvento) {

		OmsOpcione lOpcionDB = servicioMantenedor.obtenerObjetoPropiedad("opcion", entidadRegistrar.getOpcion(), OmsOpcione.class);
		
		if (lOpcionDB!=null)
		{
			entidadRegistrar.setOpcion("");
			addError("La opcion " + lOpcionDB.getOpcion() + ", ya se encuentra registrada, por favor revisar desde la tabla de opciones");
		}
	}
	
	
}
