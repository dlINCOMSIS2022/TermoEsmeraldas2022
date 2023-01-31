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
@Table(name = "teranalisis_de_gasesbingt")
public class TerAnalisisGasesBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

 private Date fecha;
 private Long iddato;
 private String turno;

 private Double caudal_de_aire;
 private Double caudal_de_fuel;
 private Double exceso_de_aire;
 private Date hora;
 private String metodo;
 private Double porcentaje_c02;
 private Double porcentaje_o2;
 private Double potencia;
 private Double ppm_co;
 private Double ppm_nox;
 private Double ppm_so2;
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

public String getTurno() {
	return turno;
}

public void setTurno(String turno) {
	this.turno = turno;
}

public Double getCaudal_de_aire() {
	return caudal_de_aire;
}

public void setCaudal_de_aire(Double caudal_de_aire) {
	this.caudal_de_aire = caudal_de_aire;
}

public Double getCaudal_de_fuel() {
	return caudal_de_fuel;
}

public void setCaudal_de_fuel(Double caudal_de_fuel) {
	this.caudal_de_fuel = caudal_de_fuel;
}

public Double getExceso_de_aire() {
	return exceso_de_aire;
}

public void setExceso_de_aire(Double exceso_de_aire) {
	this.exceso_de_aire = exceso_de_aire;
}

public Date getHora() {
	return hora;
}

public void setHora(Date hora) {
	this.hora = hora;
}

public String getMetodo() {
	return metodo;
}

public void setMetodo(String metodo) {
	this.metodo = metodo;
}

public Double getPorcentaje_c02() {
	return porcentaje_c02;
}

public void setPorcentaje_c02(Double porcentaje_c02) {
	this.porcentaje_c02 = porcentaje_c02;
}

public Double getPorcentaje_o2() {
	return porcentaje_o2;
}

public void setPorcentaje_o2(Double porcentaje_o2) {
	this.porcentaje_o2 = porcentaje_o2;
}

public Double getPotencia() {
	return potencia;
}

public void setPotencia(Double potencia) {
	this.potencia = potencia;
}

public Double getPpm_co() {
	return ppm_co;
}

public void setPpm_co(Double ppm_co) {
	this.ppm_co = ppm_co;
}

public Double getPpm_nox() {
	return ppm_nox;
}

public void setPpm_nox(Double ppm_nox) {
	this.ppm_nox = ppm_nox;
}

public Double getPpm_so2() {
	return ppm_so2;
}

public void setPpm_so2(Double ppm_so2) {
	this.ppm_so2 = ppm_so2;
}

public String getUbicacion() {
	return ubicacion;
}

public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
}



}
