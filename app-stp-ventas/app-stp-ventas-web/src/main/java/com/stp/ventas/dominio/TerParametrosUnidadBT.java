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
@Table(name = "terparametros_de_unidadbt")
public class TerParametrosUnidadBT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;
private Date fecha;
private Long iddato;

private Double potencia_activa;
private Integer potencia_reactiva;
private Double voltaje_generador;
private Double presion_de_vapor_sh;
private Double temperatura_vapor_rh;
private Double presion_vapor_camara_rueda;
private Double temp_vapor_camara_rueda;
private Double temp_metal_camara_rueda;
private Double pvtd;
private Double pvti;
private Double pvid;
private Double pvii;
private Double vacio_del_condensador;
private Double p_bomba_aceite_principal;
private Double presion_lubricacion;
private Double presion_impeller;
private Double nivel_condensador;
private Double presion_h2;
private Double presion_ac_sello_anterior;
private Double presion_ac_sello_posterior;

private Double presion_desc_condensador;
private Double p_vapor_de_sello;
private Double temp_vapor_de_seloo;

private String turno;




@GeneratedValue(generator = "terparametros_de_unidadbt", strategy = GenerationType.SEQUENCE)
@SequenceGenerator(name = "terparametros_de_unidadbt", allocationSize = 1, initialValue = 1, sequenceName = "SEC_TERPARAMETROS_DE_UNIDADBT")
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
@Column(name="potencia_activa")
public Double getPotencia_activa() {
	return potencia_activa;
}

public void setPotencia_activa(Double potencia_activa) {
	this.potencia_activa = potencia_activa;
}

public Integer getPotencia_reactiva() {
	return potencia_reactiva;
}

public void setPotencia_reactiva(Integer potencia_reactiva) {
	this.potencia_reactiva = potencia_reactiva;
}

public Double getVoltaje_generador() {
	return voltaje_generador;
}

public void setVoltaje_generador(Double voltaje_generador) {
	this.voltaje_generador = voltaje_generador;
}

public Double getPresion_de_vapor_sh() {
	return presion_de_vapor_sh;
}

public void setPresion_de_vapor_sh(Double presion_de_vapor_sh) {
	this.presion_de_vapor_sh = presion_de_vapor_sh;
}

public Double getTemperatura_vapor_rh() {
	return temperatura_vapor_rh;
}

public void setTemperatura_vapor_rh(Double temperatura_vapor_rh) {
	this.temperatura_vapor_rh = temperatura_vapor_rh;
}

public Double getPresion_vapor_camara_rueda() {
	return presion_vapor_camara_rueda;
}

public void setPresion_vapor_camara_rueda(Double presion_vapor_camara_rueda) {
	this.presion_vapor_camara_rueda = presion_vapor_camara_rueda;
}

public Double getTemp_vapor_camara_rueda() {
	return temp_vapor_camara_rueda;
}

public void setTemp_vapor_camara_rueda(Double temp_vapor_camara_rueda) {
	this.temp_vapor_camara_rueda = temp_vapor_camara_rueda;
}

public Double getTemp_metal_camara_rueda() {
	return temp_metal_camara_rueda;
}

public void setTemp_metal_camara_rueda(Double temp_metal_camara_rueda) {
	this.temp_metal_camara_rueda = temp_metal_camara_rueda;
}

public Double getPvtd() {
	return pvtd;
}

public void setPvtd(Double pvtd) {
	this.pvtd = pvtd;
}

public Double getPvti() {
	return pvti;
}

public void setPvti(Double pvti) {
	this.pvti = pvti;
}

public Double getPvid() {
	return pvid;
}

public void setPvid(Double pvid) {
	this.pvid = pvid;
}

public Double getPvii() {
	return pvii;
}

public void setPvii(Double pvii) {
	this.pvii = pvii;
}

public Double getVacio_del_condensador() {
	return vacio_del_condensador;
}

public void setVacio_del_condesador(Double vacio_del_condensador) {
	this.vacio_del_condensador = vacio_del_condensador;
}

public Double getP_bomba_aceite_principal() {
	return p_bomba_aceite_principal;
}

public void setP_bomba_aceite_principal(Double p_bomba_aceite_principal) {
	this.p_bomba_aceite_principal = p_bomba_aceite_principal;
}

public Double getPresion_lubricacion() {
	return presion_lubricacion;
}

public void setPresion_lubricacion(Double presion_lubricacion) {
	this.presion_lubricacion = presion_lubricacion;
}

public Double getPresion_impeller() {
	return presion_impeller;
}

public void setPresion_impeller(Double presion_impeller) {
	this.presion_impeller = presion_impeller;
}

public Double getNivel_condensador() {
	return nivel_condensador;
}

public void setNivel_condensador(Double nivel_condensador) {
	this.nivel_condensador = nivel_condensador;
}

public Double getPresion_h2() {
	return presion_h2;
}

public void setPresion_h2(Double presion_h2) {
	this.presion_h2 = presion_h2;
}

public Double getPresion_ac_sello_anterior() {
	return presion_ac_sello_anterior;
}

public void setPresion_ac_sello_anterior(Double presion_ac_sello_anterior) {
	this.presion_ac_sello_anterior = presion_ac_sello_anterior;
}

public Double getPresion_ac_sello_posterior() {
	return presion_ac_sello_posterior;
}

public void setPresion_ac_sello_posterior(Double presion_ac_sello_posterior) {
	this.presion_ac_sello_posterior = presion_ac_sello_posterior;
}

public Double getPresion_desc_condensador() {
	return presion_desc_condensador;
}

public void setPresion_desc_condensador(Double presion_desc_condensador) {
	this.presion_desc_condensador = presion_desc_condensador;
}

public Double getTemp_vapor_de_seloo() {
	return temp_vapor_de_seloo;
}

public void setTemp_vapor_de_seloo(Double temp_vapor_de_seloo) {
	this.temp_vapor_de_seloo = temp_vapor_de_seloo;
}

public Double getP_vapor_de_sello() {
	return p_vapor_de_sello;
}

public void setT_descarga_condensador(Double p_vapor_de_sello) {
	this.p_vapor_de_sello = p_vapor_de_sello;
}

public String getTurno() {
	return turno;
}

public void setTurno(String turno) {
	this.turno = turno;
}




}
