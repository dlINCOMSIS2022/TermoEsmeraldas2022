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
@Table(name = "terdatos_de_unidadinfor")
public class TerParametrosUnidadBTAB extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

private Double aireauxiliar;
private Double caudal_de_agua_de_alimento;
private Double caudal_de_aire;
private Double caudal_de_fuel_oil;
private Double caudal_de_vapor_sh;
private Integer cojinetevm;
private Double corriente_excitatriz;
private Double dilatacion_diferencial;
private Double frecuencia;
private Double nivel_pozo_torre;
private Double niveldomo;
private Double pmaster;
private Double porcentaje_o2;
private Double posicion_rotor;
private Double potencia_reactiva;
private Double presion_de_vapor_rh;
private Double presion_de_vapor_sh;
private Double presion_h2;
private Double presion_hogar;
private Double temperatura_vapor_rh;
private Double temperatura_vapor_sh;
private Double tmcr;
private Double tvcr;
private Double vacio_del_condensador;
private Double vibracion_maxima;
private Double voltaje_excitatriz;
private Double voltaje_generador;
private Date fecha;
private Long iddato;
private Double potencia_activa;
private String turno;



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

public Double getAireauxiliar() {
	return aireauxiliar;
}

public void setAireauxiliar(Double aireauxiliar) {
	this.aireauxiliar = aireauxiliar;
}

public Double getCaudal_de_agua_de_alimento() {
	return caudal_de_agua_de_alimento;
}

public void setCaudal_de_agua_de_alimento(Double caudal_de_agua_de_alimento) {
	this.caudal_de_agua_de_alimento = caudal_de_agua_de_alimento;
}

public Double getCaudal_de_aire() {
	return caudal_de_aire;
}

public void setCaudal_de_aire(Double caudal_de_aire) {
	this.caudal_de_aire = caudal_de_aire;
}

public Double getCaudal_de_fuel_oil() {
	return caudal_de_fuel_oil;
}

public void setCaudal_de_fuel_oil(Double caudal_de_fuel_oil) {
	this.caudal_de_fuel_oil = caudal_de_fuel_oil;
}

public Double getCaudal_de_vapor_sh() {
	return caudal_de_vapor_sh;
}

public void setCaudal_de_vapor_sh(Double caudal_de_vapor_sh) {
	this.caudal_de_vapor_sh = caudal_de_vapor_sh;
}

public Integer getCojinetevm() {
	return cojinetevm;
}

public void setCojinetevm(Integer cojinetevm) {
	this.cojinetevm = cojinetevm;
}

public Double getCorriente_excitatriz() {
	return corriente_excitatriz;
}

public void setCorriente_excitatriz(Double corriente_excitatriz) {
	this.corriente_excitatriz = corriente_excitatriz;
}

public Double getDilatacion_diferencial() {
	return dilatacion_diferencial;
}

public void setDilatacion_diferencial(Double dilatacion_diferencial) {
	this.dilatacion_diferencial = dilatacion_diferencial;
}

public Double getFrecuencia() {
	return frecuencia;
}

public void setFrecuencia(Double frecuencia) {
	this.frecuencia = frecuencia;
}

public Double getNivel_pozo_torre() {
	return nivel_pozo_torre;
}

public void setNivel_pozo_torre(Double nivel_pozo_torre) {
	this.nivel_pozo_torre = nivel_pozo_torre;
}

public Double getNiveldomo() {
	return niveldomo;
}

public void setNiveldomo(Double niveldomo) {
	this.niveldomo = niveldomo;
}

public Double getPmaster() {
	return pmaster;
}

public void setPmaster(Double pmaster) {
	this.pmaster = pmaster;
}

public Double getPorcentaje_o2() {
	return porcentaje_o2;
}

public void setPorcentaje_o2(Double porcentaje_o2) {
	this.porcentaje_o2 = porcentaje_o2;
}

public Double getPosicion_rotor() {
	return posicion_rotor;
}

public void setPosicion_rotor(Double posicion_rotor) {
	this.posicion_rotor = posicion_rotor;
}

public Double getPotencia_reactiva() {
	return potencia_reactiva;
}

public void setPotencia_reactiva(Double potencia_reactiva) {
	this.potencia_reactiva = potencia_reactiva;
}

public Double getPresion_de_vapor_rh() {
	return presion_de_vapor_rh;
}

public void setPresion_de_vapor_rh(Double presion_de_vapor_rh) {
	this.presion_de_vapor_rh = presion_de_vapor_rh;
}

public Double getPresion_de_vapor_sh() {
	return presion_de_vapor_sh;
}

public void setPresion_de_vapor_sh(Double presion_de_vapor_sh) {
	this.presion_de_vapor_sh = presion_de_vapor_sh;
}

public Double getPresion_h2() {
	return presion_h2;
}

public void setPresion_h2(Double presion_h2) {
	this.presion_h2 = presion_h2;
}

public Double getPresion_hogar() {
	return presion_hogar;
}

public void setPresion_hogar(Double presion_hogar) {
	this.presion_hogar = presion_hogar;
}

public Double getTemperatura_vapor_rh() {
	return temperatura_vapor_rh;
}

public void setTemperatura_vapor_rh(Double temperatura_vapor_rh) {
	this.temperatura_vapor_rh = temperatura_vapor_rh;
}

public Double getTemperatura_vapor_sh() {
	return temperatura_vapor_sh;
}

public void setTemperatura_vapor_sh(Double temperatura_vapor_sh) {
	this.temperatura_vapor_sh = temperatura_vapor_sh;
}

public Double getTmcr() {
	return tmcr;
}

public void setTmcr(Double tmcr) {
	this.tmcr = tmcr;
}

public Double getTvcr() {
	return tvcr;
}

public void setTvcr(Double tvcr) {
	this.tvcr = tvcr;
}

public Double getVacio_del_condensador() {
	return vacio_del_condensador;
}

public void setVacio_del_condensador(Double vacio_del_condensador) {
	this.vacio_del_condensador = vacio_del_condensador;
}

public Double getVibracion_maxima() {
	return vibracion_maxima;
}

public void setVibracion_maxima(Double vibracion_maxima) {
	this.vibracion_maxima = vibracion_maxima;
}

public Double getVoltaje_excitatriz() {
	return voltaje_excitatriz;
}

public void setVoltaje_excitatriz(Double voltaje_excitatriz) {
	this.voltaje_excitatriz = voltaje_excitatriz;
}

public Double getVoltaje_generador() {
	return voltaje_generador;
}

public void setVoltaje_generador(Double voltaje_generador) {
	this.voltaje_generador = voltaje_generador;
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

public Double getPotencia_activa() {
	return potencia_activa;
}

public void setPotencia_activa(Double potencia_activa) {
	this.potencia_activa = potencia_activa;
}

public String getTurno() {
	return turno;
}

public void setTurno(String turno) {
	this.turno = turno;
}



}
