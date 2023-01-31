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
 * The persistent class for the PRI_ACCESOS_DIRECTOS database table.
 *   
 */
@Entity
@Table(name = "ONIX_ACCESOS_DIRECTOS")
@NamedQuery(name = "OmsAccesosDirecto.findAll", query = "SELECT p FROM OmsAccesosDirecto p")
public class OmsAccesosDirecto extends EntidadBaseAuditable<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The pri opcione. */
	private OmsOpcione priOpcione;

	/** The pri usuario. */
	private OmsUsuario priUsuario;

	/**
	 * Instantiates a new oms accesos directo.
	 */
	public OmsAccesosDirecto() {
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secIDAcceso", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secIDAcceso", allocationSize = 1, initialValue = 1, sequenceName = "SEC_IDACCESO")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	/**
	 * Gets the pri opcione.
	 *
	 * @return the pri opcione
	 */
	@ManyToOne
	@JoinColumn(name = "ID_OPCION")
	public OmsOpcione getPriOpcione() {
		return priOpcione;
	}

	/**
	 * Sets the pri opcione.
	 *
	 * @param priOpcione the new pri opcione
	 */
	public void setPriOpcione(OmsOpcione priOpcione) {
		this.priOpcione = priOpcione;
	}

	/**
	 * Gets the pri usuario.
	 *
	 * @return the pri usuario
	 */
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	public OmsUsuario getPriUsuario() {
		return priUsuario;
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