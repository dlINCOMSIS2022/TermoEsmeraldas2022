package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerTablerosAlarmaBM;
import com.stp.ventas.eao.TerTablerosAlarmaBMEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerTablerosAlarmaBM;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerTablerosAlarmaBM extends BeanMantenedorGenerico<ServicioMantenedorTerTablerosAlarmaBM,Long,TerTablerosAlarmaBM,TerTablerosAlarmaBMEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerTablerosAlarmaBM servicioParUni;

public BeanTerTablerosAlarmaBM() {
super(TerTablerosAlarmaBM.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from tertableros_alarmabm ";
List<TerTablerosAlarmaBM> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerTablerosAlarmaBM.class);
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

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Tableros Alarma Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Tableros Alarma Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Tableros Alarma Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Tableros Alarma Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Tableros Alarma Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci???n de Tableros Alarma Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Tableros Alarma Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerTablerosAlarmaBM getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
