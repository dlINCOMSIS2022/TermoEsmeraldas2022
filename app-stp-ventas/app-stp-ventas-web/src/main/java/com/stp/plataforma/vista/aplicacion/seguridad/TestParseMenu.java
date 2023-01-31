package com.stp.plataforma.vista.aplicacion.seguridad;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuElement;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.servicio.aplicacion.seguridad.ServicioAplicacion;

import librerias.vista.JsfUtil;
import librerias.vista.anotaciones.ITestParseMenu;
import librerias.vista.anotaciones.ITestUsuarioSession;
import librerias.vista.interfaces.IGuardiaUsuarioSession;
import librerias.vista.interfaces.IParseMenu;
import librerias.vista.interfaces.IUsuarioSession;

// TODO: Auto-generated Javadoc
/**
 * The Class TestParseMenu.
 */
@ITestParseMenu
@SessionScoped
@Named("testParseMenu")
public class TestParseMenu implements IParseMenu<OmsOpcione, DefaultMenuModel>,
		ActionListener, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The servicio aplicacion. */
	@EJB
	private ServicioAplicacion servicioAplicacion;

	/** The parametro obtener opciones. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<OmsUsuario> parametroObtenerOpciones;

	private OmsOpcione opcionSeleccionada;
	private String lPaginaPrincipal;
	
	
	@PostConstruct
	public void inicializar()
	{
		lPaginaPrincipal = JsfUtil.getContextoJSF().getExternalContext().getApplicationContextPath() + IGuardiaUsuarioSession.PAGINA_PRINCIPAL;
	}
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IParseMenu#parseMenuOpciones(java.util.List)
	 */
	public DefaultMenuModel parseMenuOpciones(List<OmsOpcione> opciones) {
		MenuModel model = new DefaultMenuModel();

		DefaultMenuItem menItm = new DefaultMenuItem("Principal");
		menItm.setIcon("fa fa-home");//fa-home
		menItm.setTitle("Pagina Principal");
		menItm.setUrl(lPaginaPrincipal);
		menItm.setPartialSubmit(true);
		menItm.setProcess("@this");
		//menItm.setContainerStyleClass("layout-menubar-active");
		
		
		model.addElement(menItm);
		
		model.addElement(new org.primefaces.model.menu.DefaultSeparator() );

		
		for (OmsOpcione opcion : opciones) {
			DefaultSubMenu submenu = new DefaultSubMenu(opcion.getOpcion());
			// submenu.setLabel(opcion.getOpcion());
			System.out.println(opcion.getOpcion()
					+ "----------------------------");
			submenu.setId("OPTH" + opcion.getId());
			submenu.setIcon(opcion.getRutaimagen());
			Iterator<OmsOpcione> opcionesHijas = opcion.getOpcionesHijas()
					.iterator();

			while (opcionesHijas.hasNext()) {
				OmsOpcione opcionHija = opcionesHijas.next();

				// Solo si hay permisos para esa opcion
				if (servicioAplicacion.verificarPermisoOpcion(opcionHija,
						parametroObtenerOpciones.getUsuarioSession())) {
					if (opcionHija.getEstado().equals("A"))
						 {
						submenu.getElements().add(getMenuHija(opcionHija));
					}
				}
			}
			model.addElement(submenu);
			model.addElement(new org.primefaces.model.menu.DefaultSeparator() );
		}

		return (DefaultMenuModel) model;
	}

	/**
	 * Gets the menu hija.
	 *
	 * @param opcionPadre the opcion padre
	 * @return the menu hija
	 */
	private MenuElement getMenuHija(OmsOpcione opcionPadre) {

		if (opcionPadre.getOpcionesHijas() == null
				|| opcionPadre.getOpcionesHijas().size() < 1) {

			DefaultMenuItem menItm = new DefaultMenuItem(
					opcionPadre.getOpcion());
			// menItm.setValue(opcionPadre.getOpcion());
			menItm.setIcon(opcionPadre.getRutaimagen());
			// menItm.setAjax(true);
			menItm.setTitle(opcionPadre.getDescripcion());
			// menItm.setOncomplete("showDialogProceso("+opcionPadre.getOnComplete()+");");

			// menItm.getAttributes().put("cfOpcion", opcionPadre);
//
//			if (opcionPadre.getTipo().equals("P")) {
//				// menItm.setUpdate(":MAIN_TARGET");
//			} else {
//				menItm.setOnclick("pantalla.show()");
//			}
			// menItm.setImmediate(true);
			// menItm.setUrl(opcionPadre.getAccion());
//			menItm.setCommand("#{verticalMenu.navegarUrl('"
//					+  + "')}");
			System.out.println("Accion " + opcionPadre.getAccion());
			//menItm.setUrl(JsfUtil.getContextoJSF().getExternalContext().getApplicationContextPath() + opcionPadre.getAccion());
			menItm.setPartialSubmit(true);
			menItm.setProcess("@this");
			menItm.setCommand("#{vistaMenu.onMenuItemClick( '"
					+  opcionPadre.getDescripcion()
					+" ', '" 
					+opcionPadre.getAccion() 
					+ "', '"
					+
					opcionPadre.getModuloPadre().getDescripcion()
					+"')}");
			
			/*
			 * menItm.setProcess("@all"); menItm.setUpdate("@all");
			 */
			// menItm.addActionListener(this);
			return menItm;

		} else {

			DefaultSubMenu menItm = new DefaultSubMenu(opcionPadre.getOpcion());
			menItm.setIcon(opcionPadre.getRutaimagen());
			// menItm.setLabel(opcionPadre.getOpcion());

			Iterator<OmsOpcione> opcionesHijas = opcionPadre.getOpcionesHijas()
					.iterator();

			while (opcionesHijas.hasNext()) {
				OmsOpcione opcionHija = opcionesHijas.next();
				// Solo voy a recorrer si hay permisos para esa opcion
				if (servicioAplicacion.verificarPermisoOpcion(opcionHija,
						parametroObtenerOpciones.getUsuarioSession())) {
					menItm.getElements().add(getMenuHija(opcionHija));
				}
			}
			
			return menItm;
		}

	}
	
	

	/* (non-Javadoc)
	 * @see javax.faces.event.ActionListener#processAction(javax.faces.event.ActionEvent)
	 */
	@SuppressWarnings("unused")
	public void processAction(ActionEvent event)
			throws AbortProcessingException {
		System.out.println("Ejecucion");
		if (event.getSource().getClass() == MenuItem.class) {
			MenuItem sourceItem = (MenuItem) event.getSource();
			/*
			 * CfOpciones opcionActual = (CfOpciones)
			 * sourceItem.getAttributes().get("cfOpcion"); if
			 * (opcionActual.getTipo().equals("P")) {
			 * beanTarget.setCabecera(getRutaOpcion(opcionActual)); }
			 */
		}
	}

	/**
	 * Gets the ruta opcion.
	 *
	 * @param opcionActual the opcion actual
	 * @return the ruta opcion
	 */
	@SuppressWarnings("unused")
	private String getRutaOpcion(OmsOpcione opcionActual) {
		String rutaOpcion = "";
		rutaOpcion = getOpcionPadre(opcionActual, rutaOpcion);
		rutaOpcion = rutaOpcion.substring(0, rutaOpcion.length() - 2);
		return "OPCION ACTUAL : " + rutaOpcion.toUpperCase();
	}

	/**
	 * Gets the opcion padre.
	 *
	 * @param opcion the opcion
	 * @param nombre the nombre
	 * @return the opcion padre
	 */
	private String getOpcionPadre(OmsOpcione opcion, String nombre) {

		if (opcion.getModuloPadre() != null) {
			nombre = opcion.getOpcion() + " - " + nombre;
			return getOpcionPadre(opcion.getModuloPadre(), nombre);
		}
		return opcion.getOpcion() + " - " + nombre;
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IParseMenu#parseMenuOpciones(java.util.List, java.lang.Long)
	 */
	@Override
	public DefaultMenuModel parseMenuOpciones(List<OmsOpcione> opciones, Long lUsuario) {
		MenuModel model = new DefaultMenuModel();

		DefaultMenuItem menItm = new DefaultMenuItem("Principal");
		menItm.setIcon("fa fa-home");//fa-home
		menItm.setTitle("Pagina Principal");
		menItm.setUrl(IGuardiaUsuarioSession.PAGINA_PRINCIPAL);
		menItm.setPartialSubmit(true);
		menItm.setProcess("@this");
		//menItm.setContainerStyleClass("layout-menubar-active");
		
		
		model.addElement(menItm);
		
		model.addElement(new org.primefaces.model.menu.DefaultSeparator() );

		
		for (OmsOpcione opcion : opciones) {
			DefaultSubMenu submenu = new DefaultSubMenu(opcion.getOpcion());
			// submenu.setLabel(opcion.getOpcion());
			System.out.println(opcion.getOpcion()
					+ "----------------------------");
			submenu.setId("OPTH" + opcion.getId());
			submenu.setIcon(opcion.getRutaimagen());
			Iterator<OmsOpcione> opcionesHijas = opcion.getOpcionesHijas()
					.iterator();

			while (opcionesHijas.hasNext()) {
				OmsOpcione opcionHija = opcionesHijas.next();

				// Solo si hay permisos para esa opcion
				if (servicioAplicacion.verificarPermisoOpcion(opcionHija,
						lUsuario)) {
					if (opcionHija.getEstado().equals("A"))
						 {
						submenu.getElements().add(getMenuHija(opcionHija));
					}
				}
			}
			model.addElement(submenu);
			model.addElement(new org.primefaces.model.menu.DefaultSeparator() );
		}

		return (DefaultMenuModel) model;
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IParseMenu#parseMenuOpciones(java.util.List, java.lang.String)
	 */
	@Override
	public DefaultMenuModel parseMenuOpciones(List<OmsOpcione> opciones, String lRol) {
		MenuModel model = new DefaultMenuModel();

		DefaultMenuItem menItm = new DefaultMenuItem("Principal");
		menItm.setIcon("fa fa-home");//fa-home
		menItm.setTitle("Pagina Principal");
		menItm.setUrl(IGuardiaUsuarioSession.PAGINA_PRINCIPAL);
		menItm.setPartialSubmit(true);
		menItm.setProcess("@this");
		//menItm.setContainerStyleClass("layout-menubar-active");
		
		
		model.addElement(menItm);
		
		model.addElement(new org.primefaces.model.menu.DefaultSeparator() );

		
		for (OmsOpcione opcion : opciones) {
			DefaultSubMenu submenu = new DefaultSubMenu(opcion.getOpcion());
			// submenu.setLabel(opcion.getOpcion());
			System.out.println(opcion.getOpcion()
					+ "----------------------------");
			submenu.setId("OPTH" + opcion.getId());
			submenu.setIcon(opcion.getRutaimagen());
			Iterator<OmsOpcione> opcionesHijas = opcion.getOpcionesHijas()
					.iterator();

			while (opcionesHijas.hasNext()) {
				OmsOpcione opcionHija = opcionesHijas.next();

				// Solo si hay permisos para esa opcion
				if (servicioAplicacion.verificarPermisoOpcion(opcionHija,
						lRol)) {
					if (opcionHija.getEstado().equals("A"))
						 {
						submenu.getElements().add(getMenuHija(opcionHija));
					}
				}
			}
			model.addElement(submenu);
			model.addElement(new org.primefaces.model.menu.DefaultSeparator() );
		}

		return (DefaultMenuModel) model;
	}

	public OmsOpcione getOpcionSeleccionada() {
		return opcionSeleccionada;
	}

	public void setOpcionSeleccionada(OmsOpcione opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}

	public String getlPaginaPrincipal() {
		return lPaginaPrincipal;
	}

	public void setlPaginaPrincipal(String lPaginaPrincipal) {
		this.lPaginaPrincipal = lPaginaPrincipal;
	}
	
	

}
