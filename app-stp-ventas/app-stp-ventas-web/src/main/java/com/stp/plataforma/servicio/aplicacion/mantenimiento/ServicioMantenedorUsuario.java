package com.stp.plataforma.servicio.aplicacion.mantenimiento;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.librerias.generales.UtilEncriptarDataSource;
import com.stp.plataforma.dominio.aplicacion.OmsRole;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.dominio.aplicacion.OmsUsuariosRole;
import com.stp.plataforma.eao.aplicacion.OmsUsuarioEAO;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;

import librerias.eao.GenericEAO;
import librerias.exceptions.ErrorServicioNegocio;
import librerias.exceptions.ErrorValidacionGeneral;
import librerias.servicio.oyentes.AccionTransaccionalListener;
import librerias.servicio.oyentes.AccionValidacionListener;
import librerias.servicio.oyentes.AccionValidacionSimpleListener;
import librerias.servicio.oyentes.PreLlenadoRegistroListener;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorUsuario.
 */
@Stateless
public class ServicioMantenedorUsuario extends ServicioMantenedorControlAuditoria<OmsUsuarioEAO, OmsUsuario, Long> {

	/** The crud. */
	@EJB
	private OmsUsuarioEAO crud;

	/** The servicio rol. */
	@EJB
	private ServicioMantenedorRol servicioRol;

	/** The servicio usuario rol. */
	@EJB
	private ServicioMantenedorUsuarioRol servicioUsuarioRol;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected OmsUsuarioEAO getCrud() {
		return crud;
	}

	/**
	 * Lista roles.
	 *
	 * @return the list
	 */
	public List<OmsRole> listaRoles() {
		return servicioRol.listaObjetosActivos(OmsRole.class);
	}
	
	public List<OmsRole> listaRolesEmpresa() {
		
		String lQuery = "select b.* from ONIX_ROLES b where estado = 'A' and seleccionable = 'S' ";
		
		return servicioRol.ejecutarQueryNativoObjeto(lQuery, new HashMap<>(), OmsRole.class);
	}

	/**
	 * Obtener rol por ID.
	 *
	 * @param idRol the id rol
	 * @return the oms role
	 */
	public OmsRole obtenerRolPorID(Long idRol) {
		return servicioRol.buscarRolPorID(idRol);
	}

	/**
	 * Lista usuario por rol.
	 *
	 * @param pIdRol the id rol
	 * @return the list
	 */
	public List<OmsUsuario> listaUsuarioPorRol(Long pIdRol) {
		return crud.listaUsuarioPorRol(pIdRol);
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {

		addPostInsertListener(new AccionTransaccionalListener<OmsUsuario, Long>() {

			@Override
			public void controlTransaccional(OmsUsuario entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral {
				List<OmsUsuariosRole> listaUsuarioRoles = entidad.getPriUsuariosRoles();
				if (listaUsuarioRoles != null && !listaUsuarioRoles.isEmpty())
					for (OmsUsuariosRole usuariosRoleRol : listaUsuarioRoles) {
						usuariosRoleRol.setAuditoria(entidad.getAuditoria());
						servicioUsuarioRol.guardarActualizar(usuariosRoleRol);
					}

			}
		});

		addDatosRegistroUpdateListener(new PreLlenadoRegistroListener<OmsUsuario, Long>() {

			@Override
			public void preCargaDatosRegistro(OmsUsuario entidad) {
				
			}
		});

		addDatosRegistroInsertListener(new PreLlenadoRegistroListener<OmsUsuario, Long>() {
			@Override
			public void preCargaDatosRegistro(OmsUsuario entidad) {
				entidad.setEsNuevo(ES_NUEVO);
				entidad.setClave(CLAVE_DEFECTO);

			}

		});

		addValidacionSimpleInsertListener(new AccionValidacionSimpleListener<OmsUsuario, Long>() {

			@Override
			public void validacionDatos(OmsUsuario entidad) throws ErrorValidacionGeneral {
				if (entidad.getClave().length() < 8)
					throw new ErrorValidacionGeneral("La clave del usuario debe tener más de ocho caracteres");
				try {
					entidad.setClave(UtilEncriptarDataSource.encode(entidad.getClave()));
				} catch (Throwable e) {
					throw new ErrorValidacionGeneral("Eror al encriptar la clave");
				}
			}
		});

		addValidacionTransaccionalInsertListener(new AccionValidacionListener<OmsUsuario, Long>() {

			@Override
			public void validacionTransaccional(OmsUsuario entidad) throws ErrorServicioNegocio {
				OmsUsuario usuario = crud.buscarUsuario(entidad.getUsuario());
				if (usuario != null)
					throw new ErrorServicioNegocio(
							"El usuario " + usuario.getUsuario() + ", ya se encuentra registrado");

			}
		});

		addValidacionTransaccionalUpdateListener(new AccionValidacionListener<OmsUsuario, Long>() {

			@Override
			public void validacionTransaccional(OmsUsuario entidad) throws ErrorServicioNegocio {
				OmsUsuario usuario = crud.buscarUsuario(entidad.getUsuario());
				if (usuario != null && !entidad.getId().equals(usuario.getId()))
					throw new ErrorServicioNegocio(
							"El usuario " + usuario.getUsuario() + ", ya se encuentra registrado para otra persona");

			}

		});

	}

	/**
	 * Obtener roles asignados.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<String> obtenerRolesAsignados(Long id) {
		String lQuery = "select distinct b.ROL from ONIX_USUARIOS_ROLES a, ONIX_ROLES b " + "where a.ID_USUARIO = :idUsuario  "
				+ "and a.ESTADO = 'A' " + "and b.id = a.ID_ROL " + "and b.ESTADO = 'A'";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	/**
	 * Obtener roles por asignar.
	 *
	 * @param usuarioAutenticado the usuario autenticado
	 * @param id the id
	 * @return the list
	 */
	public List<String> obtenerRolesPorAsignar(String usuarioAutenticado, Long id) {
		/*String lQuery = "select distinct e.ROL from ONIX_USUARIOS_ROLES c, ONIX_ROLES e "
				+ "where c.ID_USUARIO in (select d.id from ONIX_USUARIOS d where d.usuario = :usuario and d.ESTADO = 'A') "
				+ "and c.estado = 'A' " + "and e.id = c.id_rol " + "and e.estado = 'A' " + "and  e.id not in ( "
				+ "select b.id from ONIX_USUARIOS_ROLES a, OMS_ROLES b " + "where a.ID_USUARIO = :idUsuario  "
				+  "and b.id = a.ID_ROL " + "and b.estado = 'A' " + ")";*/
		String lQuery = "select a.rol from ONIX_ROLES a where a.id not in  "
				+ "( select b.id from ONIX_USUARIOS_ROLES a, ONIX_ROLES b where a.ID_USUARIO = :idUsuario  "
				+ "and b.id = a.ID_ROL and b.estado = 'A' and a.estado = 'A' ) "
				+ "and a.estado = 'A' "
				+ "and a.seleccionable = 'S'";

		HashMap<String, Object> lParametros = new HashMap<>();
		//lParametros.put("usuario", usuarioAutenticado);
		lParametros.put("idUsuario", id);
		System.out.println("Usuario autenticado : " + usuarioAutenticado);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}
	
	public List<String> obtenerRolesPorAsignarEmpresa(String usuarioAutenticado, Long id) {
		/*String lQuery = "select distinct e.ROL from ONIX_USUARIOS_ROLES c, ONIX_ROLES e "
				+ "where c.ID_USUARIO in (select d.id from ONIX_USUARIOS d where d.usuario = :usuario and d.ESTADO = 'A') "
				+ "and c.estado = 'A' " + "and e.id = c.id_rol " + "and e.estado = 'A' " + "and  e.id not in ( "
				+ "select b.id from ONIX_USUARIOS_ROLES a, OMS_ROLES b " + "where a.ID_USUARIO = :idUsuario  "
				+  "and b.id = a.ID_ROL " + "and b.estado = 'A' " + ")";*/
		String lQuery = "select a.rol from ONIX_ROLES a where a.id not in  "
				+ "( select b.id from ONIX_USUARIOS_ROLES a, ONIX_ROLES b where a.ID_USUARIO = :idUsuario  "
				+ "and b.id = a.ID_ROL and b.estado = 'A' and a.estado = 'A' and b.ambito = 'E' ) "
				+ "and a.estado = 'A' "
				+ "and a.seleccionable = 'S'";

		HashMap<String, Object> lParametros = new HashMap<>();
		//lParametros.put("usuario", usuarioAutenticado);
		lParametros.put("idUsuario", id);
		System.out.println("Usuario autenticado : " + usuarioAutenticado);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	/**
	 * Asigar roles.
	 *
	 * @param lRolesTransferidas the l roles transferidas
	 * @param usuarioAutenticado the usuario autenticado
	 * @param id the id
	 * @param referencia the referencia
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public void asigarRoles(List<String> lRolesTransferidas, String usuarioAutenticado, Long id, Long referencia)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {
		for (String lRol : lRolesTransferidas) {

			OmsUsuariosRole lOmsUsuariosRole = servicioUsuarioRol.obtenerUsuarioRolPorRolUsuario(lRol, id);

			if (lOmsUsuariosRole != null) {
				lOmsUsuariosRole.setAuditoria(usuarioAutenticado);
				lOmsUsuariosRole.setFechaActualizacion(new Date());
				lOmsUsuariosRole.setEstado(GenericEAO.ESTADO_ACTIVO);
				lOmsUsuariosRole
						.setObservacion("ACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
			} else {
				OmsRole lOmsRol = servicioRol.obtenerObjetoPropiedad("rol", lRol, OmsRole.class);
				lOmsUsuariosRole = new OmsUsuariosRole();
				lOmsUsuariosRole.setEstado(GenericEAO.ESTADO_ACTIVO);
				lOmsUsuariosRole.setObservacion("REGISTRO DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				lOmsUsuariosRole.setFechaRegistro(new Date());
				lOmsUsuariosRole.setIdReferencia(referencia);
				lOmsUsuariosRole.setAuditoria(usuarioAutenticado);
				lOmsUsuariosRole.setPriUsuario(getCrud().find(id, OmsUsuario.class));
				lOmsUsuariosRole.setPriRole(lOmsRol);
			}
			servicioUsuarioRol.guardarActualizar(lOmsUsuariosRole);
		}

	}

	/**
	 * Permitir eliminar usuario rol.
	 *
	 * @param idUsuario the id usuario
	 * @return true, if successful
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public boolean permitirEliminarUsuarioRol(Long idUsuario) throws ErrorServicioNegocio, ErrorValidacionGeneral {
		return servicioUsuarioRol.obtenerListaUsuarioRolIdUsuario(idUsuario).size() > 1;
	}

	/**
	 * Inactivar roles.
	 *
	 * @param lRolesTransferidas the l roles transferidas
	 * @param usuarioAutenticado the usuario autenticado
	 * @param id the id
	 * @return the integer
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public Integer inactivarRoles(List<String> lRolesTransferidas, String usuarioAutenticado, Long id)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {
		Integer lResultado = 0;
		for (String lRol : lRolesTransferidas) {

			OmsUsuariosRole lOmsUsuariosRole = servicioUsuarioRol.obtenerUsuarioRolPorRolUsuario(lRol, id);
			if (!permitirEliminarUsuarioRol(id)) {
				lResultado = 1;
				break;
			}
			if (lOmsUsuariosRole != null) {
				lOmsUsuariosRole.setAuditoria(usuarioAutenticado);
				lOmsUsuariosRole.setFechaActualizacion(new Date());
				lOmsUsuariosRole.setEstado(GenericEAO.ESTADO_INACTIVO);
				lOmsUsuariosRole
						.setObservacion("INACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				servicioUsuarioRol.guardarActualizar(lOmsUsuariosRole);
			}
		}
		return lResultado;

	}

}
