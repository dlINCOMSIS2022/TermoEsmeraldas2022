package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerSugerenciasBINGT;
import com.stp.ventas.eao.TerSugerenciasBINGTEAO;
@Stateless
public class ServicioMantenedorTerSugerenciasBINGT extends ServicioMantenedorControlAuditoria <TerSugerenciasBINGTEAO,TerSugerenciasBINGT,Long> {
@EJB
private TerSugerenciasBINGTEAO crud;
protected TerSugerenciasBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
