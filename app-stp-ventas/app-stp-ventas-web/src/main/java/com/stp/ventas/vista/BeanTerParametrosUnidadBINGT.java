package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerParametrosUnidadBINGT;
import com.stp.ventas.eao.TerParametrosUnidadBINGTEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerParametrosUnidadBINGT;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerParametrosUnidadBINGT extends BeanMantenedorGenerico<ServicioMantenedorTerParametrosUnidadBINGT,Long,TerParametrosUnidadBINGT,TerParametrosUnidadBINGTEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerParametrosUnidadBINGT servicioParUni;

public BeanTerParametrosUnidadBINGT() {
super(TerParametrosUnidadBINGT.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from terparametros_de_unidadbingt ";
List<TerParametrosUnidadBINGT> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerParametrosUnidadBINGT.class);
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

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Parametros de Unidad Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Parametros de Unidad Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Parametros de Unidad Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Parametros de Unidad Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Parametros de Unidad Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci�n de Parametros de unidad Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Parametros de unidad Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerParametrosUnidadBINGT getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
