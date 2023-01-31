package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerOrdenesdeTrabajoBINGT;
import com.stp.ventas.eao.TerOrdenesdeTrabajoBINGTEAO;
@Stateless
public class ServicioMantenedorTerOrdenesdeTrabajoBINGT extends ServicioMantenedorControlAuditoria <TerOrdenesdeTrabajoBINGTEAO,TerOrdenesdeTrabajoBINGT,Long> {
@EJB
private TerOrdenesdeTrabajoBINGTEAO crud;
protected TerOrdenesdeTrabajoBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
