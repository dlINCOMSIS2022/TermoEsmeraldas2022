package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.PosTipoProducto;
import com.stp.ventas.eao.PosTipoProductoEAO;

@Stateless
public class ServicioMantenedorTipoProducto
		extends ServicioMantenedorControlAuditoria<PosTipoProductoEAO, PosTipoProducto, Long> {

	@EJB
	private PosTipoProductoEAO crud;

	@Override
	protected PosTipoProductoEAO getCrud() {
		// TODO Auto-generated method stub
		return crud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		System.out.println("SIN NADA QUE CONFIGURAR");
	}

}
