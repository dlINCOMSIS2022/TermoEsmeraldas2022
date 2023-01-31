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
@Table(name="ONIX_ESTABLECIMIENTO")
public class FactEstablecimiento extends  EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String direccion;
	private String codigo;
	private FactEmpresa lEmpresa;
	
	@GeneratedValue(generator = "FactEstablecimiento", strategy = GenerationType.SEQUENCE)
   	@SequenceGenerator(name = "FactEstablecimiento", allocationSize = 1, initialValue = 1, 
   	sequenceName = "SEC_FACT_ESTABLECIMIENTO")
   	@Id
   	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	
	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable)idReferencia);
	}


	@ManyToOne
	@JoinColumn(name="ID_EMPRESA")
	public FactEmpresa getlEmpresa() {
		return lEmpresa;
	}


	public void setlEmpresa(FactEmpresa lEmpresa) {
		this.lEmpresa = lEmpresa;
	}
	
	

}
