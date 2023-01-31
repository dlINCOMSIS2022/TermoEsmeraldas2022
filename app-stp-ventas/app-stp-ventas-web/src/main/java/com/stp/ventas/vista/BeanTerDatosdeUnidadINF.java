package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerDatosdeUnidadINF;
import com.stp.ventas.eao.TerDatosdeUnidadINFEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerDatosdeUnidadINF;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerDatosdeUnidadINF extends BeanMantenedorGenerico<ServicioMantenedorTerDatosdeUnidadINF,Long,TerDatosdeUnidadINF,TerDatosdeUnidadINFEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerDatosdeUnidadINF servicioParUni;

public BeanTerDatosdeUnidadINF() {
super(TerDatosdeUnidadINF.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from terdatos_de_unidadinfor ";
List<TerDatosdeUnidadINF> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerDatosdeUnidadINF.class);
setListaEntidades(lListaTipo);
}
});

addPreTransaccionServicioListener(new PreTransaccionListener() {

@Override
public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
entidadRegistrar.setFechaRegistro(new Date());
entidadRegistrar.setFechaActualizacion(new Date());
entidadRegistrar.setFecha(new Date());

}
});

}

@Override
protected void cargarListaEtiquetas() {

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Datos de Unidad Informe de Operacion");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Datos de Unidad Informe de Operacion");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Datos de Unidad Informe de Operacion");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Datos de Unidad Informe de Operacion");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Datos de Unidad Informe de Operacion");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci�n de Datos de Unidad Informe de Operacion");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Datos de Unidad Informe de Operacion");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerDatosdeUnidadINF getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
