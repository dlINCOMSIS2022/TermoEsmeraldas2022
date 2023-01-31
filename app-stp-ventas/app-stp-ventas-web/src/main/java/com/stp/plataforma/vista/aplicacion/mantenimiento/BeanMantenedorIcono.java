package com.stp.plataforma.vista.aplicacion.mantenimiento;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.plataforma.dominio.aplicacion.OmgIcono;
import com.stp.plataforma.eao.aplicacion.OmgIconoEAO;
import com.stp.plataforma.servicio.aplicacion.mantenimiento.ServicioMantenedorIcono;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.PostTransaccionListener;

// TODO: Auto-generated Javadoc
/**
 * The Class BeanMantenedorIcono.
 */
@ManagedBean
@ViewScoped
public class BeanMantenedorIcono 
extends BeanMantenedorGenerico<ServicioMantenedorIcono, Long, OmgIcono, OmgIconoEAO>
{

	/**
	 * Instantiates a new bean mantenedor icono.
	 */
	public BeanMantenedorIcono() {
		super( OmgIcono.class);
		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				
			}
		});
	}

	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The l servicio. */
	@EJB
	private ServicioMantenedorIcono lServicio;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Íconos registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaciónn de Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Íconos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Íconos registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#getServicioMantenedor()
	 */
	@Override
	protected ServicioMantenedorIcono getServicioMantenedor() {
		return lServicio;
	}

}
