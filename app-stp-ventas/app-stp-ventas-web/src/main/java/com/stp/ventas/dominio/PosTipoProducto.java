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
@Table(name = "POS_TIPO_PRODUCTO")
public class PosTipoProducto extends EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String lTipoProducto;
	private FactEmpresa lEmpresa;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactTipoProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactTipoProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_POS_TIPO_PRODUCTO")
	public Long getId() {
		return id;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);
	}

	public String getlTipoProducto() {
		return lTipoProducto;
	}

	public void setlTipoProducto(String lTipoProducto) {
		this.lTipoProducto = lTipoProducto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	public FactEmpresa getlEmpresa() {
		return lEmpresa;
	}

	public void setlEmpresa(FactEmpresa lEmpresa) {
		this.lEmpresa = lEmpresa;
	}

}
