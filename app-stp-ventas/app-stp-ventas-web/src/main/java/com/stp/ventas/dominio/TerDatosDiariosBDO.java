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
@Table(name = "terdatos_diariosbdo")
public class TerDatosDiariosBDO extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;
 
 private Long iddato;
 private Date fecha;

 private Double c;
 private Double c1;
 private Double c2;
 private Double max;
 private Double max1;
 private Double max2;
 private Double min;
 private Double min1;
 private Double min2;
 private String parametro;
 private Double promedio;
 private Double promedio1;
 private Double promedio2;
 
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

public Date getFecha() {
	return fecha;
}

public void setFecha(Date fecha) {
	this.fecha = fecha;
}

public Double getC() {
	return c;
}

public void setC(Double c) {
	this.c = c;
}

public Double getC1() {
	return c1;
}

public void setC1(Double c1) {
	this.c1 = c1;
}

public Double getC2() {
	return c2;
}

public void setC2(Double c2) {
	this.c2 = c2;
}

public Double getMax() {
	return max;
}

public void setMax(Double max) {
	this.max = max;
}

public Double getMax1() {
	return max1;
}

public void setMax1(Double max1) {
	this.max1 = max1;
}

public Double getMax2() {
	return max2;
}

public void setMax2(Double max2) {
	this.max2 = max2;
}

public Double getMin() {
	return min;
}

public void setMin(Double min) {
	this.min = min;
}

public Double getMin1() {
	return min1;
}

public void setMin1(Double min1) {
	this.min1 = min1;
}

public Double getMin2() {
	return min2;
}

public void setMin2(Double min2) {
	this.min2 = min2;
}

public String getParametro() {
	return parametro;
}

public void setParametro(String parametro) {
	this.parametro = parametro;
}

public Double getPromedio() {
	return promedio;
}

public void setPromedio(Double promedio) {
	this.promedio = promedio;
}

public Double getPromedio1() {
	return promedio1;
}

public void setPromedio1(Double promedio1) {
	this.promedio1 = promedio1;
}

public Double getPromedio2() {
	return promedio2;
}

public void setPromedio2(Double promedio2) {
	this.promedio2 = promedio2;
}



}
