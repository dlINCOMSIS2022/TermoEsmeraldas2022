package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerEstadodeEquiposBM;
import com.stp.ventas.eao.TerEstadodeEquiposBMEAO;
@Stateless
public class ServicioMantenedorTerEstadodeEquiposBM extends ServicioMantenedorControlAuditoria <TerEstadodeEquiposBMEAO,TerEstadodeEquiposBM,Long> {
@EJB
private TerEstadodeEquiposBMEAO crud;
protected TerEstadodeEquiposBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
