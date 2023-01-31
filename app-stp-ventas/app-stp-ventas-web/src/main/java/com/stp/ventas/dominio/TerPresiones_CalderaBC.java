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
@Table(name = "terpresiones_calderabc")
public class TerPresiones_CalderaBC extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;


private Date hora;
private Double pt_030;
private Double pt_031;
private Double pt_032;
private Double pt_035a;
private Double pt_035b;
private Double pt_036l;
private Double pt_038;
private Double pt_042;
private Double pt_043;
private Double fit_043t;


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


public Double getFit_043t() {
	return fit_043t;
}

public void setFit_043t(Double fit_043t) {
	this.fit_043t = fit_043t;
}

public Date getHora() {
	return hora;
}

public void setHora(Date hora) {
	this.hora = hora;
}

public Double getPt_030() {
	return pt_030;
}

public void setPt_030(Double pt_030) {
	this.pt_030 = pt_030;
}

public Double getPt_031() {
	return pt_031;
}

public void setPt_031(Double pt_031) {
	this.pt_031 = pt_031;
}

public Double getPt_032() {
	return pt_032;
}

public void setPt_032(Double pt_032) {
	this.pt_032 = pt_032;
}

public Double getPt_035a() {
	return pt_035a;
}

public void setPt_035a(Double pt_035a) {
	this.pt_035a = pt_035a;
}

public Double getPt_035b() {
	return pt_035b;
}

public void setPt_035b(Double pt_035b) {
	this.pt_035b = pt_035b;
}

public Double getPt_036l() {
	return pt_036l;
}

public void setPt_036l(Double pt_036l) {
	this.pt_036l = pt_036l;
}

public Double getPt_038() {
	return pt_038;
}

public void setPt_038(Double pt_038) {
	this.pt_038 = pt_038;
}

public Double getPt_042() {
	return pt_042;
}

public void setPt_042(Double pt_042) {
	this.pt_042 = pt_042;
}

public Double getPt_043() {
	return pt_043;
}

public void setPt_043(Double pt_043) {
	this.pt_043 = pt_043;
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
