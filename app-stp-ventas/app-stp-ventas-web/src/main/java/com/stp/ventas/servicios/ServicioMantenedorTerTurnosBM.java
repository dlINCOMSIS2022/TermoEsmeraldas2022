package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerTurnosBM;
import com.stp.ventas.eao.TerTurnosBMEAO;
@Stateless
public class ServicioMantenedorTerTurnosBM extends ServicioMantenedorControlAuditoria <TerTurnosBMEAO,TerTurnosBM,Long> {
@EJB
private TerTurnosBMEAO crud;
protected TerTurnosBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
