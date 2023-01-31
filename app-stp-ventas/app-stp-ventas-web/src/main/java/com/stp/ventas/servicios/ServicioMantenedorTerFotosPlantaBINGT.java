package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerFotosPlantaBINGT;
import com.stp.ventas.eao.TerFotosPlantaBINGTEAO;
@Stateless
public class ServicioMantenedorTerFotosPlantaBINGT extends ServicioMantenedorControlAuditoria <TerFotosPlantaBINGTEAO,TerFotosPlantaBINGT,Long> {
@EJB
private TerFotosPlantaBINGTEAO crud;
protected TerFotosPlantaBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
