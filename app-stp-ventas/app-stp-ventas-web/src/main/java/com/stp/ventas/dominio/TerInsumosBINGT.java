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
@Table(name = "terinsumosbingt")
public class TerInsumosBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

private Date fecha;
private Long iddato;
private String turno;

private Integer co2;
private Integer hidrogeno;
private Integer nitrogeno;

private Double nivel_tanque_js_s1;
private Double nivel_tanque_js_s2;
private Double nivel_tanque_js_s3;

private Double nivel_tanque_js_s4;
private Double nivel_tanque_mk_s2;
private Double nivel_tanque_my_s2;





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

public Integer getCo2() {
	return co2;
}

public void setCo2(Integer co2) {
	this.co2 = co2;
}

public Integer getHidrogeno() {
	return hidrogeno;
}

public void setHidrogeno(Integer hidrogeno) {
	this.hidrogeno = hidrogeno;
}

public Integer getNitrogeno() {
	return nitrogeno;
}

public void setNitrogeno(Integer nitrogeno) {
	this.nitrogeno = nitrogeno;
}

public Double getNivel_tanque_js_s1() {
	return nivel_tanque_js_s1;
}

public void setNivel_tanque_js_s1(Double nivel_tanque_js_s1) {
	this.nivel_tanque_js_s1 = nivel_tanque_js_s1;
}

public Double getNivel_tanque_js_s2() {
	return nivel_tanque_js_s2;
}

public void setNivel_tanque_js_s2(Double nivel_tanque_js_s2) {
	this.nivel_tanque_js_s2 = nivel_tanque_js_s2;
}

public Double getNivel_tanque_js_s3() {
	return nivel_tanque_js_s3;
}

public void setNivel_tanque_js_s3(Double nivel_tanque_js_s3) {
	this.nivel_tanque_js_s3 = nivel_tanque_js_s3;
}

public Double getNivel_tanque_js_s4() {
	return nivel_tanque_js_s4;
}

public void setNivel_tanque_js_s4(Double nivel_tanque_js_s4) {
	this.nivel_tanque_js_s4 = nivel_tanque_js_s4;
}

public Double getNivel_tanque_mk_s2() {
	return nivel_tanque_mk_s2;
}

public void setNivel_tanque_mk_s2(Double nivel_tanque_mk_s2) {
	this.nivel_tanque_mk_s2 = nivel_tanque_mk_s2;
}

public Double getNivel_tanque_my_s2() {
	return nivel_tanque_my_s2;
}

public void setNivel_tanque_my_s2(Double nivel_tanque_my_s2) {
	this.nivel_tanque_my_s2 = nivel_tanque_my_s2;
}


}
