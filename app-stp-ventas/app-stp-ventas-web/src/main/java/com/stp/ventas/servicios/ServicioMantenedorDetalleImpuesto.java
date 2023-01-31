package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactDetalle;
import com.stp.ventas.dominio.FactDetalleImpuesto;
import com.stp.ventas.eao.FactDetalleImpuestoEAO;

@Stateless
public class ServicioMantenedorDetalleImpuesto
		extends ServicioMantenedorControlAuditoria<FactDetalleImpuestoEAO, FactDetalleImpuesto, Long> {

	@EJB
	private FactDetalleImpuestoEAO lCrud;

	@Override
	protected FactDetalleImpuestoEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	public void eliminarImpuestoDetalle(FactDetalle lFactDetalleActual) {

		lCrud.getAdminEntidad().createNativeQuery("delete from ONIX_DETALLE_IMPUESTOS where ID_DETALLE = :idDetalle ")
				.setParameter("idDetalle", lFactDetalleActual.getId()).executeUpdate();

	}

}
