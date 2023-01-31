package com.stp.plataforma.eao.aplicacion;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.stp.plataforma.dominio.aplicacion.OmsOpcionesRole;
import com.stp.plataforma.dominio.aplicacion.OmsRole;
import com.stp.plataforma.eao.OnixEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class OmsRoleEAO.
 */
@Stateless
@LocalBean
public class OmsRoleEAO extends OnixEAO<OmsRole, Long> {

	/**
	 * Lista roles activos.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsRole> listaRolesActivos() {
		Query query = adminEntidad.createNativeQuery("select * from ONIX_ROLES a where a.estado = 'A' ", OmsRole.class);
		return query.getResultList();
	}

	/**
	 * Listar roles disponibles.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsRole> listarRolesDisponibles() {
		Query query = adminEntidad.createNativeQuery("SELECT * FROM sb_roles R", OmsRole.class);
		return query.getResultList();
	}

	/**
	 * Cod rol asesor.
	 *
	 * @return the integer
	 */
	@SuppressWarnings("unchecked")
	public Integer codRolAsesor() {
		TypedQuery<BigDecimal> qr = (TypedQuery<BigDecimal>) adminEntidad
				.createNativeQuery(" select ro.id from ONIX_ROLES ro " + " where ro.estado = 'A' "
						+ " and ro.rol = 'ASESOR' ");
		BigDecimal bd = qr.getSingleResult();
		return bd.intValue();
	}

	/**
	 * Verificar si existe rol opcion.
	 *
	 * @param idRol the id rol
	 * @param idOpcion the id opcion
	 * @return true, if successful
	 */
	@SuppressWarnings("unchecked")
	public boolean verificarSiExisteRolOpcion(Long idRol, Long idOpcion)
	{
		String sql = "select * from ONIX_OPCIONES_ROLES t1 "
				+ "where t1.id_rol = :rol "
				+ "and t1.id_opcion = :opcion "
				+ "and t1.estado = 'A'";
		Query query = adminEntidad.createNativeQuery(sql, OmsOpcionesRole.class);
		query.setParameter("rol", idRol);
		query.setParameter("opcion", idOpcion);
		List<OmsOpcionesRole> resultado = query.getResultList();
		if (resultado.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Obtener roles.
	 *
	 * @param idRoles the id roles
	 * @return the oms role
	 */
	@SuppressWarnings("unchecked")
	public OmsRole obtenerRoles(Long idRoles) {
		String sql = "select * from ONIX_ROLES r where r.id!=1 and r.id= :idRol";
		Query query = adminEntidad.createNativeQuery(sql, OmsRole.class);
		query.setParameter("idRol", idRoles);

		List<OmsRole> resultado = query.getResultList();
		if (resultado.isEmpty()) {
			return null;
		} else {
			return resultado.get(0);
		}
	}
}
