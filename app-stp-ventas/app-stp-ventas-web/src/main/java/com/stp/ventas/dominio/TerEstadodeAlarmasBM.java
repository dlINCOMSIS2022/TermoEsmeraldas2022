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
@Table(name = "terestado_de_alarmasbm")
public class TerEstadodeAlarmasBM extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

  private Long iddato;

  private String codalarma;
  private String color;
  private String descripcion;
  private String estado;
  private Byte imagen;
  private String tablero;

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

public String getCodalarma() {
	return codalarma;
}

public void setCodalarma(String codalarma) {
	this.codalarma = codalarma;
}

public String getColor() {
	return color;
}

public void setColor(String color) {
	this.color = color;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

public String getEstado() {
	return estado;
}

public void setEstado(String estado) {
	this.estado = estado;
}

public Byte getImagen() {
	return imagen;
}

public void setImagen(Byte imagen) {
	this.imagen = imagen;
}

public String getTablero() {
	return tablero;
}

public void setTablero(String tablero) {
	this.tablero = tablero;
}



}
