package com.stp.plataforma.servicio.aplicacion.mantenimiento;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.eao.aplicacion.OmsOpcioneEAO;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;

import librerias.exceptions.ErrorServicioNegocio;
import librerias.servicio.oyentes.AccionValidacionListener;

// TODO: Auto-generated Javadoc
/**
 * The Class ServicioMantenedorOpcion.
 */
@Stateless
public class ServicioMantenedorOpcion extends ServicioMantenedorControlAuditoria<OmsOpcioneEAO, OmsOpcione, Long> {

	/** The crud. */
	@EJB
	private OmsOpcioneEAO crud;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#getCrud()
	 */
	protected OmsOpcioneEAO getCrud() {
		return crud;
	}

	/**
	 * Lista opciones ejecutables.
	 *
	 * @param pUsuario the usuario
	 * @return the list
	 */
	public List<OmsOpcione> listaOpcionesEjecutables(String pUsuario) {
		return crud.listaOpcionesTerminales(pUsuario);
	}

	/**
	 * Lista opciones padre.
	 *
	 * @param lReferencia the l referencia
	 * @return the list
	 */
	public List<OmsOpcione> listaOpcionesPadre(Long lReferencia) {
		HashMap<String, Object> listaParametros = new HashMap<>();
		listaParametros.put("referencia", lReferencia);
		return crud.ejecutarQueryNativo(
				"select g.* from ONIX_OPCIONES g where g.id_referencia = :referencia and g.modulo_padre is null",
				listaParametros, OmsOpcione.class);
	}
	
	/**
	 * Lista opciones submenu.
	 *
	 * @param lReferencia the l referencia
	 * @return the list
	 */
	public List<OmsOpcione> listaOpcionesSubmenu(Long lReferencia) {
		HashMap<String, Object> listaParametros = new HashMap<>();
		listaParametros.put("referencia", lReferencia);
		return crud.ejecutarQueryNativo(
				"select g.* from ONIX_OPCIONES g where g.id_referencia = :referencia  and g.accion is null",
				listaParametros, OmsOpcione.class);
	}
	
	/**
	 * Lista opciones.
	 *
	 * @param lReferencia the l referencia
	 * @return the list
	 */
	public List<OmsOpcione> listaOpciones(Long lReferencia) {
		HashMap<String, Object> listaParametros = new HashMap<>();
		listaParametros.put("referencia", lReferencia);
		return crud.ejecutarQueryNativo(
				"select g.* from ONIX_OPCIONES g where g.id_referencia = :referencia",
				listaParametros, OmsOpcione.class);
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.servicio.ServicioMantenimientoEntidad#cargarConfiguracionServicio()
	 */
	@Override
	protected void cargarConfiguracionServicio() {
		addValidacionTransaccionalInsertListener(new AccionValidacionListener<OmsOpcione, Long>() {

			@Override
			public void validacionTransaccional(OmsOpcione entidad) throws ErrorServicioNegocio {
				OmsOpcione opcion = crud.buscarOpcionPorNombre(entidad.getDescripcion());
				if (opcion != null)
					throw new ErrorServicioNegocio(
							"La opción " + opcion.getDescripcion() + ", ya se encuentra registrado");
			}
		});

		addValidacionTransaccionalUpdateListener(new AccionValidacionListener<OmsOpcione, Long>() {

			@Override
			public void validacionTransaccional(OmsOpcione entidad) throws ErrorServicioNegocio {
				OmsOpcione opcion = crud.buscarOpcionPorNombre(entidad.getDescripcion());
				if (opcion != null && !entidad.getId().equals(opcion.getId()))
					throw new ErrorServicioNegocio(
							"La opción " + opcion.getDescripcion() + ", ya se encuentra registrado para otra persona");
			}
		});
	}

}
