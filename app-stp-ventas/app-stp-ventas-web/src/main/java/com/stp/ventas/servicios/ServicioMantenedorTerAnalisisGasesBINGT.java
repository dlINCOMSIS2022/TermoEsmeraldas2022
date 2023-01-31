package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerAnalisisGasesBINGT;
import com.stp.ventas.eao.TerAnalisisGasesBINGTEAO;
@Stateless
public class ServicioMantenedorTerAnalisisGasesBINGT extends ServicioMantenedorControlAuditoria <TerAnalisisGasesBINGTEAO,TerAnalisisGasesBINGT,Long> {
@EJB
private TerAnalisisGasesBINGTEAO crud;
protected TerAnalisisGasesBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
