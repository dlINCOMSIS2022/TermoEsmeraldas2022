package com.stp.plataforma.servicio.aplicacion.mantenimiento;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.dominio.aplicacion.OmsAccesosDirecto;
import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.eao.aplicacion.OmsAccesosDirectoEAO;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;

import librerias.eao.GenericEAO;
import librerias.exceptions.ErrorServicioNegocio;
import librerias.exceptions.ErrorValidacionGeneral;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorAccesoDirecto.
 */
@Stateless
public class ServicioMantenedorAccesoDirecto
		extends ServicioMantenedorControlAuditoria<OmsAccesosDirectoEAO, OmsAccesosDirecto, Long> {
	
	/** The crud. */
	@EJB
	private OmsAccesosDirectoEAO crud;

	/** The l servicio usuario. */
	@EJB
	private ServicioMantenedorUsuario lServicioUsuario;
	
	/** The l servicio opcion. */
	@EJB
	private ServicioMantenedorOpcion lServicioOpcion;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {
		System.out.println("No existen configuraciones del servicios");

	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	@Override
	protected OmsAccesosDirectoEAO getCrud() {
		return crud;
	}

	/**
	 * Obtener accesos asignados activos.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<String> obtenerAccesosAsignadosActivos(Long id) {

		String lQuery = "select (select opcion from ONIX_OPCIONES where id = id_opcion ) opcion  "
				+ "from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario  and estado = 'A'";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}
	
	/**
	 * Obtener accesos directos usuario.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<OmsAccesosDirecto> obtenerAccesosDirectosUsuario(Long id) {

		String lQuery = "select * "
				+ "from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario  and estado = 'A'";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarQueryNativo(lQuery, lParametros, OmsAccesosDirecto.class);
	}
	
	/**
	 * Obtener accesos por asignar.
	 *
	 * @param id the id
	 * @return the list
	 */
	public List<String> obtenerAccesosPorAsignar(Long id) {
		String lQuery = "select opcion from ONIX_OPCIONES pOpciones  " + "where pOpciones.id in ( "
				+ "select id_opcion from ONIX_OPCIONES_ROLES rDato where rDato.id_rol in ( "
				+ "select id_rol from ONIX_USUARIOS_ROLES tDato " + "where tDato.id_usuario = :idUsuario"
				+ "	and tDato.estado = 'A' ) " + "and rDato.estado = 'A' ) " + "and pOpciones.estado = 'A' "
				+ "and pOpciones.accion is not null " + "and pOpciones.id not in ( "
				+ "select id_opcion from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario  and estado = 'A' )";

		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);
		return crud.ejecutarNativeQueryList(lQuery, lParametros, String.class);
	}

	/**
	 * Asigar acceso.
	 *
	 * @param lAccesosSeleccionados the l accesos seleccionados
	 * @param id the id
	 * @param referencia the referencia
	 * @param usuarioAutenticado the usuario autenticado
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public void asigarAcceso(List<String> lAccesosSeleccionados, Long id, Long referencia, String usuarioAutenticado) throws ErrorServicioNegocio, ErrorValidacionGeneral
	{

		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);

		for (String opcion : lAccesosSeleccionados) {
			lParametros.put("opcion", opcion);
			List<OmsAccesosDirecto> lAccesoBase = crud.ejecutarQueryNativo(
					"select * from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario "
							+ "and id_opcion in (select id from ONIX_OPCIONES where opcion = :opcion )",
					lParametros, OmsAccesosDirecto.class);
			OmsAccesosDirecto lAcceso = null;
			if (!lAccesoBase.isEmpty()) {
				lAcceso = lAccesoBase.get(0);
				lAcceso.setAuditoria(usuarioAutenticado);
				lAcceso.setFechaActualizacion(new Date());
				lAcceso.setEstado(GenericEAO.ESTADO_ACTIVO);
				lAcceso.setObservacion("ACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
			} else {
				lAcceso = new OmsAccesosDirecto();
				lAcceso.setAuditoria(usuarioAutenticado);
				lAcceso.setEstado(GenericEAO.ESTADO_ACTIVO);
				lAcceso.setFechaRegistro(new Date());
				lAcceso.setIdReferencia(referencia);
				lAcceso.setObservacion("REGISTRO DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				lAcceso.setPriOpcione(lServicioOpcion.obtenerObjetoPropiedad("opcion", opcion, OmsOpcione.class));
				lAcceso.setPriUsuario(lServicioUsuario.obtenerObjetoPropiedad("usuario", usuarioAutenticado, OmsUsuario.class));
				
			}
			guardarActualizar(lAcceso);
		}

	}

	/**
	 * Inactivar acceso.
	 *
	 * @param lAccesosSeleccionados the l accesos seleccionados
	 * @param id the id
	 * @param usuarioAutenticado the usuario autenticado
	 * @throws ErrorServicioNegocio the error servicio negocio
	 * @throws ErrorValidacionGeneral the error validacion general
	 */
	public void inactivarAcceso(List<String> lAccesosSeleccionados, Long id, String usuarioAutenticado)throws ErrorServicioNegocio, ErrorValidacionGeneral 
	{
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idUsuario", id);

		for (String opcion : lAccesosSeleccionados) {
			lParametros.put("opcion", opcion);
			List<OmsAccesosDirecto> lAccesoBase = crud.ejecutarQueryNativo(
					"select * from ONIX_ACCESOS_DIRECTOS where id_usuario = :idUsuario "
							+ "and id_opcion in (select id from ONIX_OPCIONES where opcion = :opcion )",
					lParametros, OmsAccesosDirecto.class);
			if (!lAccesoBase.isEmpty())
			{
				OmsAccesosDirecto lAcceso = lAccesoBase.get(0);			
				lAcceso.setAuditoria(usuarioAutenticado);
				lAcceso.setFechaActualizacion(new Date());
				lAcceso.setEstado(GenericEAO.ESTADO_INACTIVO);
				lAcceso.setObservacion("INACTIVACION DE OPCION DESDE LA WEB POR EL USUARIO " + usuarioAutenticado);
				guardarActualizar(lAcceso);
			}
		}
		
	}

}
