package librerias.servicio.oyentes;

import java.io.Serializable;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

@FunctionalInterface
public interface PreLlenadoRegistroListener <ENTIDAD extends EntidadBaseAuditable<Id>, Id extends Serializable>{

	public void preCargaDatosRegistro(ENTIDAD entidad);
	
}
