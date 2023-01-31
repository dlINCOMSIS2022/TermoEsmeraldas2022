package com.stp.plataforma.vista.aplicacion.seguridad;



import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import com.stp.librerias.generales.UtilEncriptarDataSource;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.servicio.aplicacion.seguridad.ServicioAutenticacion;

import librerias.vista.anotaciones.ITestAutenticacion;
import librerias.vista.interfaces.IServiciosAutenticacion;



// TODO: Auto-generated Javadoc
/**
 * The Class TestAutenticar.
 */
@ITestAutenticacion
@RequestScoped
public class TestAutenticar implements IServiciosAutenticacion<OmsUsuario, BeanLogin>
{
	
	/** The autenticar. */
	@EJB
	private ServicioAutenticacion autenticar;
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IServiciosAutenticacion#autenticar(java.io.Serializable)
	 */
	public OmsUsuario autenticar(BeanLogin datosAutenticar) throws Exception
	{
		OmsUsuario usuari = null;
		try{
			
			
			usuari = autenticar.autenticar(datosAutenticar.getUsuario().toUpperCase(),  UtilEncriptarDataSource.encode(datosAutenticar.getClave()));
		
		
		}catch(Exception e){
			throw new Exception("No existe");
		}
		
		return usuari;
		
	}
	
	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.interfaces.IServiciosAutenticacion#autenticarDominio(java.io.Serializable)
	 */
	public boolean autenticarDominio(BeanLogin datosAutenticar){
		
		return true;
	}
}
