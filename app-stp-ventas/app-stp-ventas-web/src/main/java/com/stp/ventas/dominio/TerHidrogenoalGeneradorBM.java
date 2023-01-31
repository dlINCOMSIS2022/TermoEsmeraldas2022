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
@Table(name = "terhidrogeno_al_generadorbm")
public class TerHidrogenoalGeneradorBM extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

 private Long iddato;
 private Date fecha;
 private Double consumohorario;
 private Date hora;
 private Double masaintegrada;
 private Double pbotfin;
 private Double pbotini;
 private Double pgenfin;
 private Double pgenini;
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

public Double getConsumohorario() {
	return consumohorario;
}

public void setConsumohorario(Double consumohorario) {
	this.consumohorario = consumohorario;
}

public Date getHora() {
	return hora;
}

public void setHora(Date hora) {
	this.hora = hora;
}

public Double getMasaintegrada() {
	return masaintegrada;
}

public void setMasaintegrada(Double masaintegrada) {
	this.masaintegrada = masaintegrada;
}

public Double getPbotfin() {
	return pbotfin;
}

public void setPbotfin(Double pbotfin) {
	this.pbotfin = pbotfin;
}

public Double getPbotini() {
	return pbotini;
}

public void setPbotini(Double pbotini) {
	this.pbotini = pbotini;
}

public Double getPgenfin() {
	return pgenfin;
}

public void setPgenfin(Double pgenfin) {
	this.pgenfin = pgenfin;
}

public Double getPgenini() {
	return pgenini;
}

public void setPgenini(Double pgenini) {
	this.pgenini = pgenini;
}

public String getTurno() {
	return turno;
}

public void setTurno(String turno) {
	this.turno = turno;
}



}
