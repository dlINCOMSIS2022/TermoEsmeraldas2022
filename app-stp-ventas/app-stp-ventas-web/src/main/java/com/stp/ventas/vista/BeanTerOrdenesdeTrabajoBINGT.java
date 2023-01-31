package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerOrdenesdeTrabajoBINGT;
import com.stp.ventas.eao.TerOrdenesdeTrabajoBINGTEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerOrdenesdeTrabajoBINGT;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerOrdenesdeTrabajoBINGT extends BeanMantenedorGenerico<ServicioMantenedorTerOrdenesdeTrabajoBINGT,Long,TerOrdenesdeTrabajoBINGT,TerOrdenesdeTrabajoBINGTEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerOrdenesdeTrabajoBINGT servicioParUni;

public BeanTerOrdenesdeTrabajoBINGT() {
super(TerOrdenesdeTrabajoBINGT.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from terordenes_de_trabajobingt ";
List<TerOrdenesdeTrabajoBINGT> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerOrdenesdeTrabajoBINGT.class);
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

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Ordenes de Trabajo ODT Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Ordenes de Trabajo ODT Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Ordenes de Trabajo ODT Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Ordenes de Trabajo ODT Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Ordenes de Trabajo ODT Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci�n de Ordenes de Trabajo ODT Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Ordenes de Trabajo ODT Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerOrdenesdeTrabajoBINGT getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
