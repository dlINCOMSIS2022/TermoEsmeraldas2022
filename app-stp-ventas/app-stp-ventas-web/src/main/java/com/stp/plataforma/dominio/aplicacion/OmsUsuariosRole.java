package com.stp.plataforma.dominio.aplicacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PRI_USUARIOS_ROLES database table.
 * 
 */
@Entity
@Table(name = "ONIX_USUARIOS_ROLES")
@NamedQuery(name = "OmsUsuariosRole.findAll", query = "SELECT p FROM OmsUsuariosRole p")
public class OmsUsuariosRole extends EntidadBaseAuditable<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The descripcion. */
	private String descripcion;

	/** The pri role. */
	private OmsRole priRole;

	/** The pri usuario. */
	private OmsUsuario priUsuario;

	/**
	 * Instantiates a new oms usuarios role.
	 */
	public OmsUsuariosRole() {
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secUsuarioRol", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secUsuarioRol", allocationSize = 1, initialValue = 1, sequenceName = "SEC_USUARIOROL")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.base.EntidadBase#setId(java.io.Serializable)
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the descripcion.
	 *
	 * @return the descripcion
	 */
	@Column(name = "DESCRIPCION")
	/**/
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Sets the descripcion.
	 *
	 * @param descripcion the new descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Gets the pri role.
	 *
	 * @return the pri role
	 */
	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	public OmsRole getPriRole() {
		return this.priRole;
	}

	/**
	 * Sets the pri role.
	 *
	 * @param priRole the new pri role
	 */
	public void setPriRole(OmsRole priRole) {
		this.priRole = priRole;
	}

	/**
	 * Gets the pri usuario.
	 *
	 * @return the pri usuario
	 */
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public OmsUsuario getPriUsuario() {
		return this.priUsuario;
	}

	/**
	 * Sets the pri usuario.
	 *
	 * @param priUsuario the new pri usuario
	 */
	public void setPriUsuario(OmsUsuario priUsuario) {
		this.priUsuario = priUsuario;
	}
	
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable)idReferencia);
		
	}

}