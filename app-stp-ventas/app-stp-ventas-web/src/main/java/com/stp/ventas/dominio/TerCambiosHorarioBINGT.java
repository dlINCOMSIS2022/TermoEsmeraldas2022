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
@Table(name = "tercambios_de_horariobingt")
public class TerCambiosHorarioBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;


 private Long iddato;

 private Boolean autorizado;
 private Date fecha_asistencia;
 private Date fecha_viene;
 private String jefe_turno;
 private Integer numero_de_cambio;
 private String operador;
 private String puesto;
 private String turno_asistencia;
 private String turno_viene;


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

public Boolean getAutorizado() {
	return autorizado;
}

public void setAutorizado(Boolean autorizado) {
	this.autorizado = autorizado;
}

public Date getFecha_asistencia() {
	return fecha_asistencia;
}

public void setFecha_asistencia(Date fecha_asistencia) {
	this.fecha_asistencia = fecha_asistencia;
}

public Date getFecha_viene() {
	return fecha_viene;
}

public void setFecha_viene(Date fecha_viene) {
	this.fecha_viene = fecha_viene;
}

public String getJefe_turno() {
	return jefe_turno;
}

public void setJefe_turno(String jefe_turno) {
	this.jefe_turno = jefe_turno;
}

public Integer getNumero_de_cambio() {
	return numero_de_cambio;
}

public void setNumero_de_cambio(Integer numero_de_cambio) {
	this.numero_de_cambio = numero_de_cambio;
}

public String getOperador() {
	return operador;
}

public void setOperador(String operador) {
	this.operador = operador;
}

public String getPuesto() {
	return puesto;
}

public void setPuesto(String puesto) {
	this.puesto = puesto;
}

public String getTurno_asistencia() {
	return turno_asistencia;
}

public void setTurno_asistencia(String turno_asistencia) {
	this.turno_asistencia = turno_asistencia;
}

public String getTurno_viene() {
	return turno_viene;
}

public void setTurno_viene(String turno_viene) {
	this.turno_viene = turno_viene;
}



}
