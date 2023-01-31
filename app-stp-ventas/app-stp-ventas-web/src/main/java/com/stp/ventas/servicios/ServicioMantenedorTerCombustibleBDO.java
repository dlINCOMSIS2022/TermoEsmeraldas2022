package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerCombustibleBDO;
import com.stp.ventas.eao.TerCombustibleBDOEAO;
@Stateless
public class ServicioMantenedorTerCombustibleBDO extends ServicioMantenedorControlAuditoria <TerCombustibleBDOEAO,TerCombustibleBDO,Long> {
@EJB
private TerCombustibleBDOEAO crud;
protected TerCombustibleBDOEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
