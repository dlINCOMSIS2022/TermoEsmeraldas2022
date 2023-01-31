package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerBitacoraBDO;
import com.stp.ventas.eao.TerBitacoraBDOEAO;
@Stateless
public class ServicioMantenedorTerBitacoraBDO extends ServicioMantenedorControlAuditoria <TerBitacoraBDOEAO,TerBitacoraBDO,Long> {
@EJB
private TerBitacoraBDOEAO crud;
protected TerBitacoraBDOEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
