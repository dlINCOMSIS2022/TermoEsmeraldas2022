package com.stp.plataforma.servicio;

import java.io.Serializable;

import javax.ejb.EJB;

import com.stp.plataforma.eao.aplicacion.OmsUsuarioEAO;

import librerias.dominio.entidades.base.EntidadBaseAuditable;
import librerias.eao.GenericEAO;
import librerias.exceptions.ErrorServicioNegocio;
import librerias.servicio.ServicioMantenimientoEntidad;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorControlAuditoria.
 *
 * @param <EAO> the generic type
 * @param <ENTIDAD> the generic type
 * @param <Id> the generic type
 */
public abstract class ServicioMantenedorControlAuditoria<EAO extends GenericEAO<ENTIDAD, Id>, ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable>

		extends ServicioMantenimientoEntidad<EAO, ENTIDAD, Id> {
	
	/** The l crud usuario. */
	@EJB
	private OmsUsuarioEAO lCrudUsuario;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#validarControlAuditoriaExistente(com.onix.modulo.librerias.dominio.entidades.base.EntidadBaseAuditable)
	 */
	@Override
	protected void validarControlAuditoriaExistente(ENTIDAD arg0) throws ErrorServicioNegocio {
		if ("USR_WEB".equals(arg0.getAuditoria()))
			if (lCrudUsuario.buscarUsuario(arg0.getAuditoria()) == null) {
				throw new ErrorServicioNegocio("EL USUARIO RESPONSABLE NO EXISTE");
			}
	}

}
