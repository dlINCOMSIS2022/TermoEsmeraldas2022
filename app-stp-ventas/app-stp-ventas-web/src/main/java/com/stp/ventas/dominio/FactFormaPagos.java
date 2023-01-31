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
@Table(name = "ONIX_FORMAS_PAGO")
public class FactFormaPagos extends EntidadBaseAuditable<Long> implements Serializable {

	/** 
	 *  
	 */
	private static final long serialVersionUID = 1L;

	private String codigo;
	private String descripcion;

	@GeneratedValue(generator = "FactFormaPago", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactFormaPago", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_FORMA_PAGO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);
	}

}
