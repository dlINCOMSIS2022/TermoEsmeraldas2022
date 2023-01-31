package librerias.vista.beans.oyentes;

import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@FunctionalInterface
public interface PreTransaccionListener 
{

	public void accionPreTransaccion() throws ErrorAccionesPreTransaccion;
	
}
