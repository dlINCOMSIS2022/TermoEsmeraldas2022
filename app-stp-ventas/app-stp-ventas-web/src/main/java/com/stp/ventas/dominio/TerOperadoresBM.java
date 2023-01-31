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
@Table(name = "teroperadoresbm")
public class TerOperadoresBM extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

 private Date fecha_ingreso;
 private Long iddato;

 private String apellidos;
 private Byte fotografia;
 private String idoperador;
 private String nombres;
 private String profesion;
 private String guardia;
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

public Date getFecha_ingreso() {
	return fecha_ingreso;
}

public void setFecha_ingreso(Date fecha_ingreso) {
	this.fecha_ingreso = fecha_ingreso;
}

public Long getIddato() {
	return iddato;
}

public void setIddato(Long iddato) {
	this.iddato = iddato;
}

public String getApellidos() {
	return apellidos;
}

public void setApellidos(String apellidos) {
	this.apellidos = apellidos;
}

public Byte getFotografia() {
	return fotografia;
}

public void setFotografia(Byte fotografia) {
	this.fotografia = fotografia;
}

public String getIdoperador() {
	return idoperador;
}

public void setIdoperador(String idoperador) {
	this.idoperador = idoperador;
}

public String getNombres() {
	return nombres;
}

public void setNombres(String nombres) {
	this.nombres = nombres;
}

public String getProfesion() {
	return profesion;
}

public void setProfesion(String profesion) {
	this.profesion = profesion;
}

public String getGuardia() {
	return guardia;
}

public void setGuardia(String guardia) {
	this.guardia = guardia;
}

public String getPuesto() {
	return puesto;
}

public void setPuesto(String puesto) {
	this.puesto = puesto;
}



}
