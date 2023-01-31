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
@Table(name = "terquemadoresbc")
public class TerQuemadoresBC extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;


private Double fuel_1a;
private Double fuel_1b;
private Double fuel_1c;
private Double fuel_1d;
private Double fuel_2a;
private Double fuel_2b;
private Double fuel_2c;
private Double fuel_2d;
private Integer idregistro;
private Double vapor_1a;
private Double vapor_1b;
private Double vapor_1c;
private Double vapor_1d;
private Double vapor_2a;
private Double vapor_2b;
private Double vapor_2c;
private Double vapor_2d;

private Date fecha;
private Long iddato;
private String turno;



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

public Double getFuel_1a() {
	return fuel_1a;
}

public void setFuel_1a(Double fuel_1a) {
	this.fuel_1a = fuel_1a;
}

public Double getFuel_1b() {
	return fuel_1b;
}

public void setFuel_1b(Double fuel_1b) {
	this.fuel_1b = fuel_1b;
}

public Double getFuel_1c() {
	return fuel_1c;
}

public void setFuel_1c(Double fuel_1c) {
	this.fuel_1c = fuel_1c;
}

public Double getFuel_1d() {
	return fuel_1d;
}

public void setFuel_1d(Double fuel_1d) {
	this.fuel_1d = fuel_1d;
}

public Double getFuel_2a() {
	return fuel_2a;
}

public void setFuel_2a(Double fuel_2a) {
	this.fuel_2a = fuel_2a;
}

public Double getFuel_2b() {
	return fuel_2b;
}

public void setFuel_2b(Double fuel_2b) {
	this.fuel_2b = fuel_2b;
}

public Double getFuel_2c() {
	return fuel_2c;
}

public void setFuel_2c(Double fuel_2c) {
	this.fuel_2c = fuel_2c;
}

public Double getFuel_2d() {
	return fuel_2d;
}

public void setFuel_2d(Double fuel_2d) {
	this.fuel_2d = fuel_2d;
}

public Integer getIdregistro() {
	return idregistro;
}

public void setIdregistro(Integer idregistro) {
	this.idregistro = idregistro;
}

public Double getVapor_1a() {
	return vapor_1a;
}

public void setVapor_1a(Double vapor_1a) {
	this.vapor_1a = vapor_1a;
}

public Double getVapor_1b() {
	return vapor_1b;
}

public void setVapor_1b(Double vapor_1b) {
	this.vapor_1b = vapor_1b;
}

public Double getVapor_1c() {
	return vapor_1c;
}

public void setVapor_1c(Double vapor_1c) {
	this.vapor_1c = vapor_1c;
}

public Double getVapor_1d() {
	return vapor_1d;
}

public void setVapor_1d(Double vapor_1d) {
	this.vapor_1d = vapor_1d;
}

public Double getVapor_2a() {
	return vapor_2a;
}

public void setVapor_2a(Double vapor_2a) {
	this.vapor_2a = vapor_2a;
}

public Double getVapor_2b() {
	return vapor_2b;
}

public void setVapor_2b(Double vapor_2b) {
	this.vapor_2b = vapor_2b;
}

public Double getVapor_2c() {
	return vapor_2c;
}

public void setVapor_2c(Double vapor_2c) {
	this.vapor_2c = vapor_2c;
}

public Double getVapor_2d() {
	return vapor_2d;
}

public void setVapor_2d(Double vapor_2d) {
	this.vapor_2d = vapor_2d;
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



}
