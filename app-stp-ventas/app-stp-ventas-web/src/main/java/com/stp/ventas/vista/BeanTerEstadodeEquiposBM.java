package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerEstadodeEquiposBM;
import com.stp.ventas.eao.TerEstadodeEquiposBMEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerEstadodeEquiposBM;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerEstadodeEquiposBM extends BeanMantenedorGenerico<ServicioMantenedorTerEstadodeEquiposBM,Long,TerEstadodeEquiposBM,TerEstadodeEquiposBMEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerEstadodeEquiposBM servicioParUni;

public BeanTerEstadodeEquiposBM() {
super(TerEstadodeEquiposBM.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from terestado_de_equiposbm ";
List<TerEstadodeEquiposBM> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerEstadodeEquiposBM.class);
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

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Estado de Equipos Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Estado de Equipos Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Estado de Equipos Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Estado de Equipos Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Estado de Equipos Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci�n de Estado de Equipos Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Estado de Equipos Bitacora Maestra");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerEstadodeEquiposBM getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
