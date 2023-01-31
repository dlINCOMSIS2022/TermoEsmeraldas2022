package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerParametrosUnidadBAG;
import com.stp.ventas.eao.TerParametrosUnidadBAGEAO;
@Stateless
public class ServicioMantenedorTerParametrosUnidadBAG extends ServicioMantenedorControlAuditoria <TerParametrosUnidadBAGEAO,TerParametrosUnidadBAG,Long> {
@EJB
private TerParametrosUnidadBAGEAO crud;
protected TerParametrosUnidadBAGEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
