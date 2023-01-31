package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerOperadoresBM;
import com.stp.ventas.eao.TerOperadoresBMEAO;
@Stateless
public class ServicioMantenedorTerOperadoresBM extends ServicioMantenedorControlAuditoria <TerOperadoresBMEAO,TerOperadoresBM,Long> {
@EJB
private TerOperadoresBMEAO crud;
protected TerOperadoresBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
