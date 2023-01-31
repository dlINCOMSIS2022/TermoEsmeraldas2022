package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactCabecera;
import com.stp.ventas.dominio.FactCabeceraImpuesto;
import com.stp.ventas.eao.FactCabeceraImpuestoEAO;

@Stateless
public class ServicioMantenedorImpuestoFactura
		extends ServicioMantenedorControlAuditoria<FactCabeceraImpuestoEAO, FactCabeceraImpuesto, Long> {

	@EJB
	private FactCabeceraImpuestoEAO lCrud;

	@Override
	protected FactCabeceraImpuestoEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	public void eliminarImpuestoFactura(FactCabecera entidadRegistrar) {

		lCrud.getAdminEntidad()
				.createNativeQuery("delete from ONIX_CAB_FACTURA_IMPUESTO where ID_FACTURA = :idFactura ")
				.setParameter("idFactura", entidadRegistrar.getId()).executeUpdate();

	}

}
