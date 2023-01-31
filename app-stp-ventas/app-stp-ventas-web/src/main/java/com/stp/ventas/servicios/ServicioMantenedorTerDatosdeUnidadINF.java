package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerDatosdeUnidadINF;
import com.stp.ventas.eao.TerDatosdeUnidadINFEAO;
@Stateless
public class ServicioMantenedorTerDatosdeUnidadINF extends ServicioMantenedorControlAuditoria <TerDatosdeUnidadINFEAO,TerDatosdeUnidadINF,Long> {
@EJB
private TerDatosdeUnidadINFEAO crud;
protected TerDatosdeUnidadINFEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
