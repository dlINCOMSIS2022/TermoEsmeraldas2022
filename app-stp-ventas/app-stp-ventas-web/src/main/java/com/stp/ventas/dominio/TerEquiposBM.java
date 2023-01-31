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
@Table(name = "terequiposbm")
public class TerEquiposBM extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

 private Long iddato;

 
 private Boolean secuencia_negativa_46;
 private Boolean sobrecarga_49;
 private Boolean prot_sobrecorriente_50_51;
 private Boolean falla_a_la_tierra_64;
  private String barra;
  private String celda;
  private String codigo;
  private Double corriente;
  private String denominacion;
  private Integer numero;
  private Double potencia_kva;
  private String tipo_equipo;
  private String ubicacion;
  private Double voltaje;
  private Double voltaje2;


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

public Boolean getSecuencia_negativa_46() {
	return secuencia_negativa_46;
}

public void setSecuencia_negativa_46(Boolean secuencia_negativa_46) {
	this.secuencia_negativa_46 = secuencia_negativa_46;
}

public Boolean getSobrecarga_49() {
	return sobrecarga_49;
}

public void setSobrecarga_49(Boolean sobrecarga_49) {
	this.sobrecarga_49 = sobrecarga_49;
}

public Boolean getProt_sobrecorriente_50_51() {
	return prot_sobrecorriente_50_51;
}

public void setProt_sobrecorriente_50_51(Boolean prot_sobrecorriente_50_51) {
	this.prot_sobrecorriente_50_51 = prot_sobrecorriente_50_51;
}

public Boolean getFalla_a_la_tierra_64() {
	return falla_a_la_tierra_64;
}

public void setFalla_a_la_tierra_64(Boolean falla_a_la_tierra_64) {
	this.falla_a_la_tierra_64 = falla_a_la_tierra_64;
}

public String getBarra() {
	return barra;
}

public void setBarra(String barra) {
	this.barra = barra;
}

public String getCelda() {
	return celda;
}

public void setCelda(String celda) {
	this.celda = celda;
}

public String getCodigo() {
	return codigo;
}

public void setCodigo(String codigo) {
	this.codigo = codigo;
}

public Double getCorriente() {
	return corriente;
}

public void setCorriente(Double corriente) {
	this.corriente = corriente;
}

public String getDenominacion() {
	return denominacion;
}

public void setDenominacion(String denominacion) {
	this.denominacion = denominacion;
}

public Integer getNumero() {
	return numero;
}

public void setNumero(Integer numero) {
	this.numero = numero;
}

public Double getPotencia_kva() {
	return potencia_kva;
}

public void setPotencia_kva(Double potencia_kva) {
	this.potencia_kva = potencia_kva;
}

public String getTipo_equipo() {
	return tipo_equipo;
}

public void setTipo_equipo(String tipo_equipo) {
	this.tipo_equipo = tipo_equipo;
}

public String getUbicacion() {
	return ubicacion;
}

public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
}

public Double getVoltaje() {
	return voltaje;
}

public void setVoltaje(Double voltaje) {
	this.voltaje = voltaje;
}

public Double getVoltaje2() {
	return voltaje2;
}

public void setVoltaje2(Double voltaje2) {
	this.voltaje2 = voltaje2;
}



}
