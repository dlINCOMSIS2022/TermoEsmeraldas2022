package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactImpuestosProductos;
import com.stp.ventas.eao.FactImpuestosProductosEAO;

@Stateless
public class ServicioMantenedorImpuestoProducto
		extends ServicioMantenedorControlAuditoria<FactImpuestosProductosEAO, FactImpuestosProductos, Long> {

	@EJB
	private FactImpuestosProductosEAO lCrud;

	@Override
	protected FactImpuestosProductosEAO getCrud() {
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {

	}

	public void eliminarTipoImpuesto(Long idProd, String lTipoImp) {
		lCrud.getAdminEntidad()
				.createNativeQuery(
						"delete from POS_IMPUESTO_PRODUCTO where ID_PRODUCTO = :idPrd and lTipoImpuesto = :tipoImp")
				.setParameter("idPrd", idProd).setParameter("tipoImp", lTipoImp).executeUpdate();

	}

}
