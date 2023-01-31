package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactCabecera;
import com.stp.ventas.eao.FactCabeceraEAO;

@Stateless
public class ServicioMantenedorFactCabecera
		extends ServicioMantenedorControlAuditoria<FactCabeceraEAO, FactCabecera, Long> {

	@EJB
	private FactCabeceraEAO lCrud;

	@Override
	protected FactCabeceraEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

}
