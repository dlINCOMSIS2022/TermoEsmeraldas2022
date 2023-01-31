package com.stp.ventas.vista;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.stp.ventas.dominio.FactCabecera;

@SessionScoped
@Named
public class BeanControlFactura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FactCabecera lCabeceraFactura;

	public FactCabecera getlCabeceraFactura() {
		return lCabeceraFactura;
	}

	public void setlCabeceraFactura(FactCabecera lCabeceraFactura) {
		this.lCabeceraFactura = lCabeceraFactura;
	}	
}
