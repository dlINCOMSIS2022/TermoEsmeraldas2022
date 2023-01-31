package librerias.servicio.oyentes;

import java.io.Serializable;

import librerias.dominio.entidades.base.EntidadBaseAuditable;
import librerias.exceptions.ErrorServicioNegocio;
import librerias.exceptions.ErrorValidacionGeneral;

@FunctionalInterface
public interface AccionTransaccionalListener<ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable> {

	public void controlTransaccional(ENTIDAD entidad) throws ErrorServicioNegocio, ErrorValidacionGeneral;
}
