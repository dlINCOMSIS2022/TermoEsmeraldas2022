package com.stp.plataforma.vista.aplicacion.seguridad;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import com.stp.plataforma.dominio.aplicacion.OmsUsuario;

import librerias.vista.anotaciones.ITestUsuarioSession;
import librerias.vista.interfaces.IUsuarioSession;



// TODO: Auto-generated Javadoc
/**
 * The Class TestUsuarioSession.
 */
@ITestUsuarioSession
@SessionScoped
public class TestUsuarioSession implements IUsuarioSession<OmsUsuario>, Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The usuario. */
	private OmsUsuario usuario;

	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IUsuarioSession#setUsuarioSession(java.io.Serializable)
	 */
	public void setUsuarioSession(OmsUsuario usuario) {
		this.usuario = usuario;
	}
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IUsuarioSession#getUsuarioSession()
	 */
	public OmsUsuario getUsuarioSession() {
		
		return this.usuario;
	}

}
