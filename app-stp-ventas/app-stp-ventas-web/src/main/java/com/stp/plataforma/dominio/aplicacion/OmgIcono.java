package com.stp.plataforma.dominio.aplicacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

// TODO: Auto-generated Javadoc
/**
 * The Class OmgIcono.
 */
@Entity
@Table(name="ONIX_ICONOS")
public class OmgIcono extends EntidadBaseAuditable<Long> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	  
	/** The l icono font primefaces. */
	private String lIconoFontPrimefaces;


	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secIcono", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secIcono", allocationSize = 1, initialValue = 1, sequenceName = "SEC_ICONOS")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}
	
	/**
	 * Gets the l icono font primefaces.
	 *
	 * @return the l icono font primefaces
	 */
	public String getlIconoFontPrimefaces() {
		return lIconoFontPrimefaces;
	}
	
	/**
	 * Sets the l icono font primefaces.
	 *
	 * @param lIconoFontPrimefaces the new l icono font primefaces
	 */
	public void setlIconoFontPrimefaces(String lIconoFontPrimefaces) {
		this.lIconoFontPrimefaces = lIconoFontPrimefaces;
	}
	
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable)idReferencia);
		
	}
}
