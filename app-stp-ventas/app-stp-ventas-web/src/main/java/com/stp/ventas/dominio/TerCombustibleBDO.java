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
@Table(name = "tercombustiblebdo")
public class TerCombustibleBDO extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;
 
 private Long iddato;
 private Date fecha;

 private Double agua;
 private Double asfaltenos;
 private Double azufre;
 private Double carbon_conradson;
 private Double cenizas;
 private Double densidad_api;
 private Double densidad_relativa;
 private String institucion;
 private Double poder_calorico_inferior;
 private Double poder_calorico_superior;
 private Double punto_de_inflamacion;
 private Double punto_de_vertido;
 private Double temperatura_para_quemado;
 private Double viscocidad_cinematica;
 private Double viscocidad_cinematica_80;
 private Double viscocidad_redwood;
 private Double viscocidad_saybolt; 
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

public Double getAgua() {
	return agua;
}

public void setAgua(Double agua) {
	this.agua = agua;
}

public Double getAsfaltenos() {
	return asfaltenos;
}

public void setAsfaltenos(Double asfaltenos) {
	this.asfaltenos = asfaltenos;
}

public Double getAzufre() {
	return azufre;
}

public void setAzufre(Double azufre) {
	this.azufre = azufre;
}

public Double getCarbon_conradson() {
	return carbon_conradson;
}

public void setCarbon_conradson(Double carbon_conradson) {
	this.carbon_conradson = carbon_conradson;
}

public Double getCenizas() {
	return cenizas;
}

public void setCenizas(Double cenizas) {
	this.cenizas = cenizas;
}

public Double getDensidad_api() {
	return densidad_api;
}

public void setDensidad_api(Double densidad_api) {
	this.densidad_api = densidad_api;
}

public Double getDensidad_relativa() {
	return densidad_relativa;
}

public void setDensidad_relativa(Double densidad_relativa) {
	this.densidad_relativa = densidad_relativa;
}

public String getInstitucion() {
	return institucion;
}

public void setInstitucion(String institucion) {
	this.institucion = institucion;
}

public Double getPoder_calorico_inferior() {
	return poder_calorico_inferior;
}

public void setPoder_calorico_inferior(Double poder_calorico_inferior) {
	this.poder_calorico_inferior = poder_calorico_inferior;
}

public Double getPoder_calorico_superior() {
	return poder_calorico_superior;
}

public void setPoder_calorico_superior(Double poder_calorico_superior) {
	this.poder_calorico_superior = poder_calorico_superior;
}

public Double getPunto_de_inflamacion() {
	return punto_de_inflamacion;
}

public void setPunto_de_inflamacion(Double punto_de_inflamacion) {
	this.punto_de_inflamacion = punto_de_inflamacion;
}

public Double getPunto_de_vertido() {
	return punto_de_vertido;
}

public void setPunto_de_vertido(Double punto_de_vertido) {
	this.punto_de_vertido = punto_de_vertido;
}

public Double getTemperatura_para_quemado() {
	return temperatura_para_quemado;
}

public void setTemperatura_para_quemado(Double temperatura_para_quemado) {
	this.temperatura_para_quemado = temperatura_para_quemado;
}

public Double getViscocidad_cinematica() {
	return viscocidad_cinematica;
}

public void setViscocidad_cinematica(Double viscocidad_cinematica) {
	this.viscocidad_cinematica = viscocidad_cinematica;
}

public Double getViscocidad_cinematica_80() {
	return viscocidad_cinematica_80;
}

public void setViscocidad_cinematica_80(Double viscocidad_cinematica_80) {
	this.viscocidad_cinematica_80 = viscocidad_cinematica_80;
}

public Double getViscocidad_redwood() {
	return viscocidad_redwood;
}

public void setViscocidad_redwood(Double viscocidad_redwood) {
	this.viscocidad_redwood = viscocidad_redwood;
}

public Double getViscocidad_saybolt() {
	return viscocidad_saybolt;
}

public void setViscocidad_saybolt(Double viscocidad_saybolt) {
	this.viscocidad_saybolt = viscocidad_saybolt;
}


}
