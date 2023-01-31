package com.stp.plataforma.vista.aplicacion.seguridad;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.servicio.aplicacion.seguridad.ServicioAplicacion;

import librerias.vista.anotaciones.ITestServicioMenuOpcionesHorizontal;
import librerias.vista.anotaciones.ITestUsuarioSession;
import librerias.vista.interfaces.IGuardiaUsuarioSession;
import librerias.vista.interfaces.IServiciosMenu;
import librerias.vista.interfaces.IUsuarioSession;


// TODO: Auto-generated Javadoc
/**
 * The Class TestServiciosMenuHorizontal.
 */
@ITestServicioMenuOpcionesHorizontal
@RequestScoped
public class TestServiciosMenuHorizontal implements IServiciosMenu<OmsOpcione>
{

	/** The parametro obtener opciones. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<OmsUsuario> parametroObtenerOpciones;
	
	/** The servicio aplicacion. */
	@EJB
	private ServicioAplicacion servicioAplicacion;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IServiciosMenu#getMenuOpciones()
	 */
	public List<OmsOpcione> getMenuOpciones() 
	{
		return servicioAplicacion.obtenerMenu(parametroObtenerOpciones.getUsuarioSession(), IGuardiaUsuarioSession.TIPO_MENU);
	}
	
}
