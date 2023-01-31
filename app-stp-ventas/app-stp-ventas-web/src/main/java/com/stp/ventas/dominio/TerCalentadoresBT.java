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
@Table(name = "calentadores_ciclobt")
public class TerCalentadoresBT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;
private Date fecha;
private Long iddato;

private Double hora;
private Double l_r1;
private Double l_r2;
private Double l_r3;
private Double desareador;
private Double l_r5;
private Double l_r6;
private Double presion_crossover;
private Double temperatura_crossover;

private String turno;




@GeneratedValue(generator = "calentadores_ciclobt", strategy = GenerationType.SEQUENCE)
@SequenceGenerator(name = "calentadores_ciclobt", allocationSize = 1, initialValue = 1, sequenceName = "SEC_TerCalentadoresBT")
@Id
@Column(name = "id")
public Long getId() {
return id;
}


@Override
public void setIdReferencia(Long idReferencia) {
// TODO Auto-generated method stub
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

public Double getHora() {
	return hora;
}

public void setHora(Double hora) {
	this.hora = hora;
}

public Double getL_r1() {
	return l_r1;
}

public void setL_r1(Double l_r1) {
	this.l_r1 = l_r1;
}

public Double getL_r2() {
	return l_r2;
}

public void setL_r2(Double l_r2) {
	this.l_r2 = l_r2;
}

public Double getL_r3() {
	return l_r3;
}

public void setL_r3(Double l_r3) {
	this.l_r3 = l_r3;
}

public Double getDesareador() {
	return desareador;
}

public void setDesareador(Double desareador) {
	this.desareador = desareador;
}

public Double getL_r5() {
	return l_r5;
}

public void setL_r5(Double l_r5) {
	this.l_r5 = l_r5;
}

public Double getL_r6() {
	return l_r6;
}

public void setL_r6(Double l_r6) {
	this.l_r6 = l_r6;
}

public Double getPresion_crossover() {
	return presion_crossover;
}

public void setPresion_crossover(Double presion_crossover) {
	this.presion_crossover = presion_crossover;
}

public Double getTemperatura_crossover() {
	return temperatura_crossover;
}

public void setTemperatura_crossover(Double temperatura_crossover) {
	this.temperatura_crossover = temperatura_crossover;
}

public String getTurno() {
	return turno;
}

public void setTurno(String turno) {
	this.turno = turno;
}


}
