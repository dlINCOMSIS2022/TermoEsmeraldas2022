package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerParametrosUnidadBINGT;
import com.stp.ventas.eao.TerParametrosUnidadBINGTEAO;
@Stateless
public class ServicioMantenedorTerParametrosUnidadBINGT extends ServicioMantenedorControlAuditoria <TerParametrosUnidadBINGTEAO,TerParametrosUnidadBINGT,Long> {
@EJB
private TerParametrosUnidadBINGTEAO crud;
protected TerParametrosUnidadBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
