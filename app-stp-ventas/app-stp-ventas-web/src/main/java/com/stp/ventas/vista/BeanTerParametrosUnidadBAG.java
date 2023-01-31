package com.stp.ventas.vista;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.stp.ventas.dominio.TerParametrosUnidadBAG;
import com.stp.ventas.eao.TerParametrosUnidadBAGEAO;
import com.stp.ventas.servicios.ServicioMantenedorTerParametrosUnidadBAG;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanTerParametrosUnidadBAG extends BeanMantenedorGenerico<ServicioMantenedorTerParametrosUnidadBAG,Long,TerParametrosUnidadBAG,TerParametrosUnidadBAGEAO> {

private static final long serialVersionUID = 1L;

@EJB
private ServicioMantenedorTerParametrosUnidadBAG servicioParUni;

public BeanTerParametrosUnidadBAG() {
super(TerParametrosUnidadBAG.class);
// TODO Auto-generated constructor stub

addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

@Override
public void cargarListaEntidades() {
HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
String lQuery = "select * from terparametrosunidadbag ";
List<TerParametrosUnidadBAG> lListaTipo = servicioParUni.ejecutarQueryNativoObjeto(lQuery,
lParametrosEmpresa, TerParametrosUnidadBAG.class);
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

this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Parametros de Unidad");
this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Parametros de Unidad");
this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Parametros de Unidad");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Parametros de Unidad");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Parametros de Unidad");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualizaci�n de Parametros de unidad");
this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Paramteros de unidad");
this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

}

@Override
protected ServicioMantenedorTerParametrosUnidadBAG getServicioMantenedor() {
// TODO Auto-generated method stub
return servicioParUni;
}

}
