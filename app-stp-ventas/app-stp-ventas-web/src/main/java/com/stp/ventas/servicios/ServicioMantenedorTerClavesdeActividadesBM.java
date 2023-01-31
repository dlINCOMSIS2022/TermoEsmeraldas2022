package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerClavesdeActividadesBM;
import com.stp.ventas.eao.TerClavesdeActividadesBMEAO;
@Stateless
public class ServicioMantenedorTerClavesdeActividadesBM extends ServicioMantenedorControlAuditoria <TerClavesdeActividadesBMEAO,TerClavesdeActividadesBM,Long> {
@EJB
private TerClavesdeActividadesBMEAO crud;
protected TerClavesdeActividadesBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
