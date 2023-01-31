package com.stp.ventas.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "ONIX_IMPUESTOS")
public class FactImpuestos extends EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lDescripcion;
	private Double lPorcentaje;
	private String lCodigoImpuesto;
	private String lTipoImpuesto;

	@GeneratedValue(generator = "FactImpuesto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactImpuesto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_IMPUESTO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public String getlDescripcion() {
		return lDescripcion;
	}

	public void setlDescripcion(String lDescripcion) {
		this.lDescripcion = lDescripcion;
	}

	public Double getlPorcentaje() {
		return lPorcentaje;
	}

	public void setlPorcentaje(Double lPorcentaje) {
		this.lPorcentaje = lPorcentaje;
	}

	public String getlCodigoImpuesto() {
		return lCodigoImpuesto;
	}

	public void setlCodigoImpuesto(String lCodigoImpuesto) {
		this.lCodigoImpuesto = lCodigoImpuesto;
	}

	public String getlTipoImpuesto() {
		return lTipoImpuesto;
	}

	public void setlTipoImpuesto(String lTipoImpuesto) {
		this.lTipoImpuesto = lTipoImpuesto;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);

	}

}
