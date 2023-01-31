package librerias.dominio.entidades;

import java.io.Serializable;

public interface IEntidadPersistible<Id extends Serializable> 
{
	public Id getId();
	public void setId(Id id);
	public void setEstado(String estado);
	public String getEstado();
	public void setAuditoria(String usuario);
	public Long getIdReferencia();
	public void setIdReferencia(Long idReferencia);
	
}
