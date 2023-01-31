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
@Table(name = "terparametrosunidadbc")
public class TerParametrosUnidadBC extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

private Double aire_auxiliar;
private Double caudal_de_agua_de_alimento;
private Double caudal_de_aire;
private Double caudal_de_fuel_oil;
private Double caudal_de_vapor_sh;
private Double nivel_del_domo;
private Double numero_quemadores;
private Double pdomo;
private Double porcentaje_o2;
private String posicion_selector;
private Double presion_de_vapor_rh;
private Double presion_de_vapor_sh;
private Double presion_hogar;
private Double purga_continua;
private Double temperatura_vapor_rh;
private Double temperatura_vapor_sh;
private Double venteo_desareador;
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

public Double getAire_auxiliar() {
	return aire_auxiliar;
}

public void setAire_auxiliar(Double aire_auxiliar) {
	this.aire_auxiliar = aire_auxiliar;
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

public Double getNivel_del_domo() {
	return nivel_del_domo;
}

public void setNivel_del_domo(Double nivel_del_domo) {
	this.nivel_del_domo = nivel_del_domo;
}

public Double getNumero_quemadores() {
	return numero_quemadores;
}

public void setNumero_quemadores(Double numero_quemadores) {
	this.numero_quemadores = numero_quemadores;
}

public Double getPdomo() {
	return pdomo;
}

public void setPdomo(Double pdomo) {
	this.pdomo = pdomo;
}

public Double getPorcentaje_o2() {
	return porcentaje_o2;
}

public void setPorcentaje_o2(Double porcentaje_o2) {
	this.porcentaje_o2 = porcentaje_o2;
}

public String getPosicion_selector() {
	return posicion_selector;
}

public void setPosicion_selector(String posicion_selector) {
	this.posicion_selector = posicion_selector;
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

public Double getPresion_hogar() {
	return presion_hogar;
}

public void setPresion_hogar(Double presion_hogar) {
	this.presion_hogar = presion_hogar;
}

public Double getPurga_continua() {
	return purga_continua;
}

public void setPurga_continua(Double purga_continua) {
	this.purga_continua = purga_continua;
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

public Double getVenteo_desareador() {
	return venteo_desareador;
}

public void setVenteo_desareador(Double venteo_desareador) {
	this.venteo_desareador = venteo_desareador;
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
