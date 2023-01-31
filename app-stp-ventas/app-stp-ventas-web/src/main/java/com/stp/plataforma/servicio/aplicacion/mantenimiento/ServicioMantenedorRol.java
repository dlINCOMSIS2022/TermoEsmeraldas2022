package com.stp.plataforma.servicio.aplicacion.mantenimiento;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsOpcionesRole;
import com.stp.plataforma.dominio.aplicacion.OmsRole;
import com.stp.plataforma.eao.aplicacion.OmsRoleEAO;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;

import librerias.eao.GenericEAO;
import librerias.exceptions.ErrorServicioNegocio;
import librerias.exceptions.ErrorValidacionGeneral;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorRol.
 */
@Stateless
public class ServicioMantenedorRol extends ServicioMantenedorControlAuditoria<OmsRoleEAO, OmsRole, Long> {

	/** The crud. */
	@EJB
	private OmsRoleEAO crud;

	/** The servicio opcion. */
	@EJB
	private ServicioMantenedorOpcion servicioOpcion;

	/** The servicio opcion rol. */
	@EJB
	private ServicioMantenedorOpcionRol servicioOpcionRol;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	@Override
	protected OmsRoleEAO getCrud() {
		return crud;
	}

	/**
	 * Lista opciones ejecutables.
	 *
	 * @param pUsuario the usuario
	 * @return the list
	 */
	public List<OmsOpcione> listaOpcionesEjecutables(String pUsuario) {
		return servicioOpcion.listaOpcionesEjecutables(pUsuario);
	}

	/**
	 * Registrar padres.
	 *
	 * @param rol the rol
	 * @param opcionRol the opcion rol
	 * @return the oms opciones role
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	private OmsOpcionesRole registrarPadres(OmsRole rol, OmsOpcione opcionRol)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {
		OmsOpcionesRole opcionRolPdre = new OmsOpcionesRole();
		if (opcionRol != null) {
			opcionRolPdre.setAuditoria(rol.getAuditoria());
			opcionRolPdre
					.setObservacion("DESCRIPCION ASIGNACION: " + rol.getRol() + " - " + opcionRol.getDescripcion());
			opcionRolPdre.setEstado("A");
			opcionRolPdre.setFechaRegistro(new Date());
			opcionRolPdre.setIdRol(rol);
			opcionRolPdre.setPriOpcione(opcionRol);
			if (!existeRolOpcion(opcionRolPdre)) {
				servicioOpcionRol.guardarActualizar(opcionRolPdre);
			}
			return registrarPadres(rol, opcionRol.getModuloPadre());
		} else {

			return null;
		}
	}

	/**
	 * Existe rol opcion.
	 *
	 * @param opcionRolPdre the opcion rol pdre
	 * @return true, if successful
	 */
	private boolean existeRolOpcion(OmsOpcionesRole opcionRolPdre) {
		return getCrud().verificarSiExisteRolOpcion(opcionRolPdre.getIdRol().getId(),
				opcionRolPdre.getPriOpcione().getId());
	}

	/**
	 * Obtener opcion por ID.
	 *
	 * @param idOpcion the id opcion
	 * @return the oms opcione
	 */
	public OmsOpcione obtenerOpcionPorID(Long idOpcion) {
		return servicioOpcion.obtenerObjtoPK(idOpcion, OmsOpcione.class);
	}

	/**
	 * Lista opciones ejecutables.
	 *
	 * @param pUsuario the usuario
	 * @param tipo the tipo
	 * @return the list
	 */
	public List<OmsOpcione> listaOpcionesEjecutables(String pUsuario, String tipo) {

		return servicioOpcionRol.listaOpcionesEjecutablesRol(pUsuario, tipo);
	}

	/**
	 * Buscar rol por ID.
	 *
	 * @param idRol the id rol
	 * @return the oms role
	 */
	public OmsRole buscarRolPorID(Long idRol) {
		return crud.obtenerObjetoPorPk(idRol, OmsRole.class);
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {

		addValidacionSimpleUpdateListener(entidad -> {
			if (entidad.getRol().length() < 3)
				throw new ErrorValidacionGeneral("La rol debe tener más de tres caracteres");
		});

		addValidacionSimpleInsertListener(entidad -> {
			if (entidad.getRol().length() < 3)
				throw new ErrorValidacionGeneral("La rol debe tener más de tres caracteres");

		});

		addValidacionTransaccionalInsertListener(entidad -> {
			OmsRole rol = crud.obtenerObjetoPorCampoGenerico("rol", entidad.getRol(), OmsRole.class);
			if (rol != null)
				throw new ErrorServicioNegocio("El rol " + entidad.getRol() + ", ya se encuentra registrado");

		});

		addValidacionTransaccionalUpdateListener(entidad -> {
			OmsRole rol = crud.obtenerObjetoPorCampoGenerico("rol", entidad.getRol(), OmsRole.class);
			if (rol != null && !entidad.getId().equals(rol.getId()))
				throw new ErrorServicioNegocio("El rol " + entidad.getRol() + ", ya se encuentra registrado");

		});

		addPostInsertListener(entidad -> {
			List<OmsOpcionesRole> listaOpcionesRoles = entidad.getListaOpcionesRoles();
			if (listaOpcionesRoles != null && !listaOpcionesRoles.isEmpty())
				for (OmsOpcionesRole opcionRol : listaOpcionesRoles) {
					opcionRol.setAuditoria(entidad.getAuditoria());
					servicioOpcionRol.guardarActualizar(opcionRol);
					registrarPadres(entidad, opcionRol.getPriOpcione().getModuloPadre());
				}
		});
	}

	/**
	 * Obtener opciones asignadas rol.
	 *
	 * @param lRol the l rol
	 * @return the list
	 */
	public List<String> obtenerOpcionesAsignadasRol(Long lRol) {
		String lQuery = "select g.opcion  from ONIX_OPCIONES g " + "where g.id in ( "
				+ "select c.id_opcion from ONIX_OPCIONES_ROLES c " + "where c.id_rol = :pRol and c.estado = 'A' ) "
				+ "and g.estado = 'A' " + "and g.modulo_padre is not null and g.accion is not null";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("pRol", lRol);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	/**
	 * Obtener opciones por asignar rol.
	 *
	 * @param lUsuario the l usuario
	 * @param lRol the l rol
	 * @param idReferencia the id referencia
	 * @return the list
	 */
	public List<String> obtenerOpcionesPorAsignarRol(String lUsuario, Long lRol, Long idReferencia) {

		String lQuery = "select g.opcion  from ONIX_OPCIONES g " + "where g.id in ( "
				+ "select c.id_opcion from ONIX_OPCIONES_ROLES c " + "where c.id_rol in ( " + "select a.id_rol  "
				+ "from ONIX_USUARIOS_ROLES a,  ONIX_USUARIOS b " + "where a.id_usuario = b.id "
				+ "and b.usuario = :pUsuario " + "and a.estado = 'A' " + "and b.estado = 'A' ) "
				+ "and c.estado = 'A' ) " + "and g.estado = 'A' " + "and g.modulo_padre is not null "
				+ "and g.id not in ( " + "select c.id_opcion from ONIX_OPCIONES_ROLES c " + "where c.id_rol = :pRol "
				+ "and c.estado = 'A' ) union " + "select a.descripcion  from ONIX_OPCIONES a  "
				+ "where a.id_referencia = :referencia " + "and a.accion is not null  "
				+ "and a.id not in ( select c.id_opcion from ONIX_OPCIONES_ROLES c  where c.id_rol = :pRol "
				+ "and c.estado = 'A' )";

		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("pUsuario", lUsuario);
		lParametros.put("pRol", lRol);
		lParametros.put("referencia", idReferencia);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	/**
	 * Asigar opciones.
	 *
	 * @param lOpcionesTransferidas the l opciones transferidas
	 * @param lUsuario the l usuario
	 * @param pIdRol the id rol
	 * @param pReferencia the referencia
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public void asigarOpciones(List<String> lOpcionesTransferidas, String lUsuario, Long pIdRol, Long pReferencia)
			throws ErrorServicioNegocio, ErrorValidacionGeneral

	{
		for (String lOpcion : lOpcionesTransferidas) {
			asignarOpcionRol(lUsuario, pIdRol, pReferencia, lOpcion,
					servicioOpcion.obtenerObjetoPropiedad("opcion", lOpcion, OmsOpcione.class));
		}
	}

	/**
	 * Asignar opcion rol.
	 *
	 * @param lUsuario the l usuario
	 * @param pIdRol the id rol
	 * @param pReferencia the referencia
	 * @param lOpcion the l opcion
	 * @param lOmsOpcion the l oms opcion
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	private void asignarOpcionRol(String lUsuario, Long pIdRol, Long pReferencia, String lOpcion, OmsOpcione lOmsOpcion)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {

		OmsOpcionesRole lOmsOpcionRol = servicioOpcionRol.obtenerOpcionRolPorOpcionRol(lOpcion, pIdRol);
		if (lOmsOpcionRol != null) {
			if (lOmsOpcionRol.getEstado().equals(GenericEAO.ESTADO_INACTIVO)) {
				lOmsOpcionRol.setAuditoria(lUsuario);
				lOmsOpcionRol.setFechaActualizacion(new Date());
				lOmsOpcionRol.setEstado(GenericEAO.ESTADO_ACTIVO);
				lOmsOpcionRol.setObservacion("ACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + lUsuario);
				servicioOpcionRol.guardarActualizar(lOmsOpcionRol);
			}
		} else {
			lOmsOpcionRol = new OmsOpcionesRole();
			lOmsOpcionRol.setEstado(GenericEAO.ESTADO_ACTIVO);
			lOmsOpcionRol.setObservacion("REGISTRO DE OPCION DESDE LA WEB POR EL USUARIO " + lUsuario);
			lOmsOpcionRol.setFechaRegistro(new Date());
			lOmsOpcionRol.setIdReferencia(pReferencia);
			lOmsOpcionRol.setAuditoria(lUsuario);
			lOmsOpcionRol.setIdRol(getCrud().find(pIdRol, OmsRole.class));
			lOmsOpcionRol.setPriOpcione(lOmsOpcion);
			servicioOpcionRol.guardarActualizar(lOmsOpcionRol);
		}

		if (lOmsOpcion.getModuloPadre() == null)
			return;
		else
			asignarOpcionRol(lUsuario, pIdRol, pReferencia, lOmsOpcion.getModuloPadre().getOpcion(),
					servicioOpcion.obtenerObjtoPK(lOmsOpcion.getModuloPadre().getId(), OmsOpcione.class));

	}

	/**
	 * Inactivar opciones.
	 *
	 * @param lOpcionesTransferidas the l opciones transferidas
	 * @param lUsuario the l usuario
	 * @param pIdRol the id rol
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public void inactivarOpciones(List<String> lOpcionesTransferidas, String lUsuario, Long pIdRol)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {
		for (String lOpcion : lOpcionesTransferidas)
			inactivarOpcionesRol(lUsuario, servicioOpcion.obtenerObjetoPropiedad("opcion", lOpcion, OmsOpcione.class),
					pIdRol);

	}

	/**
	 * Inactivar opciones rol.
	 *
	 * @param lUsuario the l usuario
	 * @param lOmsOpcion the l oms opcion
	 * @param pIdRol the id rol
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	private void inactivarOpcionesRol(String lUsuario, OmsOpcione lOmsOpcion, Long pIdRol)
			throws ErrorServicioNegocio, ErrorValidacionGeneral {
		OmsOpcionesRole lOmsOpcionRol = servicioOpcionRol.obtenerOpcionRolPorOpcionRol(lOmsOpcion.getOpcion(), pIdRol);
		if (lOmsOpcionRol != null && lOmsOpcionRol.getEstado().equals(GenericEAO.ESTADO_ACTIVO)) {
			lOmsOpcionRol.setAuditoria(lUsuario);
			lOmsOpcionRol.setFechaActualizacion(new Date());
			lOmsOpcionRol.setEstado(GenericEAO.ESTADO_INACTIVO);
			lOmsOpcionRol.setObservacion("INACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + lUsuario);
			servicioOpcionRol.guardarActualizar(lOmsOpcionRol);
		}
		if (lOmsOpcion.getModuloPadre() == null)
			return;
		else
			inactivarOpcionesRol(lUsuario,
					servicioOpcion.obtenerObjtoPK(lOmsOpcion.getModuloPadre().getId(), OmsOpcione.class), pIdRol);
	}

}
