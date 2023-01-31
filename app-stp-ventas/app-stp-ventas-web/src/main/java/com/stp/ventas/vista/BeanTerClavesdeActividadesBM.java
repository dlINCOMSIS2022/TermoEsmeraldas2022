package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerClavesdeActividadesBM;
import com.stp.ventas.eao.TerClavesdeActividadesBMEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerClavesdeActividadesBM;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerClavesdeActividadesBM extends BeanMantenedorGenerico<ServicioMantenedorTerClavesdeActividadesBM,Long,TerClavesdeActividadesBM,TerClavesdeActividadesBMEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerClavesdeActividadesBM servicioParUni;

public BeanTerClavesdeActividadesBM() {
super(TerClavesdeActividadesBM.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from terclaves_de_actividadesbm ";
List<TerClavesdeActividadesBM> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerClavesdeActividadesBM.class);
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

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Claves de Actividades Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Claves de Actividades Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Claves de Actividades Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Claves de Actividades Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Claves de Actividades Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci�n de Claves de Actividades Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Claves de Actividades Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerClavesdeActividadesBM getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
