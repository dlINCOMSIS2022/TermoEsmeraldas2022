package librerias.servicio.oyentes;

import java.io.Serializable;

import librerias.dominio.entidades.base.EntidadBaseAuditable;
import librerias.exceptions.ErrorServicioNegocio;

@FunctionalInterface
public interface AccionValidacionListener<ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable> {

	public void validacionTransaccional(ENTIDAD entidad) throws ErrorServicioNegocio;

}
