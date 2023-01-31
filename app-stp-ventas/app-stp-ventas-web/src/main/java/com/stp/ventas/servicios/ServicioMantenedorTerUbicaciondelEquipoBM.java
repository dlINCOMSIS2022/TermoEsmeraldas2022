package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerUbicaciondelEquipoBM;
import com.stp.ventas.eao.TerUbicaciondelEquipoBMEAO;
@Stateless
public class ServicioMantenedorTerUbicaciondelEquipoBM extends ServicioMantenedorControlAuditoria <TerUbicaciondelEquipoBMEAO,TerUbicaciondelEquipoBM,Long> {
@EJB
private TerUbicaciondelEquipoBMEAO crud;
protected TerUbicaciondelEquipoBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
