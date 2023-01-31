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
@Table(name = "terfotos_de_plantabingt")
public class TerFotosPlantaBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

private Date fecha;
private Long iddato;
private String turno;
private Byte fotos;
private String comentario;





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

public Byte getFotos() {
	return fotos;
}

public void setFotos(Byte fotos) {
	this.fotos = fotos;
}

public String getComentario() {
	return comentario;
}

public void setComentario(String comentario) {
	this.comentario = comentario;
}




}
