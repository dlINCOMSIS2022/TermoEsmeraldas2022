package com.stp.ventas.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactFormaPagosFactura;
import com.stp.ventas.eao.FactFormaPagosFacturaEAO;

@Stateless
public class ServicioMantenedorFormaPagoFactura
		extends ServicioMantenedorControlAuditoria<FactFormaPagosFacturaEAO, FactFormaPagosFactura, Long> {
	@EJB
	private FactFormaPagosFacturaEAO lCrud;

	@Override
	protected FactFormaPagosFacturaEAO getCrud() {
		// TODO Auto-generated method stub
		return lCrud;
	}

	@Override
	protected void cargarConfiguracionServicio() {
		// TODO Auto-generated method stub

	}

	public void eliminarFormaPagoFact(FactFormaPagosFactura lFormaPagoFact) {
		lCrud.getAdminEntidad().createNativeQuery("delete from ONIX_FORMAS_PAGO_FACTURA where id = :id ")
		.setParameter("id", lFormaPagoFact.getId()).executeUpdate();

	}

}
