package com.stp.plataforma.vista.aplicacion.seguridad;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import com.stp.librerias.generales.UtilEncriptarDataSource;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;

import librerias.vista.anotaciones.ITestGuardiaSession;
import librerias.vista.anotaciones.ITestUsuarioSession;
import librerias.vista.interfaces.IGuardiaUsuarioSession;
import librerias.vista.interfaces.IUsuarioSession;


// TODO: Auto-generated Javadoc
/**
 * The Class TestGuardiaUsuarioSession.
 */
@ITestGuardiaSession
@RequestScoped
public class TestGuardiaUsuarioSession implements IGuardiaUsuarioSession 
{
	
	/** The usuario session. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<OmsUsuario> usuarioSession;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IGuardiaUsuarioSession#usuarioEnSession()
	 */
	public boolean usuarioEnSession() 
	{
		if (usuarioSession == null || usuarioSession.getUsuarioSession()==null)
		{
			return false;
		}
		return true;
		
	}
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IGuardiaUsuarioSession#validarSemilla(java.lang.String)
	 */
	public boolean validarSemilla(String semilla) {
		try {
				String semillaReal =   UtilEncriptarDataSource.encode(usuarioSession.getUsuarioSession().getClave().toUpperCase()+usuarioSession.getUsuarioSession().getUsuario().toUpperCase());
				return semillaReal.equals(semilla);
		} catch (Exception e) {

			return false;
		}
	}
	

	
}
