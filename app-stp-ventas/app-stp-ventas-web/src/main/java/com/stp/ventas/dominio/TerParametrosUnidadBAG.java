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
@Table(name = "terparametrosunidadbag")
public class TerParametrosUnidadBAG extends EntidadBaseAuditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Date fecha;
	private Long iddato;
	private Double potencia_activa;
	private Double voltaje_generador;
	private Double presion_aire_servicios;
	private Double presion_aire_instrumentos;
	private Double presion_agua_enfr_cc;
	private Double temp_agua_enfr_cc_entrada;
	private Double temp_agua_enfr_cc_salida;
	private Double temp_agua_c_entrada;
	private Double temp_agua_c_salida;
	private Double max_temp_mt1;
	private Double max_temp_ut1;
	private Double max_temp_sto;
	private Double gde_voltaje_carga_bateria;
	private Double gde_nivel_diesel;
	private Double temp_fo_js_s2;
	private Double temp_fo_js_s3;
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

	public Double getPotencia_activa() {
		return potencia_activa;
	}

	public void setPotencia_activa(Double potencia_activa) {
		this.potencia_activa = potencia_activa;
	}

	public Double getVoltaje_generador() {
		return voltaje_generador;
	}

	public void setVoltaje_generador(Double voltaje_generador) {
		this.voltaje_generador = voltaje_generador;
	}

	public Double getPresion_aire_servicios() {
		return presion_aire_servicios;
	}

	public void setPresion_aire_servicios(Double presion_aire_servicios) {
		this.presion_aire_servicios = presion_aire_servicios;
	}

	public Double getPresion_aire_instrumentos() {
		return presion_aire_instrumentos;
	}

	public void setPresion_aire_instrumentos(Double presion_aire_instrumentos) {
		this.presion_aire_instrumentos = presion_aire_instrumentos;
	}

	public Double getPresion_agua_enfr_cc() {
		return presion_agua_enfr_cc;
	}

	public void setPresion_agua_enfr_cc(Double presion_agua_enfr_cc) {
		this.presion_agua_enfr_cc = presion_agua_enfr_cc;
	}

	public Double getTemp_agua_enfr_cc_entrada() {
		return temp_agua_enfr_cc_entrada;
	}

	public void setTemp_agua_enfr_cc_entrada(Double temp_agua_enfr_cc_entrada) {
		this.temp_agua_enfr_cc_entrada = temp_agua_enfr_cc_entrada;
	}

	public Double getTemp_agua_enfr_cc_salida() {
		return temp_agua_enfr_cc_salida;
	}

	public void setTemp_agua_enfr_cc_salida(Double temp_agua_enfr_cc_salida) {
		this.temp_agua_enfr_cc_salida = temp_agua_enfr_cc_salida;
	}

	public Double getTemp_agua_c_entrada() {
		return temp_agua_c_entrada;
	}

	public void setTemp_agua_c_entrada(Double temp_agua_c_entrada) {
		this.temp_agua_c_entrada = temp_agua_c_entrada;
	}

	public Double getTemp_agua_c_salida() {
		return temp_agua_c_salida;
	}

	public void setTemp_agua_c_salida(Double temp_agua_c_salida) {
		this.temp_agua_c_salida = temp_agua_c_salida;
	}

	public Double getMax_temp_mt1() {
		return max_temp_mt1;
	}

	public void setMax_temp_mt1(Double max_temp_mt1) {
		this.max_temp_mt1 = max_temp_mt1;
	}

	public Double getMax_temp_ut1() {
		return max_temp_ut1;
	}

	public void setMax_temp_ut1(Double max_temp_ut1) {
		this.max_temp_ut1 = max_temp_ut1;
	}

	public Double getMax_temp_sto() {
		return max_temp_sto;
	}

	public void setMax_temp_sto(Double max_temp_sto) {
		this.max_temp_sto = max_temp_sto;
	}

	public Double getGde_voltaje_carga_bateria() {
		return gde_voltaje_carga_bateria;
	}

	public void setGde_voltaje_carga_bateria(Double gde_voltaje_carga_bateria) {
		this.gde_voltaje_carga_bateria = gde_voltaje_carga_bateria;
	}

	public Double getGde_nivel_diesel() {
		return gde_nivel_diesel;
	}

	public void setGde_nivel_diesel(Double gde_nivel_diesel) {
		this.gde_nivel_diesel = gde_nivel_diesel;
	}

	public Double getTemp_fo_js_s2() {
		return temp_fo_js_s2;
	}

	public void setTemp_fo_js_s2(Double temp_fo_js_s2) {
		this.temp_fo_js_s2 = temp_fo_js_s2;
	}

	public Double getTemp_fo_js_s3() {
		return temp_fo_js_s3;
	}

	public void setTemp_fo_js_s3(Double temp_fo_js_s3) {
		this.temp_fo_js_s3 = temp_fo_js_s3;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}
	

}
