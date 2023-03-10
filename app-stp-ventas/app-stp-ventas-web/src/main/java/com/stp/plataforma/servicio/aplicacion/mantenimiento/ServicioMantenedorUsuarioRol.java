package com.stp.plataforma.servicio.aplicacion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.dominio.aplicacion.OmsUsuariosRole;
import com.stp.plataforma.eao.aplicacion.OmsUsuariosRoleEAO;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorUsuarioRol.
 */
@Stateless
public class ServicioMantenedorUsuarioRol
		extends ServicioMantenedorControlAuditoria<OmsUsuariosRoleEAO, OmsUsuariosRole, Long> {

	/** The crud. */
	@EJB
	private OmsUsuariosRoleEAO crud;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected OmsUsuariosRoleEAO getCrud() {
		return crud;
	}

	/**
	 * Eliminar roles usuario.
	 *
	 * @param idUsuario the id usuario
	 */
	public void eliminarRolesUsuario(Long idUsuario) {
		crud.eliminarUsuarioRol(idUsuario);
	}
	
	

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {
		System.out.println("NO EXISTEN CONFIGURACIONES PARA EL SEVICIO ServicioMantenedorUsuarioRol");

	}

	/**
	 * Obtener lista usuario rol id usuario.
	 *
	 * @param pIdUsuario the id usuario
	 * @return the list
	 */
	public List<OmsUsuariosRole> obtenerListaUsuarioRolIdUsuario(Long pIdUsuario)
	{
		return getCrud().ejecutarQueryNativo("select * from ONIX_USUARIOS_ROLES where id_usuario = :idUsuario "
				+ "and estado = 'A'",
		new HashMap<String, Object>() {
			static final long serialVersionUID = 1L;
			{
				put("idUsuario", pIdUsuario);
			}
		}, OmsUsuariosRole.class); 
		
		
		
	}
	
	/**
	 * Obtener usuario rol por rol usuario.
	 *
	 * @param pRol the rol
	 * @param pIdUsuario the id usuario
	 * @return the oms usuarios role
	 */
	public OmsUsuariosRole obtenerUsuarioRolPorRolUsuario(String pRol, Long pIdUsuario ) {
		List<OmsUsuariosRole> lListaOpcionesRoles = obtenerListaUsuarioRolPorRolUsuario(pRol, pIdUsuario);
		return lListaOpcionesRoles.isEmpty() ? null : lListaOpcionesRoles.get(0);
	}
	
	/**
	 * Obtener lista usuario rol por rol usuario.
	 *
	 * @param lRol the l rol
	 * @param id the id
	 * @return the list
	 */
	public List<OmsUsuariosRole>  obtenerListaUsuarioRolPorRolUsuario(String lRol, Long id) {
		
		return getCrud().ejecutarQueryNativo("select * from ONIX_USUARIOS_ROLES where id_usuario = :idUsuario "
				+ "and id_rol = (select ID from ONIX_ROLES where rol = :nombreRol)",
		new HashMap<String, Object>() {
			static final long serialVersionUID = 1L;
			{
				put("idUsuario", id);
				put("nombreRol", lRol);
			}
		}, OmsUsuariosRole.class); 
	}
}
