package com.stp.plataforma.eao.aplicacion;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsOpcionesRole;
import com.stp.plataforma.eao.OnixEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class OmsOpcionesRoleEAO.
 */
@Stateless
@LocalBean
public class OmsOpcionesRoleEAO extends OnixEAO<OmsOpcionesRole, Long> {

	/** The Constant OPCIONES_PADRE. */
	public static final String OPCIONES_PADRE = "OPCIONES_ROLES.OPCIONES_PADRE";
	
	/** The Constant OPCIONES_PADRE_ROL. */
	public static final String OPCIONES_PADRE_ROL = "OPCIONES_ROLES.OPCIONES_PADRE_ROL";

	/**
	 * Obtener lista opciones rol.
	 *
	 * @param usuario the usuario
	 * @param orientacion the orientacion
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsOpcione> obtenerListaOpcionesRol(Long usuario, String orientacion) {
		Query query = adminEntidad.createNamedQuery(OPCIONES_PADRE);
		query.setParameter(1, usuario);
		query.setParameter(2, orientacion);
		return query.getResultList();
	}

	/**
	 * Obtener lista opciones rol usuario.
	 *
	 * @param rol the rol
	 * @param orientacion the orientacion
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsOpcione> obtenerListaOpcionesRolUsuario(Long rol, String orientacion) {
		Query query = adminEntidad.createNamedQuery(OPCIONES_PADRE_ROL);
		query.setParameter("idRol", rol);
		query.setParameter("orientacion", orientacion);
		return query.getResultList();
	}
	
	/**
	 * Obtener opcion rol.
	 *
	 * @param idOpcion the id opcion
	 * @param idRol the id rol
	 * @return the oms opciones role
	 */
	@SuppressWarnings("unchecked")
	public OmsOpcionesRole obtenerOpcionRol(Integer idOpcion, Integer idRol) {
		Query query = adminEntidad.createNativeQuery(
				"select op.* from ONIX_OPCIONES_ROLES op " + " where op.id_opcion = ? " + " and op.id_rol = ? ",
				OmsOpcionesRole.class);
		query.setParameter(1, idOpcion);
		query.setParameter(2, idRol);
		List<OmsOpcionesRole> resultado = query.getResultList();
		if (!resultado.isEmpty()) {
			return resultado.get(0);
		} else {
			return null;
		}
	}

	/**
	 * Actualizar opciones rol.
	 *
	 * @param estado the estado
	 * @param idOpRol the id op rol
	 */
	public void actualizarOpcionesRol(String estado, Integer idOpRol) {
		String lsQuery = " update ONIX_OPCIONES_ROLES b " + " set b.estado = ?, b.fecha_actualizacion=sysdate "
				+ " where b.id = ? ";
		Query query = adminEntidad.createNativeQuery(lsQuery);
		query.setParameter(1, estado);
		query.setParameter(2, idOpRol);
		query.executeUpdate();
	}

	/**
	 * Eliminar opciones rol.
	 *
	 * @param idRol the id rol
	 */
	public void eliminarOpcionesRol(Long idRol) {
		String lsQuery = " delete ONIX_OPCIONES_ROLES b " + " where b.ID_ROL = :idRol ";
		Query query = adminEntidad.createNativeQuery(lsQuery);
		query.setParameter("idRol", idRol);
		query.executeUpdate();
	}

	/**
	 * Ingresar opcion rol.
	 *
	 * @param opcRol the opc rol
	 */
	public void ingresarOpcionRol(OmsOpcionesRole opcRol) {
		adminEntidad.persist(opcRol);
	}

	/**
	 * Obtener lista opciones ejecutables rol.
	 *
	 * @param lUsuario the l usuario
	 * @param tipo the tipo
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsOpcione> obtenerListaOpcionesEjecutablesRol(String lUsuario, String tipo) {
		String lsQuery = " select f.* from ONIX_OPCIONES f, ONIX_OPCIONES_ROLES s " + "where f.id = s.id_opcion "
				+ "and f.estado = 'A' " + "and f.accion is not null " + "and f.tipo = :tipo " + "and s.id_rol in (  "
				+ "select id_rol from ONIX_USUARIOS_ROLES f " + "where f.id_usuario in ( "
				+ "select g.id from ONIX_USUARIOS g " + "where g.usuario = :usuario " + "and g.estado = 'A' " + " ) "
				+ "and estado = 'A' " + ") ";
		Query query = adminEntidad.createNativeQuery(lsQuery, OmsOpcione.class);
		query.setParameter("usuario", lUsuario);
		query.setParameter("tipo", tipo);
		return query.getResultList();
	}

}
