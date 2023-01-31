package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerDisponibilidadBINGT;
import com.stp.ventas.eao.TerDisponibilidadBINGTEAO;
@Stateless
public class ServicioMantenedorTerDisponibilidadBINGT extends ServicioMantenedorControlAuditoria <TerDisponibilidadBINGTEAO,TerDisponibilidadBINGT,Long> {
@EJB
private TerDisponibilidadBINGTEAO crud;
protected TerDisponibilidadBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
