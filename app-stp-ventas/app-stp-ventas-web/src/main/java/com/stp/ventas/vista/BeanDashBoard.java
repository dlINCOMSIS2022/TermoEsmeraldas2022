package com.stp.ventas.vista;

import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stp.ventas.dominio.FactCabecera;
import com.stp.ventas.dominio.FactCliente;
import com.stp.ventas.dominio.FactEmpresa;
import com.stp.ventas.servicios.ServicioMantenedorFactCabecera;
import com.stp.ventas.servicios.ServicioMantenedorFactCliente;
import com.stp.ventas.servicios.ServicioMantenedorFactEmpresa;

import librerias.vista.JsfUtil;

@ManagedBean
@ViewScoped
public class BeanDashBoard {

	private List<FactEmpresa> lListaEmpresa;

	private List<FactCabecera> lListaMisFacturas;

	@Inject
	private ServicioMantenedorFactEmpresa lServicioUsuarioEmpresa;

	@EJB
	private ServicioMantenedorFactCabecera lServicioFactCab;
	
	private List<FactCliente> lListaCliente;

	@EJB
	private ServicioMantenedorFactCliente lServicioMantenedorCliente;
	
	@Inject
	private BeanControlFactura lFacturaSesion;
	
	@PostConstruct
	public void init() {
		HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
		lParametrosEmpresa.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
		String lQuery = "select * from onix_empresa where id in ( "
				+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b " + "where a.usuario = :usuario "
				+ "and a.estado = 'A' " + "and b.id_usuario = a.id " + "and b.estado = 'A') " + "and estado = 'A'";
		lListaEmpresa = lServicioUsuarioEmpresa.ejecutarQueryNativoObjeto(lQuery, lParametrosEmpresa,
				FactEmpresa.class);

		HashMap<String, Object> lParametrosFactura = new HashMap<>();
		lParametrosFactura.put("usuario", JsfUtil.getUsuarioAutenticado().getName());

		String lQueryDoc = "select * from onix_cab_factura where campo_auditoria = :usuario and id_empresa  in ( "
				+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b " + "where a.usuario = :usuario "
				+ "and a.estado = 'A' " + "and b.id_usuario = a.id " + "and b.estado = 'A') " + "and estado = 'A' and estadoProceso != 'FI' order by fecha_registro desc";

		lListaMisFacturas = lServicioFactCab.ejecutarQueryNativoObjeto(lQueryDoc, lParametrosFactura,
				FactCabecera.class);

		
		HashMap<String, Object> lParametro = new HashMap<>();
		lParametro.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
		String lquery = "select * from ONIX_CLIENTES where estado = 'A' and ID_EMPRESA  in ( "
				+ " select b.id_empresa from onix_usuarios a, onix_usuario_empresa b "
				+ " where a.usuario = :usuario " + " and a.estado = 'A' " + " and b.id_usuario = a.id "
				+ " and b.estado = 'A' ) ";
		
		lListaCliente = lServicioMantenedorCliente.ejecutarQueryNativoObjeto(lquery,
				lParametro, FactCliente.class);
		
	}

	public List<FactEmpresa> getlListaEmpresa() {
		return lListaEmpresa;
	}

	public void setlListaEmpresa(List<FactEmpresa> lListaEmpresa) {
		this.lListaEmpresa = lListaEmpresa;
	}

	public List<FactCabecera> getlListaMisFacturas() {
		return lListaMisFacturas;
	}

	public void setlListaMisFacturas(List<FactCabecera> lListaMisFacturas) {
		this.lListaMisFacturas = lListaMisFacturas;
	}

	public List<FactCliente> getlListaCliente() {
		return lListaCliente;
	}

	public void setlListaCliente(List<FactCliente> lListaCliente) {
		this.lListaCliente = lListaCliente;
	}
	
	public String seleccionarDocumento(Long idFactura) {
		FactCabecera lCabecera = lServicioFactCab.obtenerObjtoPK(idFactura, FactCabecera.class);
		lFacturaSesion.setlCabeceraFactura(lCabecera);
		return "/privadas/modulos/ventas/operacion/pag_facturacion";
	}
	
	

}
