package com.stp.plataforma.vista.aplicacion.seguridad;



import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.stp.plataforma.dominio.aplicacion.OmsOpcione;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.servicio.aplicacion.seguridad.ServiciosMantenimientoSeguridad;

import librerias.vista.anotaciones.ITestServicioAutorizacion;
import librerias.vista.anotaciones.ITestUsuarioSession;
import librerias.vista.interfaces.IServicioAutorizacion;
import librerias.vista.interfaces.IUsuarioSession;



// TODO: Auto-generated Javadoc
/**
 * The Class ServicioAutorizacion.
 */
@ITestServicioAutorizacion
@RequestScoped
public class ServicioAutorizacion implements IServicioAutorizacion
{

	/** The servicios mantenimiento seguridad. */
	@EJB
	private ServiciosMantenimientoSeguridad serviciosMantenimientoSeguridad;
	
	/** The usuario session. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<OmsUsuario> usuarioSession;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IServicioAutorizacion#validarOpcionUsuario(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validarOpcionUsuario(String url, String usuario, String contextPath) {
		// TODO Auto-generated method stub
		System.out.println("Se debe controlar los usuarios");
		
		List<OmsOpcione> listaOpciones = serviciosMantenimientoSeguridad.obtenerOpcionesPorUsuario(usuario);
		for (OmsOpcione cfOpciones : listaOpciones) {
			if ((contextPath + cfOpciones.getAccion()).equals(url)) {
				return true;
			}
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IServicioAutorizacion#obtenerUsuarioAutenticado()
	 */
	@Override
	public String obtenerUsuarioAutenticado() {
		// TODO Auto-generated method stub
		return usuarioSession.getUsuarioSession().getUsuario();
	}
	
}
