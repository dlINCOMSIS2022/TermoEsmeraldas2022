package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerControlAsistenciaBINGT;
import com.stp.ventas.eao.TerControlAsistenciaBINGTEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerControlAsistenciaBINGT;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerControlAsistenciaBINGT extends BeanMantenedorGenerico<ServicioMantenedorTerControlAsistenciaBINGT,Long,TerControlAsistenciaBINGT,TerControlAsistenciaBINGTEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerControlAsistenciaBINGT servicioParUni;

public BeanTerControlAsistenciaBINGT() {
super(TerControlAsistenciaBINGT.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from terControlAsistenciabingt ";
List<TerControlAsistenciaBINGT> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerControlAsistenciaBINGT.class);
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

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Control Asistencia Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Control Asistencia Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Control Asistencia Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Control Asistencia Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Control Asistencia Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci�n de Control Asistencia Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Control Asistencia Bitacora Ing de Turno");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerControlAsistenciaBINGT getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
