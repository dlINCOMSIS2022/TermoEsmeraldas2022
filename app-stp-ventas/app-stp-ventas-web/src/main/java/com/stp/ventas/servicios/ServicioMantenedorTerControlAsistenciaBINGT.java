package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerControlAsistenciaBINGT;
import com.stp.ventas.eao.TerControlAsistenciaBINGTEAO;
@Stateless
public class ServicioMantenedorTerControlAsistenciaBINGT extends ServicioMantenedorControlAuditoria <TerControlAsistenciaBINGTEAO,TerControlAsistenciaBINGT,Long> {
@EJB
private TerControlAsistenciaBINGTEAO crud;
protected TerControlAsistenciaBINGTEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
