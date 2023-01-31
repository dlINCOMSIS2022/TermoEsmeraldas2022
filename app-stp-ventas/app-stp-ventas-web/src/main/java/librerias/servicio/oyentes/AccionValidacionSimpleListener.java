package librerias.servicio.oyentes;

import java.io.Serializable;

import librerias.dominio.entidades.base.EntidadBaseAuditable;
import librerias.exceptions.ErrorValidacionGeneral;

@FunctionalInterface
public interface AccionValidacionSimpleListener<ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable> {

	public void validacionDatos(ENTIDAD entidad) throws ErrorValidacionGeneral;

}
