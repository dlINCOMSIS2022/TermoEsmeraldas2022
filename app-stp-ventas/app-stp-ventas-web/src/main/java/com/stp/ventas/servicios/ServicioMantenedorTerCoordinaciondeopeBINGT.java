package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerCoordinaciondeopeBINGT;
import com.stp.ventas.eao.TerCoordinaciondeopeBINGTEAO;
@Stateless
public class ServicioMantenedorTerCoordinaciondeopeBINGT extends ServicioMantenedorControlAuditoria <TerCoordinaciondeopeBINGTEAO,TerCoordinaciondeopeBINGT,Long> {
@EJB
private TerCoordinaciondeopeBINGTEAO crud;
protected TerCoordinaciondeopeBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
