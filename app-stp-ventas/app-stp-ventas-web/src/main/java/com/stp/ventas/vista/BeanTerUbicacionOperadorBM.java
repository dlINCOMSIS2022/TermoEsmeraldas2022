package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerUbicacionOperadorBM;
import com.stp.ventas.eao.TerUbicacionOperadorBMEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerUbicacionOperadorBM;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerUbicacionOperadorBM extends BeanMantenedorGenerico<ServicioMantenedorTerUbicacionOperadorBM,Long,TerUbicacionOperadorBM,TerUbicacionOperadorBMEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerUbicacionOperadorBM servicioParUni;

public BeanTerUbicacionOperadorBM() {
super(TerUbicacionOperadorBM.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from terubicacion_operadorbm ";
List<TerUbicacionOperadorBM> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerUbicacionOperadorBM.class);
setListaEntidades(lListaTipo);
}
});

addPreTransaccionServicioListener(new PreTransaccionListener() {

@Override
public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
entidadRegistrar.setFechaRegistro(new Date());
entidadRegistrar.setFechaActualizacion(new Date());

}
});

}

@Override
protected void cargarListaEtiquetas() {

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Ubicacion Operador Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Ubicacion Operador Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Ubicacion Operador Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Ubicacion Operador Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Ubicacion Operador Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci�n de Ubicacion Operador Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Ubicacion Operador Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerUbicacionOperadorBM getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
