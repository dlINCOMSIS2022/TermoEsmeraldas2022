package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.PosProducto;
import com.stp.ventas.eao.PosProductoEAO;

@Stateless
public class ServicioMantenimientoProducto
		extends ServicioMantenedorControlAuditoria<PosProductoEAO, PosProducto, Long> {

	@EJB
	private PosProductoEAO crud;

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	@Override
	protected PosProductoEAO getCrud() {
		return crud;
	}

}
