package com.stp.ventas.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@Table(name = "POS_PRODUCTO")
@NamedQuery(name = "Producto.findAll", query = "SELECT p FROM PosProducto p")
public class PosProducto extends  EntidadBaseAuditable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String lCodigoProducto;
	
	private String lCodigoAuxiliar;

	private String lDescripcion;

	private Double lExistencia;

	private String lNombre;

	private Double lPrecioUnitario;

	private Long idImpuestoIVA;
	
	private Long idImpuestoICE;
	
	private Double lPrecioIVA;
	
	private Double lPrecioICE;

	private Integer lImpuestoIva;

	private Integer lImpuestoIce;

	private String lDetallesAdicionales;

	private Double lPorcentajeIva;

	private Double lPorcentajeICE;

	private FactEmpresa lEmpresa;
	
	private PosTipoProducto tipoProducto;
	
	private byte[] imagenReferencia;
	
	private List<FactImpuestosProductos> lListaImpuestos;

	public PosProducto() {
	}

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactProducto", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactProducto", allocationSize = 1, initialValue = 1, sequenceName = "SEC_POS_PRODUCTO")
	public Long getId() {
		return id;
	}

	public String getlDescripcion() {
		return lDescripcion;
	}

	public void setlDescripcion(String lDescripcion) {
		this.lDescripcion = lDescripcion;
	}

	public Double getlExistencia() {
		return lExistencia;
	}

	public void setlExistencia(Double lExistencia) {
		this.lExistencia = lExistencia;
	}

	public String getlNombre() {
		return lNombre;
	}

	public void setlNombre(String lNombre) {
		this.lNombre = lNombre;
	}

	public Double getlPrecioUnitario() {
		return lPrecioUnitario;
	}

	public void setlPrecioUnitario(Double lPrecioUnitario) {
		this.lPrecioUnitario = lPrecioUnitario;
	}

	public Integer getlImpuestoIva() {
		return lImpuestoIva;
	}

	public void setlImpuestoIva(Integer lImpuestoIva) {
		this.lImpuestoIva = lImpuestoIva;
	}

	public Integer getlImpuestoIce() {
		return lImpuestoIce;
	}

	public void setlImpuestoIce(Integer lImpuestoIce) {
		this.lImpuestoIce = lImpuestoIce;
	}

	public String getlCodigoProducto() {
		return lCodigoProducto;
	}

	public void setlCodigoProducto(String lCodigoProducto) {
		this.lCodigoProducto = lCodigoProducto;
	}

	public String getlDetallesAdicionales() {
		return lDetallesAdicionales;
	}

	public void setlDetallesAdicionales(String lDetallesAdicionales) {
		this.lDetallesAdicionales = lDetallesAdicionales;
	}

	

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable)idReferencia);
	}

	public Double getlPrecioIVA() {
		return lPrecioIVA;
	}

	public void setlPrecioIVA(Double lPrecioIVA) {
		this.lPrecioIVA = lPrecioIVA;
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
	@JoinColumn(name="ID_TIPO")
	public PosTipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(PosTipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
	@Lob
	@Column(name = "IMAGEN_REFERENCIA")
	public byte[] getImagenReferencia() {
		return imagenReferencia;
	}

	/**
	 * Sets the imagen referencia.
	 *
	 * @param imagenReferencia the new imagen referencia
	 */
	public void setImagenReferencia(byte[] imagenReferencia) {
		this.imagenReferencia = imagenReferencia;
	}

	public String getlCodigoAuxiliar() {
		return lCodigoAuxiliar;
	}

	public void setlCodigoAuxiliar(String lCodigoAuxiliar) {
		this.lCodigoAuxiliar = lCodigoAuxiliar;
	}

	public Double getlPorcentajeIva() {
		return lPorcentajeIva;
	}

	public void setlPorcentajeIva(Double lPorcentajeIva) {
		this.lPorcentajeIva = lPorcentajeIva;
	}

	public Double getlPrecioICE() {
		return lPrecioICE;
	}

	public void setlPrecioICE(Double lPrecioICE) {
		this.lPrecioICE = lPrecioICE;
	}

	public Double getlPorcentajeICE() {
		return lPorcentajeICE;
	}

	public void setlPorcentajeICE(Double lPorcentajeICE) {
		this.lPorcentajeICE = lPorcentajeICE;
	}

	@OneToMany(mappedBy = "lProducto", fetch = FetchType.EAGER )
	public List<FactImpuestosProductos> getlListaImpuestos() {
		return lListaImpuestos;
	}

	public void setlListaImpuestos(List<FactImpuestosProductos> lListaImpuestos) {
		this.lListaImpuestos = lListaImpuestos;
	}

	public Long getIdImpuestoIVA() {
		return idImpuestoIVA;
	}

	public void setIdImpuestoIVA(Long idImpuestoIVA) {
		this.idImpuestoIVA = idImpuestoIVA;
	}

	public Long getIdImpuestoICE() {
		return idImpuestoICE;
	}

	public void setIdImpuestoICE(Long idImpuestoICE) {
		this.idImpuestoICE = idImpuestoICE;
	}
	
	

}