package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerParametrosUnidadBC;
import com.stp.ventas.eao.TerParametrosUnidadBCEAO;
@Stateless
public class ServicioMantenedorTerParametrosUnidadBC extends ServicioMantenedorControlAuditoria <TerParametrosUnidadBCEAO,TerParametrosUnidadBC,Long> {
@EJB
private TerParametrosUnidadBCEAO crud;
protected TerParametrosUnidadBCEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
