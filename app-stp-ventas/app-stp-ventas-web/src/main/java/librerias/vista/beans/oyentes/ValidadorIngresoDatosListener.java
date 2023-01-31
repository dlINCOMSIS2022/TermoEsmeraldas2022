package librerias.vista.beans.oyentes;

import librerias.vista.exceptions.ErrorValidacionVisual;

@FunctionalInterface
public interface ValidadorIngresoDatosListener 
{
	public void validarDatosIngreso() throws ErrorValidacionVisual;
	//
}
