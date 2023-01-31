package com.stp.plataforma.servicio.aplicacion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsOpcionesRole;
import com.stp.plataforma.eao.aplicacion.OmsOpcionesRoleEAO;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorOpcionRol.
 */
@Stateless
public class ServicioMantenedorOpcionRol
		extends ServicioMantenedorControlAuditoria<OmsOpcionesRoleEAO, OmsOpcionesRole, Long> {

	/** The crud. */
	@EJB
	private OmsOpcionesRoleEAO crud;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected OmsOpcionesRoleEAO getCrud() {
		return crud;
	}

	/**
	 * Eliminar opciones role.
	 *
	 * @param idRol the id rol
	 */
	public void eliminarOpcionesRole(Long idRol) {
		crud.eliminarOpcionesRol(idRol);
	} 

	/**
	 * Lista opciones ejecutables rol.
	 *
	 * @param lUsuario the l usuario
	 * @param tipo the tipo
	 * @return the list
	 */
	public List<OmsOpcione> listaOpcionesEjecutablesRol(String lUsuario, String tipo) {
		return crud.obtenerListaOpcionesEjecutablesRol(lUsuario, tipo);
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {
		System.out.println("NO EXISTEN CONFIGURACIONES PREVIAS DEL SERVICIO");

	}

	/**
	 * Obtener opcion rol por opcion rol.
	 *
	 * @param pNobreOpcion the nobre opcion
	 * @param idRol the id rol
	 * @return the oms opciones role
	 */
	public OmsOpcionesRole obtenerOpcionRolPorOpcionRol(String pNobreOpcion, Long idRol) {
		List<OmsOpcionesRole> lListaOpcionesRoles = obtenerListaOpcionRolPorOpcionRol(pNobreOpcion, idRol);
		return lListaOpcionesRoles.isEmpty() ? null : lListaOpcionesRoles.get(0);
	}

	/**
	 * Obtener lista opcion rol por opcion rol.
	 *
	 * @param pNobreOpcion the nobre opcion
	 * @param idRol the id rol
	 * @return the list
	 */
	public List<OmsOpcionesRole> obtenerListaOpcionRolPorOpcionRol(final String pNobreOpcion, final Long idRol) {
		return getCrud().ejecutarQueryNativo("select * from ONIX_OPCIONES_ROLES  " + "where id_rol = :idRol  "
				+ "and id_opcion = (select ID from ONIX_OPCIONES where OPCION = :nombreOpcion )",
		new HashMap<String, Object>() {
			static final long serialVersionUID = 1L;
			{
				put("idRol", idRol);
				put("nombreOpcion", pNobreOpcion);
			}
		}, OmsOpcionesRole.class);
	}

}
