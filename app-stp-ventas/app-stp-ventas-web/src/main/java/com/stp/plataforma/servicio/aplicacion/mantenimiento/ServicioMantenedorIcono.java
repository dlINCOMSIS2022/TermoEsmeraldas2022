package com.stp.plataforma.servicio.aplicacion.mantenimiento;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.dominio.aplicacion.OmgIcono;
import com.stp.plataforma.eao.aplicacion.OmgIconoEAO;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorIcono.
 */
@Stateless
public class ServicioMantenedorIcono extends  ServicioMantenedorControlAuditoria<OmgIconoEAO, OmgIcono, Long>
{
	
	/** The crud. */
	@EJB
	private OmgIconoEAO crud;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected OmgIconoEAO getCrud() {
		return crud;
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	protected void cargarConfiguracionServicio() {
		System.out.println("NO EXISTE CONFIGURACIÓN ADICIONAL PARA ESTE SERVICIO ServicioMantenedorIcono " );
	}

}
