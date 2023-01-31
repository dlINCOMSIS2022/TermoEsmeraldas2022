package com.stp.ventas.vista;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.FactFormaPagos;
import com.stp.ventas.eao.FactFormaPagosEAO;
import com.stp.ventas.servicios.ServicioMantenedorFormaPago;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;

@ManagedBean
@ViewScoped
public class BeanMantenedorFormaPago
		extends BeanMantenedorGenerico<ServicioMantenedorFormaPago, Long, FactFormaPagos, FactFormaPagosEAO> {

	private static final long serialVersionUID = 1L;

	@EJB
	private ServicioMantenedorFormaPago lServicioFormaPago;

	public BeanMantenedorFormaPago() {
		super(FactFormaPagos.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Forma Pago");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Forma Pago");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Forma Pago");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Forma Pago");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Forma Pago");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Forma Pago");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Forma Pago");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	@Override
	protected ServicioMantenedorFormaPago getServicioMantenedor() {
		return lServicioFormaPago;
	}

}
