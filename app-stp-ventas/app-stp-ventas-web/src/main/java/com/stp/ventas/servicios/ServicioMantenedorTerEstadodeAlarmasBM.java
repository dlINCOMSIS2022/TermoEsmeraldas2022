package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerEstadodeAlarmasBM;
import com.stp.ventas.eao.TerEstadodeAlarmasBMEAO;
@Stateless
public class ServicioMantenedorTerEstadodeAlarmasBM extends ServicioMantenedorControlAuditoria <TerEstadodeAlarmasBMEAO,TerEstadodeAlarmasBM,Long> {
@EJB
private TerEstadodeAlarmasBMEAO crud;
protected TerEstadodeAlarmasBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
