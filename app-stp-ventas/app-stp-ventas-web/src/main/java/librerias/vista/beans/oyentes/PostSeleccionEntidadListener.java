package librerias.vista.beans.oyentes;

import java.io.Serializable;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

@FunctionalInterface
public interface PostSeleccionEntidadListener<ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable>
{

	public void postSeleccionRegistro(ENTIDAD pEntidadSeleccionada);
	
}
