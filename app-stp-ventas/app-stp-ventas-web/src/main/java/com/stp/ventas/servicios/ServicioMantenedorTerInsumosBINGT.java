package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerInsumosBINGT;
import com.stp.ventas.eao.TerInsumosBINGTEAO;
@Stateless
public class ServicioMantenedorTerInsumosBINGT extends ServicioMantenedorControlAuditoria <TerInsumosBINGTEAO,TerInsumosBINGT,Long> {
@EJB
private TerInsumosBINGTEAO crud;
protected TerInsumosBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
