package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerParametrosUnidadBTAB;
import com.stp.ventas.eao.TerParametrosUnidadBTABEAO;
@Stateless
public class ServicioMantenedorTerParametrosUnidadBTAB extends ServicioMantenedorControlAuditoria <TerParametrosUnidadBTABEAO,TerParametrosUnidadBTAB,Long> {
@EJB
private TerParametrosUnidadBTABEAO crud;
protected TerParametrosUnidadBTABEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
