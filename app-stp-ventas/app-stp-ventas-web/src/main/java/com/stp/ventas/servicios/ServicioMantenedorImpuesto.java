package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactImpuestos;
import com.stp.ventas.eao.FactImpuestosEAO;

@Stateless
public class ServicioMantenedorImpuesto
		extends ServicioMantenedorControlAuditoria<FactImpuestosEAO, FactImpuestos, Long> {

	@EJB
	private FactImpuestosEAO lCrud;

	@Override
	protected FactImpuestosEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

}
