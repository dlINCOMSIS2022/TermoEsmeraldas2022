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
@Table(name = "terrestriccionesbingt")
public class TerRestriccionesBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

 private Date fecha;
 private Long iddato;

 private String equipo;
 private Date fecha_levantamiento;
 private Date fecha_prog_levantar;
 private Integer num_odt;
 private String restriccion;
 private String subsistema;
 private String turno;
 private Boolean vigencia_restriccion;


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

public String getEquipo() {
	return equipo;
}

public void setEquipo(String equipo) {
	this.equipo = equipo;
}

public Date getFecha_levantamiento() {
	return fecha_levantamiento;
}

public void setFecha_levantamiento(Date fecha_levantamiento) {
	this.fecha_levantamiento = fecha_levantamiento;
}

public Date getFecha_prog_levantar() {
	return fecha_prog_levantar;
}

public void setFecha_prog_levantar(Date fecha_prog_levantar) {
	this.fecha_prog_levantar = fecha_prog_levantar;
}

public Integer getNum_odt() {
	return num_odt;
}

public void setNum_odt(Integer num_odt) {
	this.num_odt = num_odt;
}

public String getRestriccion() {
	return restriccion;
}

public void setRestriccion(String restriccion) {
	this.restriccion = restriccion;
}

public String getSubsistema() {
	return subsistema;
}

public void setSubsistema(String subsistema) {
	this.subsistema = subsistema;
}

public String getTurno() {
	return turno;
}

public void setTurno(String turno) {
	this.turno = turno;
}

public Boolean getVigencia_restriccion() {
	return vigencia_restriccion;
}

public void setVigencia_restriccion(Boolean vigencia_restriccion) {
	this.vigencia_restriccion = vigencia_restriccion;
}


}
