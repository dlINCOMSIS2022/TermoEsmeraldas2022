package com.stp.ventas.vista;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.apache.commons.lang.time.DateUtils;

import com.stp.ventas.dominio.FactCabecera;
import com.stp.ventas.dominio.FactDetalle;
import com.stp.ventas.dominio.FactFormaPagosFactura;
import com.stp.ventas.servicios.ServicioMantenedorFactCabecera;
import com.stp.ventas.servicios.ServicioMantenedorFactDetalle;
import com.stp.ventas.servicios.ServicioMantenedorFormaPagoFactura;

import librerias.exceptions.ErrorServicioNegocio;
import librerias.exceptions.ErrorValidacionGeneral;
import librerias.vista.JsfUtil;

@ManagedBean
@ViewScoped
public class BeanConsultaFacturacion {

	private List<FactCabecera> lListaFacturasEmitidas;

	private List<FactCabecera> lListaFacturasAutorizadas;

	@EJB
	private ServicioMantenedorFactCabecera lServicioFactCab;

	@Inject
	private BeanControlFactura lFacturaSesion;

	@EJB
	private ServicioMantenedorFactDetalle lServicioDetalle;

	@EJB
	private ServicioMantenedorFormaPagoFactura lServicioFormaPagoFact;

	private Date lFechaDesde;

	private Date lFechaHasta;

	@PostConstruct
	public void init() {

		Calendar lCal = new GregorianCalendar();
		lCal.add(Calendar.MONTH, -1);

		lFechaDesde = lCal.getTime();

		lFechaHasta = new Date();

		cargarTablaFacturas();

	}

	public void buscarFacturas() {
		cargarTablaFacturas();
	}

	private void cargarTablaFacturas() {
		HashMap<String, Object> lParametrosFactura = new HashMap<>();
		lParametrosFactura.put("usuario", JsfUtil.getUsuarioAutenticado().getName());

		Calendar calDesde = new GregorianCalendar();

		calDesde.setTime(lFechaDesde);
		calDesde.set(Calendar.HOUR, 0);
		calDesde.set(Calendar.MINUTE, 0);
		calDesde.set(Calendar.SECOND, 0);

		Calendar calHasta = new GregorianCalendar();

		calHasta.setTime(lFechaHasta);
		calHasta.set(Calendar.HOUR, 59);
		calHasta.set(Calendar.MINUTE, 59);
		calHasta.set(Calendar.SECOND, 59);

		lParametrosFactura.put("fechaDesde", calDesde.getTime());
		lParametrosFactura.put("fechaHasta", calHasta.getTime());

		String lQueryDoc = "select * from onix_cab_factura where campo_auditoria = :usuario and id_empresa  in ( "
				+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b " + "where a.usuario = :usuario "
				+ "and a.estado = 'A' " + "and b.id_usuario = a.id " + "and b.estado = 'A') "
				+ "and estado = 'A' and estadoProceso = 'FI' "
				+ "and fechaemision >= :fechaDesde and fechaemision <= :fechaHasta " + "order by fecha_registro desc";

		String lQueryDocA = "select * from onix_cab_factura where campo_auditoria = :usuario and id_empresa  in ( "
				+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b " + "where a.usuario = :usuario "
				+ "and a.estado = 'A' " + "and b.id_usuario = a.id " + "and b.estado = 'A') "
				+ "and estado = 'A' and estadoProceso = 'AU' "
				+ "and fechaemision >= :fechaDesde and fechaemision <= :fechaHasta " + "order by fecha_registro desc";

		lListaFacturasEmitidas = lServicioFactCab.ejecutarQueryNativoObjeto(lQueryDoc, lParametrosFactura,
				FactCabecera.class);

		lListaFacturasAutorizadas = lServicioFactCab.ejecutarQueryNativoObjeto(lQueryDocA, lParametrosFactura,
				FactCabecera.class);
	}

	public List<FactCabecera> getlListaFacturasEmitidas() {
		return lListaFacturasEmitidas;
	}

	public void setlListaFacturasEmitidas(List<FactCabecera> lListaFacturasEmitidas) {
		this.lListaFacturasEmitidas = lListaFacturasEmitidas;
	}

	public List<FactCabecera> getlListaFacturasAutorizadas() {
		return lListaFacturasAutorizadas;
	}

	public void setlListaFacturasAutorizadas(List<FactCabecera> lListaFacturasAutorizadas) {
		this.lListaFacturasAutorizadas = lListaFacturasAutorizadas;
	}

	public boolean filterByDate(Object value, Object filter, Locale locale) {

		if (filter == null) {
			return true;
		}

		if (value == null) {
			return false;
		}

		java.sql.Timestamp lvalue = (java.sql.Timestamp) value;
		LocalDate lfilter = (LocalDate) filter;

		return DateUtils.truncatedEquals(new Date(lvalue.getTime()),
				Date.from(lfilter.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), Calendar.DATE);
	}

	public String abrirFactura(Long idFactura) {
		FactCabecera lCabecera = lServicioFactCab.obtenerObjtoPK(idFactura, FactCabecera.class);

		lCabecera.setFechaActualizacion(new Date());
		lCabecera.setEstadoProceso("DP");
		lCabecera.setAuditoria(JsfUtil.getUsuarioAutenticado().getName());

		try {
			lServicioFactCab.guardarActualizar(lCabecera);
		} catch (ErrorServicioNegocio e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorValidacionGeneral e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		lFacturaSesion.setlCabeceraFactura(lCabecera);
		return "/privadas/modulos/ventas/operacion/pag_facturacion";
	}

	public void verFactura(ActionEvent lEvento) {
		FactCabecera lCabecera = (FactCabecera) lEvento.getComponent().getAttributes().get("FACT");

		String lQuery = "select * from ONIX_DET_FACTURA where ID_FACTURA = :idFactura";
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idFactura", lCabecera.getId());
		List<FactDetalle> lListaDetalles = lServicioDetalle.ejecutarQueryNativoObjeto(lQuery, lParametros,
				FactDetalle.class);
		lCabecera.setlListaDetalles(lListaDetalles);

		String lQuery2 = "select * from ONIX_FORMAS_PAGO_FACTURA where ID_FACTURA = :idFactura";
		HashMap<String, Object> lParametros2 = new HashMap<>();
		lParametros2.put("idFactura", lCabecera.getId());
		List<FactFormaPagosFactura> lListaFormaPago = lServicioFormaPagoFact.ejecutarQueryNativoObjeto(lQuery2,
				lParametros2, FactFormaPagosFactura.class);
		lCabecera.setlFormaPagos(lListaFormaPago);

		lFacturaSesion.setlCabeceraFactura(lCabecera);

	}

	public Date getlFechaDesde() {
		return lFechaDesde;
	}

	public void setlFechaDesde(Date lFechaDesde) {
		this.lFechaDesde = lFechaDesde;
	}

	public Date getlFechaHasta() {
		return lFechaHasta;
	}

	public void setlFechaHasta(Date lFechaHasta) {
		this.lFechaHasta = lFechaHasta;
	}

}
