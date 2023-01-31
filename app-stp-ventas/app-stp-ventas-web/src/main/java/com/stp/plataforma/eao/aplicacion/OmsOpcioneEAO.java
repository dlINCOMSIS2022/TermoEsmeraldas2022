package com.stp.plataforma.eao.aplicacion;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.stp.plataforma.dominio.aplicacion.OmsAccesosDirecto;
import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsOpcionesRole;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.eao.OnixEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class OmsOpcioneEAO.
 */
@Stateless
@LocalBean
public class OmsOpcioneEAO extends OnixEAO<OmsOpcione, Long> {

	/**
	 * Permitir acceso opcion.
	 *
	 * @param opcion the opcion
	 * @param usuario the usuario
	 * @return true, if successful
	 */
	public boolean permitirAccesoOpcion(OmsOpcione opcion, OmsUsuario usuario) {
		String querySql = "select * " + " "
				+ "from ONIX_OPCIONES_ROLES f " 
				+ " where f.id_rol in "
				+ "     (select d.id_rol "
				+ "from ONIX_USUARIOS_ROLES d where d.id_usuario = ? " + " and d.estado = 'A'"
				+ "      ) " + " and f.id_opcion in ( select id from "
						+ "ONIX_OPCIONES where id = ? and tipo = 'P' )  " 
				+ " and f.estado = 'A'";

		Query query = adminEntidad.createNativeQuery(querySql, OmsOpcionesRole.class);
		query.setParameter(1, usuario.getId());
		query.setParameter(2, opcion.getId());
		return query.getResultList().size() > 0;
	}
	
	/**
	 * Permitir acceso opcion.
	 *
	 * @param opcion the opcion
	 * @param usuario the usuario
	 * @return true, if successful
	 */
	public boolean permitirAccesoOpcion(OmsOpcione opcion, Long usuario) {
		String querySql = "select * " + " "
				+ "from ONIX_OPCIONES_ROLES f " 
				+ " where f.id_rol in "
				+ "     (select d.id_rol "
				+ "from ONIX_USUARIOS_ROLES d where d.id_usuario = ? " + " and d.estado = 'A'"
				+ "      ) " + " and f.id_opcion in ( select id from "
						+ "ONIX_OPCIONES where id = ? and tipo = 'P' )  " 
				+ " and f.estado = 'A'";

		Query query = adminEntidad.createNativeQuery(querySql, OmsOpcionesRole.class);
		query.setParameter(1, usuario);
		query.setParameter(2, opcion.getId());
		return query.getResultList().size() > 0;
	}
	
	/**
	 * Permitir acceso opcion.
	 *
	 * @param opcion the opcion
	 * @param rol the rol
	 * @return true, if successful
	 */
	public boolean permitirAccesoOpcion(OmsOpcione opcion, String rol) {
		String querySql = "select * "  
				+ " from ONIX_OPCIONES_ROLES f " 
				+ " where f.id_rol in "
				+ "     ( select d.id "
				+ " from ONIX_ROLES d where d.rol = ? and d.estado = 'A'"
				+ "      ) " + " and f.id_opcion in ( select id from "
						+ "ONIX_OPCIONES where id = ? and tipo = 'P' )  " 
				+ " and f.estado = 'A'";

		Query query = adminEntidad.createNativeQuery(querySql, OmsOpcionesRole.class);
		query.setParameter(1, rol);
		query.setParameter(2, opcion.getId());
		return query.getResultList().size() > 0;
	}

	/**
	 * Obtiene opciones por usuario.
	 *
	 * @param usuario the usuario
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsOpcione> obtieneOpcionesPorUsuario(String usuario) {
		Query query = adminEntidad.createNativeQuery("SELECT OP.* " + "FROM ONIX_USUARIOS        U, "
				+ "ONIX_USUARIOS_ROLES UR, " + "ONIX_OPCIONES       OP, " + "ONIX_OPCIONES_ROLES OPR "
				+ "WHERE U.ID = UR.ID_USUARIO " + "AND UR.ID_ROL = OPR.ID_ROL " + "AND OPR.ID_OPCION = OP.ID "
				+ "AND OPR.ESTADO = 'A' " + "AND OP.ACCION IS NOT NULL " + "AND UPPER(U.USUARIO) = ?",
				OmsOpcione.class);
		query.setParameter(1, usuario);
		return query.getResultList();
	}

	/**
	 * Lista opciones terminales.
	 *
	 * @param usuario the usuario oms_USUARIOS_ROLES
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsOpcione> listaOpcionesTerminales(String usuario) 
	{
		Query query = adminEntidad.createNativeQuery(
				"SELECT OP.* " + "FROM ONIX_USUARIOS        U, "
				+ "ONIX_USUARIOS_ROLES UR, " + "ONIX_OPCIONES       OP, " + "ONIX_OPCIONES_ROLES OPR "
				+ "WHERE U.ID = UR.ID_USUARIO " + "AND UR.ID_ROL = OPR.ID_ROL " + "AND OPR.ID_OPCION = OP.ID "
				+ "AND OPR.ESTADO = 'A' " + "AND OP.ACCION IS NOT NULL " + "AND UPPER(U.USUARIO) = ? and OP.ESTADO = 'A'",
				OmsOpcione.class).setParameter(1, usuario);
		return query.getResultList();
	}

	/**
	 * Lista opciones asignada.
	 *
	 * @param rol the rol
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsOpcione> listaOpcionesAsignada(String rol) {
		Query query = adminEntidad.createNativeQuery(
				" select op.* from ONIX_OPCIONES_ROLES opr, ONIX_OPCIONES op, ONIX_ROLES r "
						+ " where opr.id_opcion = op.id and opr.id_rol = r.id "
						+ " and op.Modulo_Padre is not null and upper(r.rol) = upper(?) and opr.estado='A' ",
				OmsOpcione.class);
		query.setParameter(1, rol);
		return query.getResultList();
	}

	/**
	 * Obtener opcion padre.
	 *
	 * @param opcion the opcion
	 * @return the oms opcione
	 */
	public OmsOpcione obtenerOpcionPadre(Integer opcion) {
		Query query = adminEntidad
				.createNativeQuery(" select b.* from ONIX_OPCIONES b where b.id = ? and b.estado = 'A' "
						+ "and b.Modulo_Padre is not null  ", OmsOpcione.class);
		query.setParameter(1, opcion);
		return (OmsOpcione) query.getSingleResult();
	}

	/**
	 * Cantidad opciones por padre.
	 *
	 * @param rol the rol
	 * @param opcion the opcion
	 * @return the integer
	 */
	@SuppressWarnings("unchecked")
	public Integer cantidadOpcionesPorPadre(Integer rol, Integer opcion) {
		TypedQuery<BigDecimal> qr = (TypedQuery<BigDecimal>) adminEntidad
				.createNativeQuery(" select count(*) from ONIX_OPCIONES_ROLES ro " + " where ro.id_rol = ? "
						+ " and ro.estado = 'A' " + " and ro.id_opcion in( " + " select o.id from ONIX_OPCIONES o "
						+ " where o.modulo_padre = (select o.id " + "     from ONIX_OPCIONES o "
						+ "     where o.Modulo_Padre is null " + "      and o.id = ?)) ");
		qr.setParameter(1, rol);
		qr.setParameter(2, opcion);
		BigDecimal bd = qr.getSingleResult();
		return bd.intValue();
	}

	/**
	 * Obtener opcion usuario.
	 *
	 * @param idUsuario the id usuario
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsAccesosDirecto> obtenerOpcionUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		String sql = " select * from ONIX_ACCESOS_DIRECTOS f " + "where f.id_usuario = :idUsuario "
				+ "and f.estado = 'A'";
		Query query = adminEntidad.createNativeQuery(sql, OmsAccesosDirecto.class);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}

	/**
	 * Eliminar accesos usuario.
	 *
	 * @param idUsuario the id usuario
	 */
	public void eliminarAccesosUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		String sql = "delete from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario";
		Query query = adminEntidad.createNativeQuery(sql, OmsAccesosDirecto.class);
		query.setParameter("idUsuario", idUsuario);
		query.executeUpdate();
	}

	/**
	 * Obtener opcion no asignada usuario.
	 *
	 * @param idUsuario the id usuario oms_OPCIONES
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsOpcione> obtenerOpcionNoAsignadaUsuario(Long idUsuario) {
		// TODO Auto-generated method stub
		String sql = " select * " + "from ONIX_OPCIONES r " + "where r.id not in (select f.id_opcion "
				+ "from ONIX_ACCESOS_DIRECTOS f  where f.id_usuario = :idUsuario "
				+ " and f.estado = 'A') and r.estado = 'A' and r.accion is not null " + "and r.id in ( "
				+ " SELECT OP.id  FROM ONIX_USUARIOS        U, " + "ONIX_USUARIOS_ROLES UR, " + "ONIX_OPCIONES       OP, "
				+ "ONIX_OPCIONES_ROLES OPR " + "WHERE U.ID = UR.ID_USUARIO " + "AND UR.ID_ROL = OPR.ID_ROL "
				+ "AND OPR.ID_OPCION = OP.ID " + "AND OP.ACCION IS NOT NULL " + "AND U.id = :idUsuario " + ") ";
		Query query = adminEntidad.createNativeQuery(sql, OmsOpcione.class);
		query.setParameter("idUsuario", idUsuario);
		return query.getResultList();
	}

	/**
	 * Guardar acceso directo.
	 *
	 * @param acceso the acceso
	 */
	public void guardarAccesoDirecto(OmsAccesosDirecto acceso) {
		adminEntidad.persist(acceso);
		adminEntidad.flush();
	}

	/**
	 * Buscar opcion por nombre.
	 *
	 * @param nombre the nombre
	 * @return the oms opcione
	 */
	public OmsOpcione buscarOpcionPorNombre(String nombre) {
		String sql = "SELECT * FROM ONIX_OPCIONES t " + "where upper(t.opcion) = upper(:opcion) " + "and t.estado = 'A'";
		Query query = adminEntidad.createNativeQuery(sql, OmsOpcione.class);
		query.setParameter("opcion", nombre);
		@SuppressWarnings("unchecked")
		List<OmsOpcione> lista = query.getResultList();

		if (lista.isEmpty()) {
			return null;
		} else {
			return lista.get(0);
		}
	}

}
