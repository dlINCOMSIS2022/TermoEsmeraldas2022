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
@Table(name = "ONIX_CAB_FACTURA_IMPUESTO")
public class FactCabeceraImpuesto extends EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String codigoPorcentaje;
	private Double baseImponible;
	private Double tarifa;
	private Double valor;

	private FactCabecera lFactura;

	@GeneratedValue(generator = "FactCabeceraImp", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactCabeceraImp", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_CABECERA_IMP")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoPorcentaje() {
		return codigoPorcentaje;
	}

	public void setCodigoPorcentaje(String codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}

	public Double getBaseImponible() {
		return baseImponible;
	}

	public void setBaseImponible(Double baseImponible) {
		this.baseImponible = baseImponible;
	}

	public Double getTarifa() {
		return tarifa;
	}

	public void setTarifa(Double tarifa) {
		this.tarifa = tarifa;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	
	@ManyToOne
	@JoinColumn(name = "ID_FACTURA")
	public FactCabecera getlFactura() {
		return lFactura;
	}

	public void setlFactura(FactCabecera lFactura) {
		this.lFactura = lFactura;
	}
	
	

}
