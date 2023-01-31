package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactFormaPagos;
import com.stp.ventas.eao.FactFormaPagosEAO;

@Stateless
public class ServicioMantenedorFormaPago
		extends ServicioMantenedorControlAuditoria<FactFormaPagosEAO, FactFormaPagos, Long> {

	@EJB
	private FactFormaPagosEAO lCrud;

	@Override
	protected FactFormaPagosEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

}
