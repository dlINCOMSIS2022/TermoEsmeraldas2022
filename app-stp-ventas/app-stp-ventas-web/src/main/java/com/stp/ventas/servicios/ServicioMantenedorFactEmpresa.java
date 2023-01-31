/*
 * 
 */
package com.stp.ventas.servicios;

import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactEmpresa;
import com.stp.ventas.eao.FactEmpresaEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorFactEmpresa.
 */
@Stateless
public class ServicioMantenedorFactEmpresa
		extends ServicioMantenedorControlAuditoria<FactEmpresaEAO, FactEmpresa, Long> {

	/** The l crud. */
	@EJB
	private FactEmpresaEAO lCrud;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#
	 * cargarConfiguracionServicio()
	 */
	protected void cargarConfiguracionServicio() {
		System.out.println("Si nada que configurar");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected FactEmpresaEAO getCrud() {
		return lCrud;
	}

	
	
	public void actualizarDatosBitacora(String lClaveAcceso, String lCorreos) {
		lCrud.getAdminEntidad().createNativeQuery("update onix_bitacora_email set tipo = :tipo, observacion = :observacion, emails = :email where clave_acceso = :claveAcceso")
		.setParameter("tipo", "EXITO").setParameter("observacion", "COMPROBANTE ENVIADO CON EXITO").setParameter("email", lCorreos).setParameter("claveAcceso", lClaveAcceso)
		.executeUpdate();
	}
	
	public void insertarDatosBitacora(String lClaveAcceso, String lCorreos) {
		lCrud.getAdminEntidad().createNativeQuery("insert into onix_bitacora_email (id, estado, fecha_registro, observacion, clave_acceso, ruc, emails, tipo) values ( "
				+ "nextval('sec_fact_bitacora'), "
				+ "'A', "
				+ ":fecha, "
				+ ":observacion, "
				+ ":clave_acceso, "
				+ ":ruc, "
				+ ":emails, "
				+ ":tipo) ")
		.setParameter("fecha", new Date())
		.setParameter("observacion", "COMPROBANTE ENVIADO CON EXITO")
		.setParameter("clave_acceso", lClaveAcceso)
		.setParameter("ruc", lClaveAcceso.substring(10,23))
		.setParameter("emails", lCorreos)
		.setParameter("tipo", "EXITO")
		.executeUpdate();
	}

}
