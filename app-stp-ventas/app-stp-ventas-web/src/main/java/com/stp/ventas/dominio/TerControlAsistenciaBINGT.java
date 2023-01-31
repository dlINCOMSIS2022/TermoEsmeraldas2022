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
@Table(name = "tercontrol_de_asistenciabingt")
public class TerControlAsistenciaBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

 private Date fecha;
 private Long iddato;
 private String turno;

private String codigo_asistencia;
private String comentario;
 private String guardia_adicional;
 private String guardia_normal;
 private String operador;
 private String puesto;



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

public String getCodigo_asistencia() {
	return codigo_asistencia;
}

public void setCodigo_asistencia(String codigo_asistencia) {
	this.codigo_asistencia = codigo_asistencia;
}

public String getComentario() {
	return comentario;
}

public void setComentario(String comentario) {
	this.comentario = comentario;
}

public String getGuardia_adicional() {
	return guardia_adicional;
}

public void setGuardia_adicional(String guardia_adicional) {
	this.guardia_adicional = guardia_adicional;
}

public String getGuardia_normal() {
	return guardia_normal;
}

public void setGuardia_normal(String guardia_normal) {
	this.guardia_normal = guardia_normal;
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


}
