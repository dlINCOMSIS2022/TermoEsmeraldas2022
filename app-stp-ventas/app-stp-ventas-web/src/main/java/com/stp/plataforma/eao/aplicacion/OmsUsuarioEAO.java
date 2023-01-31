package com.stp.plataforma.eao.aplicacion;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.eao.OnixEAO;



// TODO: Auto-generated Javadoc
/**
 * The Class OmsUsuarioEAO.
 */
@Stateless
@LocalBean
public class OmsUsuarioEAO extends OnixEAO<OmsUsuario, Long>{
	
/**
 * Obtener usuarios.
 *
 * @param idUsuario the id usuario
 * @return the list
 */
/*
	public PriUsuarioEAO() 
	{	
		super(PriUsuario.class);
		
	}*/
	@SuppressWarnings("unchecked")
	public List<OmsUsuario> obtenerUsuarios(Long idUsuario) {
		String sql = "select * from ONIX_USUARIOS u where u.id= :idUsr";

		Query query = adminEntidad.createNativeQuery(sql, OmsUsuario.class );
		query.setParameter("idUsr",idUsuario );
		List<OmsUsuario> resultado = query.getResultList();
		return resultado;
		}
	
	/**
	 * Obtener usuarios activos.
	 *
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsUsuario> obtenerUsuariosActivos() {
		String sql = "select * from ONIX_USUARIOS u where u.estado = 'A' order by u.id desc";

		Query query = adminEntidad.createNativeQuery(sql, OmsUsuario.class );
		
		List<OmsUsuario> resultado = query.getResultList();
		return resultado;
		}
	
	/**
	 * Buscar usuario.
	 *
	 * @param usuario the usuario
	 * @return the oms usuario
	 */
	@SuppressWarnings("unchecked")
	public OmsUsuario buscarUsuario(String usuario) {
		String sql = "select * from ONIX_USUARIOS u where u.usuario = :usuario";

		Query query = adminEntidad.createNativeQuery(sql, OmsUsuario.class );
		query.setParameter("usuario",usuario );
		List<OmsUsuario> resultado = query.getResultList();
		return resultado.isEmpty()?null:resultado.get(0);
		}  
	
	/**
	 * Actualiza datos usuario.
	 *
	 * @param usr the usr
	 */
	public void actualizaDatosUsuario(OmsUsuario usr){
		adminEntidad.merge(usr);
		adminEntidad.flush();
		adminEntidad.clear();
	}
	
	/**
	 * Cambio clave usuario.
	 *
	 * @param usuario the usuario
	 */
	public void cambioClaveUsuario(OmsUsuario usuario) 
	{
		String lsQuery = "update ONIX_USUARIOS  " + "set clave = ?, "
				+ "fecha_actualizacion = ?, esnuevo = ?, estado = ? " + "where id = ? ";
		Query query = adminEntidad.createNativeQuery(lsQuery);
		query.setParameter(1, usuario.getClave());
		query.setParameter(2, new Date());
		query.setParameter(3, usuario.getEsNuevo());
		query.setParameter(4, usuario.getEstado());
		query.setParameter(5, usuario.getId());
		query.executeUpdate();
	}

	/**
	 * Lista usuario por rol.
	 *
	 * @param pIdRol the id rol
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<OmsUsuario> listaUsuarioPorRol(Long pIdRol) {
		// TODO Auto-generated method stub
		return adminEntidad.createNativeQuery
				("select * from ONIX_USUARIOS u "
						+ "where u.id in ( "
						+ "select d.id_usuario from ONIX_USUARIOS_ROLES d "
						+ "where d.id_rol = :idRol "
						+ "and d.estado = 'A' ) and u.estado = 'A' ", OmsUsuario.class)
				.setParameter("idRol", pIdRol).getResultList();
	}
	
}
