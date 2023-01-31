package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerUbicacionOperadorBM;
import com.stp.ventas.eao.TerUbicacionOperadorBMEAO;
@Stateless
public class ServicioMantenedorTerUbicacionOperadorBM extends ServicioMantenedorControlAuditoria <TerUbicacionOperadorBMEAO,TerUbicacionOperadorBM,Long> {
@EJB
private TerUbicacionOperadorBMEAO crud;
protected TerUbicacionOperadorBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
