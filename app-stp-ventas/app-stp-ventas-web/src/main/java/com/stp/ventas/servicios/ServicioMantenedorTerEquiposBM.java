package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerEquiposBM;
import com.stp.ventas.eao.TerEquiposBMEAO;
@Stateless
public class ServicioMantenedorTerEquiposBM extends ServicioMantenedorControlAuditoria <TerEquiposBMEAO,TerEquiposBM,Long> {
@EJB
private TerEquiposBMEAO crud;
protected TerEquiposBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
