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
public class TerDatosdeUnidadINF extends EntidadBaseAuditable<Long> implements Serializable {

private static final long serialVersionUID = 1L;

private Date fecha;
private Long iddato;

private Double agua_de_alimento;
private Double agua_demi_reposicion_ciclo;
private Double agua_desmineralizada;
private Double agua_esm2;
private Double agua_ingresda_de_inabronco;
private Double agua_potable_producida;
private Double biocida;
private Integer co2;
private Integer consumo_a_compresor;
private Integer consumo_aceite_turbina;
private Double consumo_de_aceite_auxiliares;
private Double consumo_de_diesel;
private Double consumo_de_fuel_oil;
private Double dispersante;
private Double disponibilidad_diaria;
private Double energia_entregada_a_emelesa;
private Double energia_reactiva;
private Double energia_reactiva_max;
private Double energia_reactiva_min;
private Double energia_refineria;
private Double existencia_final_calculada;
private Double existencia_final_fiscalizada;
private Double existencia_inicial;
private Double generacion_bruta;
private Double generada_emelesa;
private Double h2_generador;
private Integer hidrogeno;
private Double horas_generadas;
private Double lectura_agua_de_inabronco_ingresada;
private Double lectura_agua_potable_producida;
private Double lectura_de_fuel_oil;
private Double lectura_energia_entregada_a_emelesa;
private Integer nitrogeno;
private Double nivel_fiscalizado_jss1;
private Double njs_s1;
private Double njs_s2;
private Double njs_s3;
private Double njs_s4;
private Double njs_s6;
private Double nmk_s2;
private Double nmy_s2;
private String novedades_principales;
private String obsconsumodiesel;
private Double policloro;
private Double potencia_efectiva;
private Double potencia_maxima_generada;
private Double potencia_minima_generada;
private Double reserva_de_diesel;
private Double reserva_de_fuel_oil;
private Double reserva_fuel_jlss;
private Double reserva_fuel_oil_util_jlss;
private Double saldopetrocomercial;
private Double sto_ues;
private Double sto_ufs;
private Double ut1_ues;
private Double ut1_ufs;
private Double vapor_caldero;
private Double vol_diesel_ingresado;
private Double vol_fiscalizado_jss1;
private Double vol_fuel_ingresado;






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

public Double getAgua_de_alimento() {
	return agua_de_alimento;
}

public void setAgua_de_alimento(Double agua_de_alimento) {
	this.agua_de_alimento = agua_de_alimento;
}

public Double getAgua_demi_reposicion_ciclo() {
	return agua_demi_reposicion_ciclo;
}

public void setAgua_demi_reposicion_ciclo(Double agua_demi_reposicion_ciclo) {
	this.agua_demi_reposicion_ciclo = agua_demi_reposicion_ciclo;
}

public Double getAgua_desmineralizada() {
	return agua_desmineralizada;
}

public void setAgua_desmineralizada(Double agua_desmineralizada) {
	this.agua_desmineralizada = agua_desmineralizada;
}

public Double getAgua_esm2() {
	return agua_esm2;
}

public void setAgua_esm2(Double agua_esm2) {
	this.agua_esm2 = agua_esm2;
}

public Double getAgua_ingresda_de_inabronco() {
	return agua_ingresda_de_inabronco;
}

public void setAgua_ingresda_de_inabronco(Double agua_ingresda_de_inabronco) {
	this.agua_ingresda_de_inabronco = agua_ingresda_de_inabronco;
}

public Double getAgua_potable_producida() {
	return agua_potable_producida;
}

public void setAgua_potable_producida(Double agua_potable_producida) {
	this.agua_potable_producida = agua_potable_producida;
}

public Double getBiocida() {
	return biocida;
}

public void setBiocida(Double biocida) {
	this.biocida = biocida;
}

public Integer getCo2() {
	return co2;
}

public void setCo2(Integer co2) {
	this.co2 = co2;
}

public Integer getConsumo_a_compresor() {
	return consumo_a_compresor;
}

public void setConsumo_a_compresor(Integer consumo_a_compresor) {
	this.consumo_a_compresor = consumo_a_compresor;
}

public Integer getConsumo_aceite_turbina() {
	return consumo_aceite_turbina;
}

public void setConsumo_aceite_turbina(Integer consumo_aceite_turbina) {
	this.consumo_aceite_turbina = consumo_aceite_turbina;
}

public Double getConsumo_de_aceite_auxiliares() {
	return consumo_de_aceite_auxiliares;
}

public void setConsumo_de_aceite_auxiliares(Double consumo_de_aceite_auxiliares) {
	this.consumo_de_aceite_auxiliares = consumo_de_aceite_auxiliares;
}

public Double getConsumo_de_diesel() {
	return consumo_de_diesel;
}

public void setConsumo_de_diesel(Double consumo_de_diesel) {
	this.consumo_de_diesel = consumo_de_diesel;
}

public Double getConsumo_de_fuel_oil() {
	return consumo_de_fuel_oil;
}

public void setConsumo_de_fuel_oil(Double consumo_de_fuel_oil) {
	this.consumo_de_fuel_oil = consumo_de_fuel_oil;
}

public Double getDispersante() {
	return dispersante;
}

public void setDispersante(Double dispersante) {
	this.dispersante = dispersante;
}

public Double getDisponibilidad_diaria() {
	return disponibilidad_diaria;
}

public void setDisponibilidad_diaria(Double disponibilidad_diaria) {
	this.disponibilidad_diaria = disponibilidad_diaria;
}

public Double getEnergia_entregada_a_emelesa() {
	return energia_entregada_a_emelesa;
}

public void setEnergia_entregada_a_emelesa(Double energia_entregada_a_emelesa) {
	this.energia_entregada_a_emelesa = energia_entregada_a_emelesa;
}

public Double getEnergia_reactiva() {
	return energia_reactiva;
}

public void setEnergia_reactiva(Double energia_reactiva) {
	this.energia_reactiva = energia_reactiva;
}

public Double getEnergia_reactiva_max() {
	return energia_reactiva_max;
}

public void setEnergia_reactiva_max(Double energia_reactiva_max) {
	this.energia_reactiva_max = energia_reactiva_max;
}

public Double getEnergia_reactiva_min() {
	return energia_reactiva_min;
}

public void setEnergia_reactiva_min(Double energia_reactiva_min) {
	this.energia_reactiva_min = energia_reactiva_min;
}

public Double getEnergia_refineria() {
	return energia_refineria;
}

public void setEnergia_refineria(Double energia_refineria) {
	this.energia_refineria = energia_refineria;
}

public Double getExistencia_final_calculada() {
	return existencia_final_calculada;
}

public void setExistencia_final_calculada(Double existencia_final_calculada) {
	this.existencia_final_calculada = existencia_final_calculada;
}

public Double getExistencia_final_fiscalizada() {
	return existencia_final_fiscalizada;
}

public void setExistencia_final_fiscalizada(Double existencia_final_fiscalizada) {
	this.existencia_final_fiscalizada = existencia_final_fiscalizada;
}

public Double getExistencia_inicial() {
	return existencia_inicial;
}

public void setExistencia_inicial(Double existencia_inicial) {
	this.existencia_inicial = existencia_inicial;
}

public Double getGeneracion_bruta() {
	return generacion_bruta;
}

public void setGeneracion_bruta(Double generacion_bruta) {
	this.generacion_bruta = generacion_bruta;
}

public Double getGenerada_emelesa() {
	return generada_emelesa;
}

public void setGenerada_emelesa(Double generada_emelesa) {
	this.generada_emelesa = generada_emelesa;
}

public Double getH2_generador() {
	return h2_generador;
}

public void setH2_generador(Double h2_generador) {
	this.h2_generador = h2_generador;
}

public Integer getHidrogeno() {
	return hidrogeno;
}

public void setHidrogeno(Integer hidrogeno) {
	this.hidrogeno = hidrogeno;
}

public Double getHoras_generadas() {
	return horas_generadas;
}

public void setHoras_generadas(Double horas_generadas) {
	this.horas_generadas = horas_generadas;
}

public Double getLectura_agua_de_inabronco_ingresada() {
	return lectura_agua_de_inabronco_ingresada;
}

public void setLectura_agua_de_inabronco_ingresada(Double lectura_agua_de_inabronco_ingresada) {
	this.lectura_agua_de_inabronco_ingresada = lectura_agua_de_inabronco_ingresada;
}

public Double getLectura_agua_potable_producida() {
	return lectura_agua_potable_producida;
}

public void setLectura_agua_potable_producida(Double lectura_agua_potable_producida) {
	this.lectura_agua_potable_producida = lectura_agua_potable_producida;
}

public Double getLectura_de_fuel_oil() {
	return lectura_de_fuel_oil;
}

public void setLectura_de_fuel_oil(Double lectura_de_fuel_oil) {
	this.lectura_de_fuel_oil = lectura_de_fuel_oil;
}

public Double getLectura_energia_entregada_a_emelesa() {
	return lectura_energia_entregada_a_emelesa;
}

public void setLectura_energia_entregada_a_emelesa(Double lectura_energia_entregada_a_emelesa) {
	this.lectura_energia_entregada_a_emelesa = lectura_energia_entregada_a_emelesa;
}

public Integer getNitrogeno() {
	return nitrogeno;
}

public void setNitrogeno(Integer nitrogeno) {
	this.nitrogeno = nitrogeno;
}

public Double getNivel_fiscalizado_jss1() {
	return nivel_fiscalizado_jss1;
}

public void setNivel_fiscalizado_jss1(Double nivel_fiscalizado_jss1) {
	this.nivel_fiscalizado_jss1 = nivel_fiscalizado_jss1;
}

public Double getNjs_s1() {
	return njs_s1;
}

public void setNjs_s1(Double njs_s1) {
	this.njs_s1 = njs_s1;
}

public Double getNjs_s2() {
	return njs_s2;
}

public void setNjs_s2(Double njs_s2) {
	this.njs_s2 = njs_s2;
}

public Double getNjs_s3() {
	return njs_s3;
}

public void setNjs_s3(Double njs_s3) {
	this.njs_s3 = njs_s3;
}

public Double getNjs_s4() {
	return njs_s4;
}

public void setNjs_s4(Double njs_s4) {
	this.njs_s4 = njs_s4;
}

public Double getNjs_s6() {
	return njs_s6;
}

public void setNjs_s6(Double njs_s6) {
	this.njs_s6 = njs_s6;
}

public Double getNmk_s2() {
	return nmk_s2;
}

public void setNmk_s2(Double nmk_s2) {
	this.nmk_s2 = nmk_s2;
}

public Double getNmy_s2() {
	return nmy_s2;
}

public void setNmy_s2(Double nmy_s2) {
	this.nmy_s2 = nmy_s2;
}

public String getNovedades_principales() {
	return novedades_principales;
}

public void setNovedades_principales(String novedades_principales) {
	this.novedades_principales = novedades_principales;
}

public String getObsconsumodiesel() {
	return obsconsumodiesel;
}

public void setObsconsumodiesel(String obsconsumodiesel) {
	this.obsconsumodiesel = obsconsumodiesel;
}

public Double getPolicloro() {
	return policloro;
}

public void setPolicloro(Double policloro) {
	this.policloro = policloro;
}

public Double getPotencia_efectiva() {
	return potencia_efectiva;
}

public void setPotencia_efectiva(Double potencia_efectiva) {
	this.potencia_efectiva = potencia_efectiva;
}

public Double getPotencia_maxima_generada() {
	return potencia_maxima_generada;
}

public void setPotencia_maxima_generada(Double potencia_maxima_generada) {
	this.potencia_maxima_generada = potencia_maxima_generada;
}

public Double getPotencia_minima_generada() {
	return potencia_minima_generada;
}

public void setPotencia_minima_generada(Double potencia_minima_generada) {
	this.potencia_minima_generada = potencia_minima_generada;
}

public Double getReserva_de_diesel() {
	return reserva_de_diesel;
}

public void setReserva_de_diesel(Double reserva_de_diesel) {
	this.reserva_de_diesel = reserva_de_diesel;
}

public Double getReserva_de_fuel_oil() {
	return reserva_de_fuel_oil;
}

public void setReserva_de_fuel_oil(Double reserva_de_fuel_oil) {
	this.reserva_de_fuel_oil = reserva_de_fuel_oil;
}

public Double getReserva_fuel_jlss() {
	return reserva_fuel_jlss;
}

public void setReserva_fuel_jlss(Double reserva_fuel_jlss) {
	this.reserva_fuel_jlss = reserva_fuel_jlss;
}

public Double getReserva_fuel_oil_util_jlss() {
	return reserva_fuel_oil_util_jlss;
}

public void setReserva_fuel_oil_util_jlss(Double reserva_fuel_oil_util_jlss) {
	this.reserva_fuel_oil_util_jlss = reserva_fuel_oil_util_jlss;
}

public Double getSaldopetrocomercial() {
	return saldopetrocomercial;
}

public void setSaldopetrocomercial(Double saldopetrocomercial) {
	this.saldopetrocomercial = saldopetrocomercial;
}

public Double getSto_ues() {
	return sto_ues;
}

public void setSto_ues(Double sto_ues) {
	this.sto_ues = sto_ues;
}

public Double getSto_ufs() {
	return sto_ufs;
}

public void setSto_ufs(Double sto_ufs) {
	this.sto_ufs = sto_ufs;
}

public Double getUt1_ues() {
	return ut1_ues;
}

public void setUt1_ues(Double ut1_ues) {
	this.ut1_ues = ut1_ues;
}

public Double getUt1_ufs() {
	return ut1_ufs;
}

public void setUt1_ufs(Double ut1_ufs) {
	this.ut1_ufs = ut1_ufs;
}

public Double getVapor_caldero() {
	return vapor_caldero;
}

public void setVapor_caldero(Double vapor_caldero) {
	this.vapor_caldero = vapor_caldero;
}

public Double getVol_diesel_ingresado() {
	return vol_diesel_ingresado;
}

public void setVol_diesel_ingresado(Double vol_diesel_ingresado) {
	this.vol_diesel_ingresado = vol_diesel_ingresado;
}

public Double getVol_fiscalizado_jss1() {
	return vol_fiscalizado_jss1;
}

public void setVol_fiscalizado_jss1(Double vol_fiscalizado_jss1) {
	this.vol_fiscalizado_jss1 = vol_fiscalizado_jss1;
}

public Double getVol_fuel_ingresado() {
	return vol_fuel_ingresado;
}

public void setVol_fuel_ingresado(Double vol_fuel_ingresado) {
	this.vol_fuel_ingresado = vol_fuel_ingresado;
}


}
