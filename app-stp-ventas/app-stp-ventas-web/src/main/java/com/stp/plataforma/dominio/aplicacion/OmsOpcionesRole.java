package com.stp.plataforma.dominio.aplicacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

// TODO: Auto-generated Javadoc
//import java.math.BigDecimal;

/**
 * The persistent class for the PRI_OPCIONES_ROLES database table.
 * 
 */
//PRI
@Entity
@Table(name = "ONIX_OPCIONES_ROLES")
@NamedNativeQueries({@NamedNativeQuery(name = "OPCIONES_ROLES.OPCIONES_PADRE", query = "select * "
		+ " from ONIX_OPCIONES d " 
		+ " where exists (select 'X' " 
		+ "        from ONIX_OPCIONES_ROLES s "
		+ "       where exists (select 'X' " + "                from ONIX_USUARIOS_ROLES t "
		+ "               where t.id_usuario = ? " + "                 and t.estado = 'A' "
		+ "                 and t.id_rol = s.id_rol) " + "         and s.estado = 'A' "
		+ "         and s.id_opcion = d.id) " 
		+ " and d.modulo_padre is null " 
		+ " and d.estado = 'A'"
		+ " and d.orientacion = ? order by d.orden asc", resultClass = OmsOpcione.class), 
		
		@NamedNativeQuery(name = "OPCIONES_ROLES.OPCIONES_PADRE_ROL", query = "select * "
				+ " from ONIX_OPCIONES d " 
				+ " where exists (select 'X' " 
				+ "        from ONIX_OPCIONES_ROLES s "
				+ "       where s.id_rol = :idRol    and s.estado = 'A' "
				+ "         and s.id_opcion = d.id) " 
				+ " and d.modulo_padre is null " 
				+ " and d.estado = 'A'"
				+ " and d.orientacion = :orientacion order by d.orden asc", resultClass = OmsOpcione.class)})
public class OmsOpcionesRole extends EntidadBaseAuditable<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The id rol. */
	private OmsRole idRol;

	/** The pri opcione. */
	private OmsOpcione priOpcione;

	/**
	 * Instantiates a new oms opciones role.
	 */
	public OmsOpcionesRole() {
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secOpcionesRol", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secOpcionesRol", allocationSize = 1, initialValue = 1, sequenceName = "SEC_OPCIONESROL")
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
	 * Gets the id rol.
	 *
	 * @return the id rol
	 */
	@ManyToOne
	@JoinColumn(name = "ID_ROL")
	public OmsRole getIdRol() {
		return idRol;
	}

	/**
	 * Sets the id rol.
	 *
	 * @param idRol the new id rol
	 */
	public void setIdRol(OmsRole idRol) {
		this.idRol = idRol;
	}

	/**
	 * Gets the pri opcione.
	 *
	 * @return the pri opcione
	 */
	@ManyToOne
	@JoinColumn(name = "ID_OPCION")
	public OmsOpcione getPriOpcione() {
		return this.priOpcione;
	}

	/**
	 * Sets the pri opcione.
	 *
	 * @param priOpcione the new pri opcione
	 */
	public void setPriOpcione(OmsOpcione priOpcione) {
		this.priOpcione = priOpcione;
	}

	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable)idReferencia);
		
	}
}