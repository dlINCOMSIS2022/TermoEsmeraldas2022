package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerInsumosBC;
import com.stp.ventas.eao.TerInsumosBCEAO;
@Stateless
public class ServicioMantenedorTerInsumosBC extends ServicioMantenedorControlAuditoria <TerInsumosBCEAO,TerInsumosBC,Long> {
@EJB
private TerInsumosBCEAO crud;
protected TerInsumosBCEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
