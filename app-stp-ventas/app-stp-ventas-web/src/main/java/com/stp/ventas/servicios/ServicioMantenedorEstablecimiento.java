package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactEstablecimiento;
import com.stp.ventas.eao.FactEstablecimientoEAO;

@Stateless
public class ServicioMantenedorEstablecimiento
		extends ServicioMantenedorControlAuditoria<FactEstablecimientoEAO, FactEstablecimiento, Long> {

	@EJB
	private FactEstablecimientoEAO lCrud;

	@Override
	protected FactEstablecimientoEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

}
