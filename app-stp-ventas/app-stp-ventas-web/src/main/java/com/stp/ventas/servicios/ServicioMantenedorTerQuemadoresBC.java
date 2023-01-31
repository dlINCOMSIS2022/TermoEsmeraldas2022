package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerQuemadoresBC;
import com.stp.ventas.eao.TerQuemadoresBCEAO;
@Stateless
public class ServicioMantenedorTerQuemadoresBC extends ServicioMantenedorControlAuditoria <TerQuemadoresBCEAO,TerQuemadoresBC,Long> {
@EJB
private TerQuemadoresBCEAO crud;
protected TerQuemadoresBCEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
