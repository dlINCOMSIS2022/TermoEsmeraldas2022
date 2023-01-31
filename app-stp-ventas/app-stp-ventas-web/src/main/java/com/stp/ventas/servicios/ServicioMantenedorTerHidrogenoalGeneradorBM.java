package com.stp.ventas.servicios;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.TerHidrogenoalGeneradorBM;
import com.stp.ventas.eao.TerHidrogenoalGeneradorBMEAO;
@Stateless
public class ServicioMantenedorTerHidrogenoalGeneradorBM extends ServicioMantenedorControlAuditoria <TerHidrogenoalGeneradorBMEAO,TerHidrogenoalGeneradorBM,Long> {
@EJB
private TerHidrogenoalGeneradorBMEAO crud;
protected TerHidrogenoalGeneradorBMEAO getCrud() {
// TODO Auto-generated method stub
return crud;
}
@Override
protected void cargarConfiguracionServicio() {
// TODO Auto-generated method stub
}
}
