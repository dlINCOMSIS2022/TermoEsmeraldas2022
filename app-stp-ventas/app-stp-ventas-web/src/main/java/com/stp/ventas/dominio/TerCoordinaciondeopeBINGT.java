package com.stp.ventas.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "tercoordinacion_deope_demanbingt")
public class TerCoordinaciondeopeBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

private Date fecha;
private Long iddato;

private Integer porct_avance;
private String actividad_solicitada;
private String ejecucion;
private Date fecha_um;
private String prioridad;
private String seccion;

/*
 * @GeneratedValue(generator = "terparametros_de_unidadbt", strategy =
 * GenerationType.SEQUENCE)
 * 
 * @SequenceGenerator(name = "terparametros_de_unidadbt", allocationSize = 1,
 * initialValue = 1, sequenceName = "SEC_TERPARAMETROS_DE_UNIDAD")
 */
@Id
@Column(name = "id")
public Long getId() {
	return id;
}

@Override
public void setIdReferencia(Long idReferencia) {
//TODO Auto-generated method stub
	System.out.println("Id referencia");
}

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public Long getIddato() {
	return iddato;
}

public void setIddato(Long iddato) {
	this.iddato = iddato;
}

public Integer getPorct_avance() {
	return porct_avance;
}

public void setPorct_avance(Integer porct_avance) {
	this.porct_avance = porct_avance;
}

public String getActividad_solicitada() {
	return actividad_solicitada;
}

public void setActividad_solicitada(String actividad_solicitada) {
	this.actividad_solicitada = actividad_solicitada;
}

public String getEjecucion() {
	return ejecucion;
}

public void setEjecucion(String ejecucion) {
	this.ejecucion = ejecucion;
}

public Date getFecha_um() {
	return fecha_um;
}

public void setFecha_um(Date fecha_um) {
	this.fecha_um = fecha_um;
}

public String getPrioridad() {
	return prioridad;
}

public void setPrioridad(String prioridad) {
	this.prioridad = prioridad;
}

public String getSeccion() {
	return seccion;
}

public void setSeccion(String seccion) {
	this.seccion = seccion;
}


}
