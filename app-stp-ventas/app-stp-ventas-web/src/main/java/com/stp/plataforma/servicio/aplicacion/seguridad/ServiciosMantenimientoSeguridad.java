package com.stp.plataforma.servicio.aplicacion.seguridad;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsOpcionesRole;
import com.stp.plataforma.dominio.aplicacion.OmsRole;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.eao.aplicacion.OmsOpcioneEAO;
import com.stp.plataforma.eao.aplicacion.OmsOpcionesRoleEAO;
import com.stp.plataforma.eao.aplicacion.OmsRoleEAO;
import com.stp.plataforma.eao.aplicacion.OmsUsuarioEAO;
import com.stp.plataforma.eao.aplicacion.OmsUsuariosRoleEAO;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiciosMantenimientoSeguridad.
 */
@Stateless
public class ServiciosMantenimientoSeguridad {
	
	/** The usuario EAO. */
	@EJB
	private OmsUsuarioEAO usuarioEAO;
	
	/** The rol EAO. */
	@EJB
	private OmsRoleEAO rolEAO;
	
	/** The opcion EAO. */
	@EJB
	private OmsOpcioneEAO opcionEAO;
	
	/** The usuario rol EAO. */
	@EJB
	private OmsUsuariosRoleEAO usuarioRolEAO;
	
	/** The roles opcion EAO. */
	@EJB
	private OmsOpcionesRoleEAO rolesOpcionEAO;

	/**
	 * Obtener opciones por usuario.
	 *
	 * @param usuario the usuario
	 * @return the list
	 */
	public List<OmsOpcione> obtenerOpcionesPorUsuario(String usuario) {
		return opcionEAO.obtieneOpcionesPorUsuario(usuario);
	}

	

	/**
	 * Lista roles activos.
	 *
	 * @return the list
	 */
	public List<OmsRole> listaRolesActivos() {
		return rolEAO.listaRolesActivos();
	}

	/**
	 * Listar roles disponibles.
	 *
	 * @return the list
	 */
	public List<OmsRole> listarRolesDisponibles() {
		return rolEAO.listarRolesDisponibles();
	}

	/**
	 * Lista opciones terminales.
	 *
	 * @param pUsuario the usuario
	 * @return the list
	 */
	public List<OmsOpcione> listaOpcionesTerminales(String pUsuario) {
		return opcionEAO.listaOpcionesTerminales(pUsuario);
	}

	/**
	 * Lista opciones asignada.
	 *
	 * @param rol the rol
	 * @return the list
	 */
	public List<OmsOpcione> listaOpcionesAsignada(String rol) {
		return opcionEAO.listaOpcionesAsignada(rol);
	}

	/**
	 * Obtener opcion rol.
	 *
	 * @param idOpcion the id opcion
	 * @param idRol the id rol
	 * @return the oms opciones role
	 */
	public OmsOpcionesRole obtenerOpcionRol(Integer idOpcion, Integer idRol) {
		return rolesOpcionEAO.obtenerOpcionRol(idOpcion, idRol);
	}

	/**
	 * Actualizar opciones rol.
	 *
	 * @param estado the estado
	 * @param idOpRol the id op rol
	 */
	public void actualizarOpcionesRol(String estado, Integer idOpRol) {
		rolesOpcionEAO.actualizarOpcionesRol(estado, idOpRol);
	}

	/**
	 * Ingresar opcion rol.
	 *
	 * @param opcRol the opc rol
	 */
	public void ingresarOpcionRol(OmsOpcionesRole opcRol) {
		rolesOpcionEAO.ingresarOpcionRol(opcRol);
	}

	/**
	 * Obtener opcion padre.
	 *
	 * @param opcion the opcion
	 * @return the oms opcione
	 */
	public OmsOpcione obtenerOpcionPadre(Integer opcion) {
		return opcionEAO.obtenerOpcionPadre(opcion);
	}

	/**
	 * Cantidad opciones por padre.
	 *
	 * @param rol the rol
	 * @param opcion the opcion
	 * @return the integer
	 */
	public Integer cantidadOpcionesPorPadre(Integer rol, Integer opcion) {
		return opcionEAO.cantidadOpcionesPorPadre(rol, opcion);
	}

	/**
	 * Guardar rol.
	 *
	 * @param rol the rol
	 */
	public void guardarRol(OmsRole rol) {
		rolEAO.insertar(rol);
	}

	/**
	 * Actualizar rol.
	 *
	 * @param rol the rol
	 * @return the oms role
	 */
	public OmsRole actualizarRol(OmsRole rol) {
		return rolEAO.actualizar(rol);
	}

	/**
	 * Crear usuario.
	 *
	 * @param usuario the usuario
	 */
	public void crearUsuario(OmsUsuario usuario) {
		usuarioEAO.insertar(usuario);
	}

	/**
	 * Cod rol asesor.
	 *
	 * @return the integer
	 */
	public Integer codRolAsesor() {
		return rolEAO.codRolAsesor();
	}
	
	/**
	 * Obtener usuario tot.
	 *
	 * @param usuario the usuario
	 * @return the oms usuario
	 */
	public OmsUsuario obtenerUsuarioTot(String usuario) {
		return usuarioEAO.buscarUsuario(usuario);
	}
	
	/**
	 * Cambio clave usuario.
	 *
	 * @param usuario the usuario
	 * @throws Throwable the throwable
	 */
	public void cambioClaveUsuario(OmsUsuario usuario) throws Throwable {
		usuarioEAO.cambioClaveUsuario(usuario);
	}


}
