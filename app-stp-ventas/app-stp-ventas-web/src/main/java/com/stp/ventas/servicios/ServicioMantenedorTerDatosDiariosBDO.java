package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerDatosDiariosBDO;
import com.stp.ventas.eao.TerDatosDiariosBDOEAO;
@Stateless
public class ServicioMantenedorTerDatosDiariosBDO extends ServicioMantenedorControlAuditoria <TerDatosDiariosBDOEAO,TerDatosDiariosBDO,Long> {
@EJB
private TerDatosDiariosBDOEAO crud;
protected TerDatosDiariosBDOEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
