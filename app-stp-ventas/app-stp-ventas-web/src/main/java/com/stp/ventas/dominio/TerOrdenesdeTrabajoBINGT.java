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
@Table(name = "terordenes_de_trabajobingt")
public class TerOrdenesdeTrabajoBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

  private Date fecha;
   private Long iddato;
  private String turno;

 private String porct_avance;
 private String area_mntto;
 private String equipo_no;
private String fa;
private String fc;
private String fe;
private Boolean oa;
private Boolean oc;
private Boolean oe;
 private String prioridad;
 private String status_x_mnto;
private String ta;
private String tc;
private String te;
 private String texto_de_la_odt;


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

public String getPorct_avance() {
	return porct_avance;
}

public void setPorct_avance(String porct_avance) {
	this.porct_avance = porct_avance;
}

public String getArea_mntto() {
	return area_mntto;
}

public void setArea_mntto(String area_mntto) {
	this.area_mntto = area_mntto;
}

public String getEquipo_no() {
	return equipo_no;
}

public void setEquipo_no(String equipo_no) {
	this.equipo_no = equipo_no;
}

public String getFa() {
	return fa;
}

public void setFa(String fa) {
	this.fa = fa;
}

public String getFc() {
	return fc;
}

public void setFc(String fc) {
	this.fc = fc;
}

public String getFe() {
	return fe;
}

public void setFe(String fe) {
	this.fe = fe;
}

public Boolean getOa() {
	return oa;
}

public void setOa(Boolean oa) {
	this.oa = oa;
}

public Boolean getOc() {
	return oc;
}

public void setOc(Boolean oc) {
	this.oc = oc;
}

public Boolean getOe() {
	return oe;
}

public void setOe(Boolean oe) {
	this.oe = oe;
}

public String getPrioridad() {
	return prioridad;
}

public void setPrioridad(String prioridad) {
	this.prioridad = prioridad;
}

public String getStatus_x_mnto() {
	return status_x_mnto;
}

public void setStatus_x_mnto(String status_x_mnto) {
	this.status_x_mnto = status_x_mnto;
}

public String getTa() {
	return ta;
}

public void setTa(String ta) {
	this.ta = ta;
}

public String getTc() {
	return tc;
}

public void setTc(String tc) {
	this.tc = tc;
}

public String getTe() {
	return te;
}

public void setTe(String te) {
	this.te = te;
}

public String getTexto_de_la_odt() {
	return texto_de_la_odt;
}

public void setTexto_de_la_odt(String texto_de_la_odt) {
	this.texto_de_la_odt = texto_de_la_odt;
}




}
