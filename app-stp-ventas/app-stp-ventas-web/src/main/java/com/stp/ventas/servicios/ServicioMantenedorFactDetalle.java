package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactDetalle;
import com.stp.ventas.eao.FactDetalleEAO;

@Stateless
public class ServicioMantenedorFactDetalle
		extends ServicioMantenedorControlAuditoria<FactDetalleEAO, FactDetalle, Long> {
	@EJB
	private FactDetalleEAO crud;

	@Override
	protected FactDetalleEAO getCrud() {
		return crud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	public void eliminarDetalle(FactDetalle lDet) {
		crud.getAdminEntidad().createNativeQuery("delete from ONIX_DET_FACTURA where id = :id ")
				.setParameter("id", lDet.getId()).executeUpdate();

	}

}
