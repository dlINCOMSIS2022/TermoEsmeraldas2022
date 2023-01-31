package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerCalentadoresBT;
import com.stp.ventas.eao.TerCalentadoresBTEAO;
@Stateless
public class ServicioMantenedorTerCalentadoresBT extends ServicioMantenedorControlAuditoria <TerCalentadoresBTEAO,TerCalentadoresBT,Long> {
@EJB
private TerCalentadoresBTEAO crud;
protected TerCalentadoresBTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
