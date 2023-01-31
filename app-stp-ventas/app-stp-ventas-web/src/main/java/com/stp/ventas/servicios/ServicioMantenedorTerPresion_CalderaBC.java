package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerPresiones_CalderaBC;
import com.stp.ventas.eao.TerPresion_CalderaBCEAO;
@Stateless
public class ServicioMantenedorTerPresion_CalderaBC extends ServicioMantenedorControlAuditoria <TerPresion_CalderaBCEAO,TerPresiones_CalderaBC,Long> {
@EJB
private TerPresion_CalderaBCEAO crud;
protected TerPresion_CalderaBCEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
