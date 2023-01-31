package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerParametrosUnidadBT;
import com.stp.ventas.eao.TerParametrosUnidadBTEAO;
@Stateless
public class ServicioMantenedorTerParametrosUnidadBT extends ServicioMantenedorControlAuditoria <TerParametrosUnidadBTEAO,TerParametrosUnidadBT,Long> {
@EJB
private TerParametrosUnidadBTEAO crud;
protected TerParametrosUnidadBTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
