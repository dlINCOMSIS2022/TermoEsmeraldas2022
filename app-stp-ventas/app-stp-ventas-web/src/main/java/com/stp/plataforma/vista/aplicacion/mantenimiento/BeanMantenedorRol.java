package com.stp.plataforma.vista.aplicacion.mantenimiento;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.menu.MenuModel;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsOpcionesRole;
import com.stp.plataforma.dominio.aplicacion.OmsRole;
import com.stp.plataforma.eao.aplicacion.OmsRoleEAO;
import com.stp.plataforma.servicio.aplicacion.mantenimiento.ServicioMantenedorRol;
import com.stp.plataforma.servicio.aplicacion.seguridad.ServicioAplicacion;
import com.stp.plataforma.vista.aplicacion.seguridad.TestParseMenu;

import librerias.exceptions.ErrorServicioNegocio;
import librerias.exceptions.ErrorValidacionGeneral;
import librerias.vista.JsfUtil;
import librerias.vista.anotaciones.ITestParseMenu;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.PostConstructListener;
import librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import librerias.vista.beans.oyentes.PostTransaccionListener;
import librerias.vista.beans.oyentes.ValidadorIngresoDatosListener;
import librerias.vista.exceptions.ErrorValidacionVisual;
import librerias.vista.interfaces.IGuardiaUsuarioSession;

// TODO: Auto-generated Javadoc
/**
 * The Class BeanMantenedorRol.
 */
@ManagedBean
@ViewScoped
public class BeanMantenedorRol extends BeanMantenedorGenerico<ServicioMantenedorRol, Long, OmsRole, OmsRoleEAO> {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The servicio mantenedor. */
	@EJB
	private ServicioMantenedorRol servicioMantenedor;

	/** The opciones seleccionadas. */
	private Long[] opcionesSeleccionadas;

	/** The lista opciones terminales. */
	private List<OmsOpcione> listaOpcionesTerminales;

	/** The lista seleccion opcion. */
	private DualListModel<String> listaSeleccionOpcion;
	
	/** The model. */
	private MenuModel model;
	
	/** The servicio aplicacion. */
	@EJB
	private ServicioAplicacion servicioAplicacion;
	
	/** The parse menu. */
	@Inject
	@ITestParseMenu
	private TestParseMenu parseMenu;

	/**
	 * Instantiates a new bean mantenedor rol.
	 */
	public BeanMantenedorRol() {
		super(OmsRole.class);
		listaSeleccionOpcion = new DualListModel<>();
		addValidacionListener(new ValidadorIngresoDatosListener() {

			@Override
			public void validarDatosIngreso() throws ErrorValidacionVisual {
				try {
					if (opcionesSeleccionadas != null && opcionesSeleccionadas.length != 0) {
						for (Long descripcionOpcion : opcionesSeleccionadas)
							entidadRegistrar.agregarOpciones(servicioMantenedor.obtenerOpcionPorID(descripcionOpcion));
					}

				} catch (Throwable e) {
					e.printStackTrace();
					throw new ErrorValidacionVisual("Imposible registrar los roles");
				}

			}
		});

		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				opcionesSeleccionadas = null;

			}
		});

		addPostConstructuListener(new PostConstructListener() {

			@Override
			public void metodoPostConstruct() {
				listaOpcionesTerminales = servicioMantenedor.listaOpcionesEjecutables(usuarioAutenticado());
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<OmsRole, Long>() {

			@Override
			public void postSeleccionRegistro(OmsRole pEntidadSeleccionada) {
				opcionesSeleccionadas = null;
				cargarOpcionesRoles(pEntidadSeleccionada);
				model = parseMenu.parseMenuOpciones(servicioAplicacion.obtenerMenu(pEntidadSeleccionada, IGuardiaUsuarioSession.TIPO_MENU), pEntidadSeleccionada.getRol());
			}

			
		});
	}

	/**
	 * Cargar opciones roles.
	 *
	 * @param pEntidadSeleccionada the entidad seleccionada
	 */
	private void cargarOpcionesRoles(OmsRole pEntidadSeleccionada) {
		List<String> listaOpcionesAsignadas = servicioMantenedor
				.obtenerOpcionesAsignadasRol(pEntidadSeleccionada.getId());
		List<String> listaOpcionesPorAsignar = servicioMantenedor
				.obtenerOpcionesPorAsignarRol(usuarioAutenticado(), pEntidadSeleccionada.getId(), obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION));
		listaSeleccionOpcion = new DualListModel<>(listaOpcionesPorAsignar, listaOpcionesAsignadas);
	}
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#getServicioMantenedor()
	 */
	protected ServicioMantenedorRol getServicioMantenedor() {
		return servicioMantenedor;
	}

	/**
	 * Gets the lista opciones terminales.
	 *
	 * @return the lista opciones terminales
	 */
	public List<OmsOpcione> getListaOpcionesTerminales() {
		return listaOpcionesTerminales;
	}

	/**
	 * Sets the lista opciones terminales.
	 *
	 * @param listaOpcionesTerminales the new lista opciones terminales
	 */
	public void setListaOpcionesTerminales(List<OmsOpcione> listaOpcionesTerminales) {
		this.listaOpcionesTerminales = listaOpcionesTerminales;
	}

	/**
	 * Gets the opciones seleccionadas.
	 *
	 * @return the opciones seleccionadas
	 */
	public Long[] getOpcionesSeleccionadas() {
		return opcionesSeleccionadas;
	}

	/**
	 * Sets the opciones seleccionadas.
	 *
	 * @param opcionesSeleccionadas the new opciones seleccionadas
	 */
	public void setOpcionesSeleccionadas(Long[] opcionesSeleccionadas) {
		this.opcionesSeleccionadas = opcionesSeleccionadas;
	}

	/**
	 * Obtener opciones asignadas rol.
	 *
	 * @param opcionesRoles the opciones roles
	 * @return the list
	 */
	public List<OmsOpcione> obtenerOpcionesAsignadasRol(List<OmsOpcionesRole> opcionesRoles) {
		List<OmsOpcione> opcionesRolesAsig = new ArrayList<>();
		for (OmsOpcionesRole opcionRol : opcionesRoles) {
			if (opcionRol.getPriOpcione().getAccion() != null) {
				opcionRol.getPriOpcione().setAuditoria(opcionRol.getAuditoria());
				opcionesRolesAsig.add(opcionRol.getPriOpcione());
			}
		}
		return opcionesRolesAsig;
	}

	/**
	 * Control transferencia.
	 *
	 * @param pEvento the evento
	 */
	public void controlTransferencia(TransferEvent pEvento) {

		Long referencia = obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION);
		List<String> lOpcionesTransferidas = (List<String>) pEvento.getItems();
		try {
			if (pEvento.isAdd())

				servicioMantenedor.asigarOpciones(lOpcionesTransferidas, usuarioAutenticado(), entidadRegistrar.getId(),
						referencia);

			else {
				if (pEvento.isRemove())
					servicioMantenedor.inactivarOpciones(lOpcionesTransferidas, usuarioAutenticado(),
							entidadRegistrar.getId());
			}
			cargarOpcionesRoles(this.entidadRegistrar);
		} catch (ErrorServicioNegocio e) {
			e.printStackTrace();
			addError(e.getMessage());

		} catch (ErrorValidacionGeneral e) {
			e.printStackTrace();
			addError(e.getMessage());
		}

	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Roles");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Roles");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite los Roles");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Rol");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Roles registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización Rol");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Rol");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	/**
	 * Gets the lista seleccion opcion.
	 *
	 * @return the lista seleccion opcion
	 */
	public DualListModel<String> getListaSeleccionOpcion() {
		return listaSeleccionOpcion;
	}

	/**
	 * Sets the lista seleccion opcion.
	 *
	 * @param listaSeleccionOpcion the new lista seleccion opcion
	 */
	public void setListaSeleccionOpcion(DualListModel<String> listaSeleccionOpcion) {
		this.listaSeleccionOpcion = listaSeleccionOpcion;
	}
	
	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public MenuModel getModel() {
		return model;
	}
	
	
	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(MenuModel model) {
		this.model = model;
	}
}
