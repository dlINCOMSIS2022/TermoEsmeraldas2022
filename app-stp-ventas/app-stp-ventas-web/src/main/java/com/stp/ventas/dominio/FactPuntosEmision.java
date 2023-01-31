package com.stp.ventas.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name="ONIX_PUNTO_EMISION")
public class FactPuntosEmision extends  EntidadBaseAuditable<Long> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String codigo;
	private Long secuenciaFactura;
	private Long secuenciaNotaCredito;
	private Long secuenciaNotaDebito;
	private Long secuenciaGuiaRemision;
	private Long secuenciaRetencion;
	private Long secuenciaLiquidacion;
	
	private FactEstablecimiento lEstblecimiento;
	
	
	@GeneratedValue(generator = "FactPtoEmision", strategy = GenerationType.SEQUENCE)
   	@SequenceGenerator(name = "FactPtoEmision", allocationSize = 1, initialValue = 1, 
   	sequenceName = "SEC_FACT_PTO_EMISION")
   	@Id
   	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable)idReferencia);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getSecuenciaFactura() {
		return secuenciaFactura;
	}

	public void setSecuenciaFactura(Long secuenciaFactura) {
		this.secuenciaFactura = secuenciaFactura;
	}

	public Long getSecuenciaNotaCredito() {
		return secuenciaNotaCredito;
	}

	public void setSecuenciaNotaCredito(Long secuenciaNotaCredito) {
		this.secuenciaNotaCredito = secuenciaNotaCredito;
	}

	public Long getSecuenciaNotaDebito() {
		return secuenciaNotaDebito;
	}

	public void setSecuenciaNotaDebito(Long secuenciaNotaDebito) {
		this.secuenciaNotaDebito = secuenciaNotaDebito;
	}

	public Long getSecuenciaGuiaRemision() {
		return secuenciaGuiaRemision;
	}

	public void setSecuenciaGuiaRemision(Long secuenciaGuiaRemision) {
		this.secuenciaGuiaRemision = secuenciaGuiaRemision;
	}

	public Long getSecuenciaRetencion() {
		return secuenciaRetencion;
	}

	public void setSecuenciaRetencion(Long secuenciaRetencion) {
		this.secuenciaRetencion = secuenciaRetencion;
	}

	public Long getSecuenciaLiquidacion() {
		return secuenciaLiquidacion;
	}

	public void setSecuenciaLiquidacion(Long secuenciaLiquidacion) {
		this.secuenciaLiquidacion = secuenciaLiquidacion;
	}

	@ManyToOne
	@JoinColumn(name="ID_ESTABLECIMIENTO")
	public FactEstablecimiento getlEstblecimiento() {
		return lEstblecimiento;
	}

	public void setlEstblecimiento(FactEstablecimiento lEstblecimiento) {
		this.lEstblecimiento = lEstblecimiento;
	}
	
	

}
