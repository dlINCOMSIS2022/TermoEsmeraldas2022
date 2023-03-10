package com.stp.plataforma.vista.aplicacion.seguridad;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.MenuActionEvent;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuItem;
import org.primefaces.model.menu.MenuModel;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.ventas.vista.BeanControlFactura;

import librerias.vista.JsfUtil;
import librerias.vista.anotaciones.ITestParseMenu;
import librerias.vista.anotaciones.ITestServicioMenuOpcionesHorizontal;
import librerias.vista.interfaces.IServiciosMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuView.
 */
@Named("vistaMenu")
@RequestScoped
public class MenuView implements Serializable {

	/** The descripcion opcion. */

	private String descripcionOpcion;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The opciones menu. */
	@Inject
	@ITestServicioMenuOpcionesHorizontal
	private IServiciosMenu<OmsOpcione> opcionesMenu;

	/** The parse menu. */
	@Inject
	@ITestParseMenu
	private TestParseMenu parseMenu;
	
	@Inject
	private BeanControlFactura lControlFact;

	/** The model. */
	private MenuModel model;
	
	private String nombreOpcion;
	private String urlOpcion;

	/**
	 * Inits the.
	 */
	@PostConstruct
	public void init() {
		model = new DefaultMenuModel();
		model = parseMenu.parseMenuOpciones(opcionesMenu.getMenuOpciones());
		model.generateUniqueIds();

	}

	/**
	 * Display list.
	 *
	 * @param event the event
	 */
	public void displayList(ActionEvent event) {
		MenuItem menuItem = ((MenuActionEvent) event).getMenuItem();
		descripcionOpcion = menuItem.getParams().get("descripcion").get(0);
		System.out.println(descripcionOpcion);
	}

	
	public String onMenuItemClick(String nombreOpcionMenu, String urlOpcionMenu, String lPadre) {
		
		String lSanitizacionUrl = "";
		
		if (urlOpcionMenu.indexOf("jsf")!=-1)
			lSanitizacionUrl = urlOpcionMenu.substring(0, urlOpcionMenu.length() - 4);
		
		if (urlOpcionMenu.indexOf("xhtml")!=-1)
			lSanitizacionUrl = urlOpcionMenu.substring(0, urlOpcionMenu.length() - 5);
		
		this.nombreOpcion = nombreOpcionMenu;
		this.urlOpcion = JsfUtil.getContextoJSF().getExternalContext().getApplicationContextPath() + urlOpcionMenu;
		
		System.out.println("Nombre opcion de menu: " + nombreOpcionMenu );
		System.out.println("Url opcion de menu: " + lSanitizacionUrl );
		
		
		OmsOpcione lOpcion = new OmsOpcione();

		OmsOpcione lOpcionPadre = new OmsOpcione();
		lOpcionPadre.setDescripcion(lPadre);
		
		lOpcion.setAccion(lSanitizacionUrl);
		lOpcion.setDescripcion(this.nombreOpcion);
		lOpcion.setModuloPadre(lOpcionPadre);
		parseMenu.setOpcionSeleccionada(lOpcion);
		
		lControlFact.setlCabeceraFactura(null);
		
		return lSanitizacionUrl+"?faces-redirect=true";
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
	 * Mensaje.
	 */
	public void Mensaje() {
		addMessage("Exito", "Ud a dado un clik");
	}

	/**
	 * Metodo post seleccion menu.
	 */
	public void metodoPostSeleccionMenu() {

	}

	/**
	 * Save.
	 */
	public void save() {
		addMessage("Success", "Data saved");
	}

	/**
	 * Update.
	 */
	public void update() {
		addMessage("Success", "Data updated");
	}

	/**
	 * Delete.
	 */
	public void delete() {
		addMessage("Success", "Data deleted");
	}

	/**
	 * Adds the message.
	 *
	 * @param summary the summary
	 * @param detail the detail
	 */
	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Gets the descripcion opcion.
	 *
	 * @return the descripcion opcion
	 */
	public String getDescripcionOpcion() {
		return descripcionOpcion;
	}

	/**
	 * Sets the descripcion opcion.
	 *
	 * @param descripcionOpcion the new descripcion opcion
	 */
	public void setDescripcionOpcion(String descripcionOpcion) {
		this.descripcionOpcion = descripcionOpcion;
	}

	public String getNombreOpcion() {
		return nombreOpcion;
	}

	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}

	public String getUrlOpcion() {
		return urlOpcion;
	}

	public void setUrlOpcion(String urlOpcion) {
		this.urlOpcion = urlOpcion;
	}
	
	
	
}
