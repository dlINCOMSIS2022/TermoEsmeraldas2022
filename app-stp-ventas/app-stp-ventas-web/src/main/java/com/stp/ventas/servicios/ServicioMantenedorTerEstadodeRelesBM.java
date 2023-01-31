package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerEstadodeRelesBM;
import com.stp.ventas.eao.TerEstadodeRelesBMEAO;
@Stateless
public class ServicioMantenedorTerEstadodeRelesBM extends ServicioMantenedorControlAuditoria <TerEstadodeRelesBMEAO,TerEstadodeRelesBM,Long> {
@EJB
private TerEstadodeRelesBMEAO crud;
protected TerEstadodeRelesBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
