package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerCambiosHorarioBINGT;
import com.stp.ventas.eao.TerCambiosHorarioBINGTEAO;
@Stateless
public class ServicioMantenedorTerCambiosHorarioBINGT extends ServicioMantenedorControlAuditoria <TerCambiosHorarioBINGTEAO,TerCambiosHorarioBINGT,Long> {
@EJB
private TerCambiosHorarioBINGTEAO crud;
protected TerCambiosHorarioBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
