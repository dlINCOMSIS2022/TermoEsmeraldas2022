package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactPuntosEmision;
import com.stp.ventas.eao.FactPuntosEmisionEAO;

@Stateless
public class ServicioMantenedorFactPuntoEmision 
extends ServicioMantenedorControlAuditoria<FactPuntosEmisionEAO, FactPuntosEmision, Long> {


	@EJB
	private FactPuntosEmisionEAO lCrud;

	@Override
	protected FactPuntosEmisionEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

}
