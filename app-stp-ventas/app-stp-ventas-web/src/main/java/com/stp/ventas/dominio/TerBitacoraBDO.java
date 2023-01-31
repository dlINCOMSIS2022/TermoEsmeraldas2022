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
@Table(name = "terbitacorabdo")
public class TerBitacoraBDO extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;
 
 private Long iddato;
 private Date fecha;

 private Boolean ejecutado;
 private Date fecha_final;
 private Integer idcomentario;
 private String nota;
 private Integer plazo;

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

public Boolean getEjecutado() {
	return ejecutado;
}

public void setEjecutado(Boolean ejecutado) {
	this.ejecutado = ejecutado;
}

public Date getFecha_final() {
	return fecha_final;
}

public void setFecha_final(Date fecha_final) {
	this.fecha_final = fecha_final;
}

public Integer getIdcomentario() {
	return idcomentario;
}

public void setIdcomentario(Integer idcomentario) {
	this.idcomentario = idcomentario;
}

public String getNota() {
	return nota;
}

public void setNota(String nota) {
	this.nota = nota;
}

public Integer getPlazo() {
	return plazo;
}

public void setPlazo(Integer plazo) {
	this.plazo = plazo;
}


}
