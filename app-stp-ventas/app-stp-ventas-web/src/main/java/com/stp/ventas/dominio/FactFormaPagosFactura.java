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
@Table(name = "ONIX_FORMAS_PAGO_FACTURA")
public class FactFormaPagosFactura extends EntidadBaseAuditable<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private FactFormaPagos lFormaPago;
	private FactCabecera lFactura;
	private Double lTotal;
	private Long plazo;
	private String unidadTiempo;

	@GeneratedValue(generator = "FactFormaPagoFact", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactFormaPagoFact", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_FORMA_PAGO_FACT")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);
	}

	@ManyToOne
	@JoinColumn(name="ID_FORMA_PAGO")
	public FactFormaPagos getlFormaPago() {
		return lFormaPago;
	}

	public void setlFormaPago(FactFormaPagos lFormaPago) {
		this.lFormaPago = lFormaPago;
	}

	@ManyToOne
	@JoinColumn(name="ID_FACTURA")
	public FactCabecera getlFactura() {
		return lFactura;
	}

	public void setlFactura(FactCabecera lFactura) {
		this.lFactura = lFactura;
	}

	public Double getlTotal() {
		return lTotal;
	}

	public void setlTotal(Double lTotal) {
		this.lTotal = lTotal;
	}

	public Long getPlazo() {
		return plazo;
	}

	public void setPlazo(Long plazo) {
		this.plazo = plazo;
	}

	public String getUnidadTiempo() {
		return unidadTiempo;
	}

	public void setUnidadTiempo(String unidadTiempo) {
		this.unidadTiempo = unidadTiempo;
	}
	
	

}
