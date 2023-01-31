package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerRestriccionesBINGT;
import com.stp.ventas.eao.TerRestriccionesBINGTEAO;
@Stateless
public class ServicioMantenedorTerRestriccionesBINGT extends ServicioMantenedorControlAuditoria <TerRestriccionesBINGTEAO,TerRestriccionesBINGT,Long> {
@EJB
private TerRestriccionesBINGTEAO crud;
protected TerRestriccionesBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
