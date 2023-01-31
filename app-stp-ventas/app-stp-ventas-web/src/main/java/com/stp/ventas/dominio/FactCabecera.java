package com.stp.ventas.dominio;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;

import librerias.dominio.entidades.base.EntidadBaseAuditable;

@Entity
@Table(name = "ONIX_CAB_FACTURA")
public class FactCabecera extends EntidadBaseAuditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ambiente;
	private String estadoProceso;
	private String tipoEmision;
	private String razonSocial;
	private String nombreComercial;
	private String ruc;
	private String claveAcceso;
	private String codDoc;
	private String estab;
	private String ptoEmi;
	private String secuencial;
	private String dirMatriz;
	private Date fechaEmision;
	private String dirEstablecimiento;
	private String obligadoContabilidad;
	private String noResolucion;
	private String tipoIdentificacionComprador;
	private String razonSocialComprador;
	private String identificacionComprador;
	private String direccionComprador;
	private Double totalSinImpuestos;
	private Double totalDescuento;
	private Double propina;
	private Double importeTotal;
	private String moneda;
	
	private Double totalImpuesto = 0D;
	private Double subTotal12 = 0D;
	private Double subTotal0 = 0D;

	private FactPuntosEmision lPuntoEmision;
	private FactEmpresa lEmpresa;

	private FactCliente lCliente;

	private List<FactFormaPagosFactura> lFormaPagos;
	
	private List<FactCabeceraImpuesto> lListaCabeceraImpuesto;

	private List<FactDetalle> lListaDetalles;
	
	private Double totalFormaPago;

	@GeneratedValue(generator = "FactCabecera", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "FactCabecera", allocationSize = 1, initialValue = 1, sequenceName = "SEC_FACT_CABECERA")
	@Id
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public String getNoResolucion() {
		return noResolucion;
	}

	public void setNoResolucion(String noResolucion) {
		this.noResolucion = noResolucion;
	}

	@Override
	public void setIdReferencia(Long idReferencia) {
		super.setIdReferencia((Serializable) idReferencia);
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getTipoEmision() {
		return tipoEmision;
	}

	public void setTipoEmision(String tipoEmision) {
		this.tipoEmision = tipoEmision;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public String getClaveAcceso() {
		return claveAcceso;
	}

	public void setClaveAcceso(String claveAcceso) {
		this.claveAcceso = claveAcceso;
	}

	public String getCodDoc() {
		return codDoc;
	}

	public void setCodDoc(String codDoc) {
		this.codDoc = codDoc;
	}

	public String getEstab() {
		return estab;
	}

	public void setEstab(String estab) {
		this.estab = estab;
	}

	public String getPtoEmi() {
		return ptoEmi;
	}

	public void setPtoEmi(String ptoEmi) {
		this.ptoEmi = ptoEmi;
	}

	public String getSecuencial() {
		return secuencial;
	}

	public void setSecuencial(String secuencial) {
		this.secuencial = secuencial;
	}

	public String getDirMatriz() {
		return dirMatriz;
	}

	public void setDirMatriz(String dirMatriz) {
		this.dirMatriz = dirMatriz;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public String getDirEstablecimiento() {
		return dirEstablecimiento;
	}

	public void setDirEstablecimiento(String dirEstablecimiento) {
		this.dirEstablecimiento = dirEstablecimiento;
	}

	public String getObligadoContabilidad() {
		return obligadoContabilidad;
	}

	public void setObligadoContabilidad(String obligadoContabilidad) {
		this.obligadoContabilidad = obligadoContabilidad;
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

	public Double getTotalSinImpuestos() {
		return totalSinImpuestos;
	}

	public void setTotalSinImpuestos(Double totalSinImpuestos) {
		this.totalSinImpuestos = totalSinImpuestos;
	}

	public Double getTotalDescuento() {
		return totalDescuento;
	}

	public void setTotalDescuento(Double totalDescuento) {
		this.totalDescuento = totalDescuento;
	}

	public Double getPropina() {
		return propina;
	}

	public void setPropina(Double propina) {
		this.propina = propina;
	}

	public Double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PUNTO_EMISION")
	public FactPuntosEmision getlPuntoEmision() {
		return lPuntoEmision;
	}

	public void setlPuntoEmision(FactPuntosEmision lPuntoEmision) {
		this.lPuntoEmision = lPuntoEmision;
	}

	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA")
	public FactEmpresa getlEmpresa() {
		return lEmpresa;
	}

	public void setlEmpresa(FactEmpresa lEmpresa) {
		this.lEmpresa = lEmpresa;
	}

	public FactCliente getlCliente() {
		return lCliente;
	}

	public void setlCliente(FactCliente lCliente) {
		this.lCliente = lCliente;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	@OneToMany(mappedBy = "lCabecera")
	public List<FactDetalle> getlListaDetalles() {
		return lListaDetalles;
	}

	public void setlListaDetalles(List<FactDetalle> lListaDetalles) {
		this.lListaDetalles = lListaDetalles;
	}

	@OneToMany(mappedBy = "lFactura")
	public List<FactCabeceraImpuesto> getlListaCabeceraImpuesto() {
		return lListaCabeceraImpuesto;
	}

	public void setlListaCabeceraImpuesto(List<FactCabeceraImpuesto> lListaCabeceraImpuesto) {
		this.lListaCabeceraImpuesto = lListaCabeceraImpuesto;
	}

	public Double getTotalImpuesto() {
		return totalImpuesto;
	}

	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}

	public Double getSubTotal12() {
		return subTotal12;
	}

	public void setSubTotal12(Double subTotal12) {
		this.subTotal12 = subTotal12;
	}

	public Double getSubTotal0() {
		return subTotal0;
	}

	public void setSubTotal0(Double subTotal0) {
		this.subTotal0 = subTotal0;
	}

	@OneToMany(mappedBy = "lFactura")
	public List<FactFormaPagosFactura> getlFormaPagos() {
		return lFormaPagos;
	}

	public void setlFormaPagos(List<FactFormaPagosFactura> lFormaPagos) {
		this.lFormaPagos = lFormaPagos;
	}

	@Transient
	public Double getTotalFormaPago() {
		return totalFormaPago;
	}

	public void setTotalFormaPago(Double totalFormaPago) {
		this.totalFormaPago = totalFormaPago;
	}
	
	

}
