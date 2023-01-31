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

import com.stp.plataforma.dominio.aplicacion.OmsUsuario;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "ONIX_USUARIO_EMPRESA")
public class FactUsuarioEmpresa extends EntidadBaseAuditable<Long> implements Serializable 
{

	private FactEmpresa lEmpresa;
	private OmsUsuario lUsuario;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(generator = "FactUsuarioEmpresa", strategy = GenerationType.SEQUENCE)
   	@SequenceGenerator(name = "FactUsuarioEmpresa", allocationSize = 1, initialValue = 1, 
   	sequenceName = "SEC_FACT_USUARIO_EMPRE")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@ManyToOne
	@JoinColumn(name="ID_EMPRESA")
	public FactEmpresa getlEmpresa() {
		return lEmpresa;
	}

	public void setlEmpresa(FactEmpresa lEmpresa) {
		this.lEmpresa = lEmpresa;
	}


	@ManyToOne
	@JoinColumn(name="ID_USUARIO")
	public OmsUsuario getlUsuario() {
		return lUsuario;
	}

	public void setlUsuario(OmsUsuario lUsuario) {
		this.lUsuario = lUsuario;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable)idReferencia);
		
	}
	
	
	
}
