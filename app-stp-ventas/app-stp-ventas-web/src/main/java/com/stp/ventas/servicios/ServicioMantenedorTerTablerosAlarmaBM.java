package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerTablerosAlarmaBM;
import com.stp.ventas.eao.TerTablerosAlarmaBMEAO;
@Stateless
public class ServicioMantenedorTerTablerosAlarmaBM extends ServicioMantenedorControlAuditoria <TerTablerosAlarmaBMEAO,TerTablerosAlarmaBM,Long> {
@EJB
private TerTablerosAlarmaBMEAO crud;
protected TerTablerosAlarmaBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
