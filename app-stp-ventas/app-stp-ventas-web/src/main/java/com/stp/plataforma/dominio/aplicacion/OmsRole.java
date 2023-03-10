package com.stp.plataforma.dominio.aplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the PRI_ROLES database table.
 * 
 */
@Entity
@Table(name = "ONIX_ROLES")
@NamedQuery(name = "OmsRole.findAll", query = "SELECT p FROM OmsRole p")
public class OmsRole extends EntidadBaseAuditable<Long> implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The aplica aud. */
	private String aplicaAud;

	/** The rol. */
	private String rol;

	/** The tipo rol. */
	private String tipoRol;
	
	
	
	

	/** The lista opciones roles. */
	private List<OmsOpcionesRole> listaOpcionesRoles;

	// bi-directional many-to-one association to PriUsuariosRole

	/** The pri usuarios roles. */
	private List<OmsUsuariosRole> priUsuariosRoles;
	
	/** The l pagina principal. */
	private String lPaginaPrincipal;
	
	/** The seleccionable. */
	private String seleccionable;
	
	private String ambito; //puede ser A ambos, E sólo empresa, AD solo admin

	/**
	 * Instantiates a new oms role.
	 */
	public OmsRole() {
	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.dominio.entidades.IEntidadPersistible#getId()
	 */
	@GeneratedValue(generator = "secRole", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "secRole", allocationSize = 1, initialValue = 1, sequenceName = "SEC_ROLE")
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
	 * Gets the aplica aud.
	 *
	 * @return the aplica aud
	 */
	@Column(name = "APLICA_AUD")
	public String getAplicaAud() {
		return this.aplicaAud;
	}

	/**
	 * Sets the aplica aud.
	 *
	 * @param aplicaAud the new aplica aud
	 */
	public void setAplicaAud(String aplicaAud) {
		this.aplicaAud = aplicaAud;
	}

	/**
	 * Gets the rol.
	 *
	 * @return the rol
	 */
	@Column(name = "ROL")
	public String getRol() {
		return this.rol;
	}

	/**
	 * Sets the rol.
	 *
	 * @param rol the new rol
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * Gets the tipo rol.
	 *
	 * @return the tipo rol
	 */
	@Column(name = "TIPO_ROL")
	public String getTipoRol() {
		return this.tipoRol;
	}

	/**
	 * Sets the tipo rol.
	 *
	 * @param tipoRol the new tipo rol
	 */
	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}

	/**
	 * Gets the pri usuarios roles.
	 *
	 * @return the pri usuarios roles
	 */
	@OneToMany(mappedBy = "priRole")
	public List<OmsUsuariosRole> getPriUsuariosRoles() {
		return this.priUsuariosRoles;
	}

	/**
	 * Sets the pri usuarios roles.
	 *
	 * @param priUsuariosRoles the new pri usuarios roles
	 */
	public void setPriUsuariosRoles(List<OmsUsuariosRole> priUsuariosRoles) {
		this.priUsuariosRoles = priUsuariosRoles;
	}

	/**
	 * Adds the pri usuarios role.
	 *
	 * @param priUsuariosRole the pri usuarios role
	 * @return the oms usuarios role
	 */
	public OmsUsuariosRole addPriUsuariosRole(OmsUsuariosRole priUsuariosRole) {
		getPriUsuariosRoles().add(priUsuariosRole);
		priUsuariosRole.setPriRole(this);

		return priUsuariosRole;
	}

	/**
	 * Removes the pri usuarios role.
	 *
	 * @param priUsuariosRole the pri usuarios role
	 * @return the oms usuarios role
	 */
	public OmsUsuariosRole removePriUsuariosRole(OmsUsuariosRole priUsuariosRole) {
		getPriUsuariosRoles().remove(priUsuariosRole);
		priUsuariosRole.setPriRole(null);

		return priUsuariosRole;
	}

	/**
	 * Gets the lista opciones roles.
	 *
	 * @return the lista opciones roles
	 */
	@OneToMany(mappedBy = "idRol")
	public List<OmsOpcionesRole> getListaOpcionesRoles() {
		return listaOpcionesRoles;
	}

	/**
	 * Sets the lista opciones roles.
	 *
	 * @param listaOpcionesRoles the new lista opciones roles
	 */
	public void setListaOpcionesRoles(List<OmsOpcionesRole> listaOpcionesRoles) {
		this.listaOpcionesRoles = listaOpcionesRoles;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.rol;
	}

	/**
	 * Agregar opciones roles.
	 *
	 * @param opcionRole the opcion role
	 */
	public void agregarOpcionesRoles(OmsOpcionesRole opcionRole) {
		if (listaOpcionesRoles == null) 
		{
			listaOpcionesRoles = new ArrayList<>();
		}
		opcionRole.setIdRol(this);
		this.listaOpcionesRoles.add(opcionRole);
	}

	/**
	 * Agregar opciones roles.
	 *
	 * @param opcionesRoles the opciones roles
	 */
	public void agregarOpcionesRoles(List<OmsOpcionesRole> opcionesRoles) {
		for (OmsOpcionesRole opcionRol : opcionesRoles) {
			agregarOpcionesRoles(opcionRol);
		}
	}

	/**
	 * Agregar opciones.
	 *
	 * @param opciones the opciones
	 */
	public void agregarOpciones(List<OmsOpcione> opciones) {
		for (OmsOpcione opcion : opciones) {
			OmsOpcionesRole opcionesRoles = new OmsOpcionesRole();
			opcionesRoles.setEstado("A");
			opcionesRoles.setFechaRegistro(new Date());
			opcionesRoles.setObservacion("DESCRIPCION ASIGNACION: " + this.getRol() + " - " + opcion.getDescripcion());
			opcionesRoles.setPriOpcione(opcion);
			agregarOpcionesRoles(opcionesRoles);
		}
	}

	/**
	 * Agregar opciones.
	 *
	 * @param opcion the opcion
	 */
	public void agregarOpciones(OmsOpcione opcion) {

		OmsOpcionesRole opcionesRoles = new OmsOpcionesRole();
		opcionesRoles.setEstado("A");
		opcionesRoles.setFechaRegistro(new Date());
		opcionesRoles.setObservacion("DESCRIPCION ASIGNACION: " + this.getRol() + " - " + opcion.getDescripcion());
		opcionesRoles.setPriOpcione(opcion);
		agregarOpcionesRoles(opcionesRoles);

	}
	
	/**
	 * Gets the l pagina principal.
	 *
	 * @return the l pagina principal
	 */
	@Column(name="PAGINA_PRINCIPAL")
	public String getlPaginaPrincipal() {
		return lPaginaPrincipal;
	}
	
	/**
	 * Sets the l pagina principal.
	 *
	 * @param lPaginaPrincipal the new l pagina principal
	 */
	public void setlPaginaPrincipal(String lPaginaPrincipal) {
		this.lPaginaPrincipal = lPaginaPrincipal;
	}

	/**
	 * Gets the seleccionable.
	 *
	 * @return the seleccionable
	 */
	@Column(name="SELECCIONABLE")
	public String getSeleccionable() {
		return seleccionable;
	}

	/**
	 * Sets the seleccionable.
	 *
	 * @param seleccionable the new seleccionable
	 */
	public void setSeleccionable(String seleccionable) {
		this.seleccionable = seleccionable;
	}

	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable)idReferencia);
	}

	public String getAmbito() {
		return ambito;
	}

	public void setAmbito(String ambito) {
		this.ambito = ambito;
	}
	
	
	
}