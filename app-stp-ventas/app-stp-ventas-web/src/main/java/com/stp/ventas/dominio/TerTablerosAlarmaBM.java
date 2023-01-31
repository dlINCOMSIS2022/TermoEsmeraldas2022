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
@Table(name = "tertableros_alarmabm")
public class TerTablerosAlarmaBM extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

  private Long iddato;

  private String descripcion_tablero;
  private String tablero;
  
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

public String getDescripcion_tablero() {
	return descripcion_tablero;
}

public void setDescripcion_tablero(String descripcion_tablero) {
	this.descripcion_tablero = descripcion_tablero;
}

public String getTablero() {
	return tablero;
}

public void setTablero(String tablero) {
	this.tablero = tablero;
}



}
