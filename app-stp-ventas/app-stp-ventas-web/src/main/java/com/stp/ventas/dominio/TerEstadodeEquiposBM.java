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
@Table(name = "terestado_de_equiposbm")
public class TerEstadodeEquiposBM extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

  private Long iddato;

  private Byte imagen;
  private String codigoequipo;
  private String estado_equipo;
  private Double inominal;
  private String nombre_equipo;
  private String ubicacion;

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

public Long getIddato() {
	return iddato;
}

public void setIddato(Long iddato) {
	this.iddato = iddato;
}

public Byte getImagen() {
	return imagen;
}

public void setImagen(Byte imagen) {
	this.imagen = imagen;
}

public String getCodigoequipo() {
	return codigoequipo;
}

public void setCodigoequipo(String codigoequipo) {
	this.codigoequipo = codigoequipo;
}

public String getEstado_equipo() {
	return estado_equipo;
}

public void setEstado_equipo(String estado_equipo) {
	this.estado_equipo = estado_equipo;
}

public Double getInominal() {
	return inominal;
}

public void setInominal(Double inominal) {
	this.inominal = inominal;
}

public String getNombre_equipo() {
	return nombre_equipo;
}

public void setNombre_equipo(String nombre_equipo) {
	this.nombre_equipo = nombre_equipo;
}

public String getUbicacion() {
	return ubicacion;
}

public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
}



}
