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
@Table(name = "POS_IMPUESTO_PRODUCTO")
public class FactImpuestosProductos extends EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private PosProducto lProducto;
	private FactImpuestos lImpuesto;
	private String lTipoImpuesto;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactImpProd", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactImpProd", allocationSize = 1, initialValue = 1, sequenceName = "SEC_IMPS_PRODUCTO")
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);
	}

	
	@ManyToOne
	@JoinColumn(name="ID_PRODUCTO")
	public PosProducto getlProducto() {
		return lProducto;
	}

	public void setlProducto(PosProducto lProducto) {
		this.lProducto = lProducto;
	}

	@ManyToOne
	@JoinColumn(name="ID_IMPUESTO")
	public FactImpuestos getlImpuesto() {
		return lImpuesto;
	}

	public void setlImpuesto(FactImpuestos lImpuesto) {
		this.lImpuesto = lImpuesto;
	}

	public String getlTipoImpuesto() {
		return lTipoImpuesto;
	}

	public void setlTipoImpuesto(String lTipoImpuesto) {
		this.lTipoImpuesto = lTipoImpuesto;
	}
	
	

}
