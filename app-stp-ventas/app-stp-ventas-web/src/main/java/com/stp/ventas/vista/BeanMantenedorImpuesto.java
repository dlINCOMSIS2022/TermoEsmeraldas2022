package com.stp.ventas.vista;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.FactImpuestos;
import com.stp.ventas.eao.FactImpuestosEAO;
import com.stp.ventas.servicios.ServicioMantenedorImpuesto;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;

@ManagedBean
@ViewScoped
public class BeanMantenedorImpuesto
		extends BeanMantenedorGenerico<ServicioMantenedorImpuesto, Long, FactImpuestos, FactImpuestosEAO> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicioMantenedorImpuesto lServicioImpuesto;

	public BeanMantenedorImpuesto() {
		super(FactImpuestos.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Impuesto");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Impuesto");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Impuesto");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Impuesto");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Impuesto");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Impuesto");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Impuesto");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	@Override
	protected ServicioMantenedorImpuesto getServicioMantenedor() {
		return lServicioImpuesto;
	}

}
