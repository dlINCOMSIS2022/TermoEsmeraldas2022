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
@Table(name = "ONIX_CLIENTES")
public class FactCliente extends EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tipoIdentificacionComprador;
	private String razonSocialComprador;
	private String identificacionComprador;
	private String direccionComprador;
	private String emailsComprador;
	private String celularComprador;
	
	private FactEmpresa lEmpresa;
	
	@GeneratedValue(generator = "FactCliente", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactCliente", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_CLIENTE")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);
	}
	
	public String getTipoIdentificacionComprador() {
		return tipoIdentificacionComprador;
	}
	public void setTipoIdentificacionComprador(String tipoIdentificacionComprador) {
		this.tipoIdentificacionComprador = tipoIdentificacionComprador;
	}
	public String getRazonSocialComprador() {
		return razonSocialComprador;
	}
	public void setRazonSocialComprador(String razonSocialComprador) {
		this.razonSocialComprador = razonSocialComprador;
	}
	public String getIdentificacionComprador() {
		return identificacionComprador;
	}
	public void setIdentificacionComprador(String identificacionComprador) {
		this.identificacionComprador = identificacionComprador;
	}
	public String getDireccionComprador() {
		return direccionComprador;
	}
	public void setDireccionComprador(String direccionComprador) {
		this.direccionComprador = direccionComprador;
	}
	public String getEmailsComprador() {
		return emailsComprador;
	}
	public void setEmailsComprador(String emailsComprador) {
		this.emailsComprador = emailsComprador;
	}
	public String getCelularComprador() {
		return celularComprador;
	}
	public void setCelularComprador(String celularComprador) {
		this.celularComprador = celularComprador;
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
