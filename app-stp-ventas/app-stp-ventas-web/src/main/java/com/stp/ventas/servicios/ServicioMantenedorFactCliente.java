package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactCliente;
import com.stp.ventas.eao.FactClienteEAO;

@Stateless
public class ServicioMantenedorFactCliente
		extends ServicioMantenedorControlAuditoria<FactClienteEAO, FactCliente, Long> {

	@EJB
	private FactClienteEAO lCrud;

	@Override
	protected FactClienteEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

}
