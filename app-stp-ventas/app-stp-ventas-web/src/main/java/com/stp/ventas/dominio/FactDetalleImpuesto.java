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
@Table(name = "ONIX_DETALLE_IMPUESTOS")
public class FactDetalleImpuesto extends EntidadBaseAuditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoTipoImpuesto;
	private String codigoPorcentaje;
	private Double tarifa;
	private Double baseImponible;
	private Double valor;
	
	private FactDetalle lDetalleFactura;
	
	

	@GeneratedValue(generator = "FactDetImpuesto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactDetImpuesto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_DET_FACT_IMPUESTO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);		
	}

	public String getCodigoTipoImpuesto() {
		return codigoTipoImpuesto;
	}

	public void setCodigoTipoImpuesto(String codigoTipoImpuesto) {
		this.codigoTipoImpuesto = codigoTipoImpuesto;
	}

	public String getCodigoPorcentaje() {
		return codigoPorcentaje;
	}

	public void setCodigoPorcentaje(String codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}

	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}

	public Double getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(Double baseImponible) {
		this.baseImponible = baseImponible;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@ManyToOne
	@JoinColumn(name="ID_DETALLE")
	public FactDetalle getlDetalleFactura() {
		return lDetalleFactura;
	}

	public void setlDetalleFactura(FactDetalle lDetalleFactura) {
		this.lDetalleFactura = lDetalleFactura;
	}
	
	

}
