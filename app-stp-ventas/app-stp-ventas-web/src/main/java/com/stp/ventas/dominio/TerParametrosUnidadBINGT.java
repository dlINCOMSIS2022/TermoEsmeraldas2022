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
@Table(name = "terparametros_de_unidadbingt")
public class TerParametrosUnidadBINGT extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

 private Double caudal_de_agua_de_alimento;
 private Double caudal_de_aire;
 private Double caudal_de_fuel_oil;
 private Double caudal_de_vapor_sh;
 private Double consumo_especifico;
 private Double frecuencia;
 private Double inclinacion_quemadores;
 private Double lectura_energia;
 private Double lectura_fuel_oil;
 private Double porcentaje_o2;
 private Double potencia_reactiva;
 private Double presion_bomba_alimento;
 private Double presion_vapor_rh;
 private Double presion_vapor_sh;
 private Double temp_ambiente;
 private Integer temp_precalentador_aire_vapor;
 private Double temperatura_vapor_rh;
 private Double temperatura_vapor_sh;
 private Double vacio_del_condensador;
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

public Double getConsumo_especifico() {
	return consumo_especifico;
}

public void setConsumo_especifico(Double consumo_especifico) {
	this.consumo_especifico = consumo_especifico;
}

public Double getFrecuencia() {
	return frecuencia;
}

public void setFrecuencia(Double frecuencia) {
	this.frecuencia = frecuencia;
}

public Double getInclinacion_quemadores() {
	return inclinacion_quemadores;
}

public void setInclinacion_quemadores(Double inclinacion_quemadores) {
	this.inclinacion_quemadores = inclinacion_quemadores;
}

public Double getLectura_energia() {
	return lectura_energia;
}

public void setLectura_energia(Double lectura_energia) {
	this.lectura_energia = lectura_energia;
}

public Double getLectura_fuel_oil() {
	return lectura_fuel_oil;
}

public void setLectura_fuel_oil(Double lectura_fuel_oil) {
	this.lectura_fuel_oil = lectura_fuel_oil;
}

public Double getPorcentaje_o2() {
	return porcentaje_o2;
}

public void setPorcentaje_o2(Double porcentaje_o2) {
	this.porcentaje_o2 = porcentaje_o2;
}

public Double getPotencia_reactiva() {
	return potencia_reactiva;
}

public void setPotencia_reactiva(Double potencia_reactiva) {
	this.potencia_reactiva = potencia_reactiva;
}

public Double getPresion_bomba_alimento() {
	return presion_bomba_alimento;
}

public void setPresion_bomba_alimento(Double presion_bomba_alimento) {
	this.presion_bomba_alimento = presion_bomba_alimento;
}

public Double getPresion_vapor_rh() {
	return presion_vapor_rh;
}

public void setPresion_vapor_rh(Double presion_vapor_rh) {
	this.presion_vapor_rh = presion_vapor_rh;
}

public Double getPresion_vapor_sh() {
	return presion_vapor_sh;
}

public void setPresion_vapor_sh(Double presion_vapor_sh) {
	this.presion_vapor_sh = presion_vapor_sh;
}

public Double getTemp_ambiente() {
	return temp_ambiente;
}

public void setTemp_ambiente(Double temp_ambiente) {
	this.temp_ambiente = temp_ambiente;
}

public Integer getTemp_precalentador_aire_vapor() {
	return temp_precalentador_aire_vapor;
}

public void setTemp_precalentador_aire_vapor(Integer temp_precalentador_aire_vapor) {
	this.temp_precalentador_aire_vapor = temp_precalentador_aire_vapor;
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

public Double getVacio_del_condensador() {
	return vacio_del_condensador;
}

public void setVacio_del_condensador(Double vacio_del_condensador) {
	this.vacio_del_condensador = vacio_del_condensador;
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
