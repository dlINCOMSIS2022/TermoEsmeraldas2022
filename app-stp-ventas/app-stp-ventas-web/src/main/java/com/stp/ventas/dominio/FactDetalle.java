package com.stp.ventas.dominio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "ONIX_DET_FACTURA")
public class FactDetalle extends EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPrincipal;
	private String codigoAuxiliar;
	private String detallesAdicionales;
	private String descripcion;
	private Long cantidad;
	private Double precioUnitario;
	private Double descuento;
	private Double precioTotalSinImpuesto;
	
	private Double precioTotalConImpuesto;

	private PosProducto lProducto;
	private FactCabecera lCabecera;

	private List<FactDetalleImpuesto> lListaImpuestos;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(generator = "FactDetalle", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactDetalle", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_DETALLE")
	public Long getId() {
		return id;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);
	}

	public String getCodigoPrincipal() {
		return codigoPrincipal;
	}

	public void setCodigoPrincipal(String codigoPrincipal) {
		this.codigoPrincipal = codigoPrincipal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public Double getPrecioTotalSinImpuesto() {
		return precioTotalSinImpuesto;
	}

	public void setPrecioTotalSinImpuesto(Double precioTotalSinImpuesto) {
		this.precioTotalSinImpuesto = precioTotalSinImpuesto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	public PosProducto getlProducto() {
		return lProducto;
	}

	public void setlProducto(PosProducto lProducto) {
		this.lProducto = lProducto;
	}

	@ManyToOne
	@JoinColumn(name = "ID_FACTURA")
	public FactCabecera getlCabecera() {
		return lCabecera;
	}

	public void setlCabecera(FactCabecera lCabecera) {
		this.lCabecera = lCabecera;
	}

	@OneToMany(mappedBy = "lDetalleFactura")
	public List<FactDetalleImpuesto> getlListaImpuestos() {
		return lListaImpuestos;
	}

	public void setlListaImpuestos(List<FactDetalleImpuesto> lListaImpuestos) {
		this.lListaImpuestos = lListaImpuestos;
	}

	public String getCodigoAuxiliar() {
		return codigoAuxiliar;
	}

	public void setCodigoAuxiliar(String codigoAuxiliar) {
		this.codigoAuxiliar = codigoAuxiliar;
	}

	public String getDetallesAdicionales() {
		return detallesAdicionales;
	}

	public void setDetallesAdicionales(String detallesAdicionales) {
		this.detallesAdicionales = detallesAdicionales;
	}

	public Double getPrecioTotalConImpuesto() {
		return precioTotalConImpuesto;
	}

	public void setPrecioTotalConImpuesto(Double precioTotalConImpuesto) {
		this.precioTotalConImpuesto = precioTotalConImpuesto;
	}
	
	

}
