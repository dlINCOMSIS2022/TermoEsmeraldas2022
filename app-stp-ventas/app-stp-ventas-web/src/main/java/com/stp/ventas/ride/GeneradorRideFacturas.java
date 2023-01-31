package com.stp.ventas.ride;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.stp.ventas.dominio.FactCabecera;
import com.stp.ventas.dominio.FactCabeceraImpuesto;
import com.stp.ventas.dominio.FactDetalle;
import com.stp.ventas.dominio.FactDetalleImpuesto;
import com.stp.ventas.dominio.FactFormaPagosFactura;
import com.stp.ventas.dto.RideFacturaCab;
import com.stp.ventas.dto.RideFacturaDet;
import com.stp.ventas.dto.RideFormaPago;
import com.stp.ventas.dto.RideInfoAdicional;
import com.stp.ventas.servicios.ServicioMantenedorDetalleImpuesto;
import com.stp.ventas.servicios.ServicioMantenedorImpuestoFactura;
import com.stp.ventas.util.ConvertirNumerosLetras;
import com.stp.ventas.util.INombresParametros;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

@Named
public class GeneradorRideFacturas {

	private final String SIGNO_DOLAR = "$";
	private SimpleDateFormat lFormatoFecha = new SimpleDateFormat("dd/MM/yyyy");

	@Inject
	private ServicioMantenedorDetalleImpuesto lServicioDetalleImp;

	@Inject
	private ServicioMantenedorImpuestoFactura lServicioImpuestoFact;

	private GeneradorRideFacturas() {
	}

	public byte[] generarRideComprobante(FactCabecera lCabeceraFactura, String lRutaJasper, String lFormato)
			throws Throwable {
		return exportarPDF(obtieneDataSourceReporte(lCabeceraFactura, lRutaJasper, lFormato),
				lCabeceraFactura.getlEmpresa().getImagenReferencia(), lRutaJasper, lCabeceraFactura.getClaveAcceso(),
				lCabeceraFactura.getlEmpresa().getlIdentificacion());
	}

	private byte[] exportarPDF(List<RideFacturaCab> dataSource, byte[] rutaLogo, String rutaJasper, String claveAcceso,
			String lruc) throws JRException {

		File reporteRide = new File(rutaJasper);
		Map<String, Object> parametros = new HashMap<>();

		String lRutaLogo = verificarLogo(rutaLogo, lruc);

		if (lRutaLogo != null)
			parametros.put("RUTA_LOGO", lRutaLogo);
		parametros.put("SUBREPORT_DIR", reporteRide.getParent().concat(File.separator));
		parametros.put("REPORT_LOCALE", Locale.US);

		JasperReport reporte = (JasperReport) JRLoader.loadObject(reporteRide);
		JasperPrint print = JasperFillManager.fillReport(reporte, parametros,
				new JRBeanCollectionDataSource(dataSource));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
		exporter.exportReport();
		byte[] pdfByte = baos.toByteArray();
		return pdfByte;
	}

	private String verificarLogo(byte[] rutaLogo, String nombreImagen) {
		String lruta = null;
		try {
			lruta = "C:/app_new/logos_new/" + nombreImagen + ".jpg";

			File file = new File(lruta);

			if (!file.exists()) {
				OutputStream os = new FileOutputStream(file);
				os.write(rutaLogo);
				os.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lruta;
	}

	private List<RideFacturaCab> obtieneDataSourceReporte(FactCabecera lCabeceraFactura, String lRutaJasper,
			String lFormato) throws Throwable {
		List<RideFacturaCab> l_fac = new ArrayList<>();
		RideFacturaCab rideCab = new RideFacturaCab();
		rideCab.setAmbiente(lCabeceraFactura.getAmbiente());
		rideCab.setClaveacceso(lCabeceraFactura.getClaveAcceso());
		rideCab.setContribuyenteespecial(lCabeceraFactura.getNoResolucion());
		rideCab.setSignoMoneda(SIGNO_DOLAR);
		rideCab.setPresentarDatosFacturaExportacion(false);

		RideInfoAdicional direccionCliente = new RideInfoAdicional();

		direccionCliente.setCampo1("Dirección Cliente");
		direccionCliente.setCampo2(lCabeceraFactura.getlCliente().getDireccionComprador());
		rideCab.addInfoAdicional(direccionCliente);

		RideInfoAdicional direccionEmailCliente = new RideInfoAdicional();
		direccionEmailCliente.setCampo1("Email Cliente");
		direccionEmailCliente.setCampo2(lCabeceraFactura.getlCliente().getEmailsComprador());
		rideCab.addInfoAdicional(direccionEmailCliente);

		RideInfoAdicional usuarioGenerador = new RideInfoAdicional();
		usuarioGenerador.setCampo1("Usuario");
		usuarioGenerador.setCampo2(lCabeceraFactura.getAuditoria());
		rideCab.addInfoAdicional(usuarioGenerador);

		String cadenaNumerica = String.valueOf(lCabeceraFactura.getImporteTotal());
		String tmpCadenaNumerica = cadenaNumerica;
		tmpCadenaNumerica = tmpCadenaNumerica.replace(",", ".");
		String decimales = null;
		try {
			tmpCadenaNumerica = tmpCadenaNumerica.substring(0, tmpCadenaNumerica.indexOf("."));
			decimales = cadenaNumerica.substring(cadenaNumerica.indexOf(".") + 1);
		} catch (Exception e) {
			decimales = "00";
		}
		if (decimales.length() == 1) {
			decimales = decimales + "0";
		} else if (decimales.length() > 2) {
			decimales = decimales.substring(0, 1);
		}
		int tmpInt = Integer.parseInt(tmpCadenaNumerica);
		cadenaNumerica = ConvertirNumerosLetras.convertirLetras(tmpInt);
		if (cadenaNumerica != null) {
			cadenaNumerica = cadenaNumerica.toUpperCase() + " DOLARES " + decimales + "/100 US Dolares dólares.";
		}
		rideCab.setCantidadLetras(cadenaNumerica);
		
		
		RideInfoAdicional celular = new RideInfoAdicional();
		celular.setCampo1("Teléfono:");
		celular.setCampo2(lCabeceraFactura.getlCliente().getCelularComprador());
		rideCab.addInfoAdicional(celular);
		
		RideInfoAdicional totalLetras = new RideInfoAdicional();
		totalLetras.setCampo1("SON:");
		totalLetras.setCampo2(cadenaNumerica);
		rideCab.addInfoAdicional(totalLetras);

		rideCab.setDirmatriz(lCabeceraFactura.getlEmpresa().getlDireccion());
		rideCab.setDirsucursal(lCabeceraFactura.getDirEstablecimiento());
		rideCab.setNoautorizacion(lCabeceraFactura.getClaveAcceso());
		rideCab.setFechaautorizacion(null);
		rideCab.setFechaemision(lFormatoFecha.format(lCabeceraFactura.getFechaEmision()));
		rideCab.setIdentificacioncliente(lCabeceraFactura.getlCliente().getDireccionComprador());
		rideCab.setNofactura(lCabeceraFactura.getEstab() + "-" + lCabeceraFactura.getPtoEmi() + "-"
				+ lCabeceraFactura.getSecuencial());
		rideCab.setNombrecliente(lCabeceraFactura.getlCliente().getRazonSocialComprador());
		try

		{
			rideCab.setObligadollevarcontabilidad(lCabeceraFactura.getObligadoContabilidad());
		} catch (Exception e) {
			e.printStackTrace();
		}
		rideCab.setRazonsocial(lCabeceraFactura.getlEmpresa().getlRazonSocial());
		rideCab.setNombrecomercial(lCabeceraFactura.getlEmpresa().getlRazonSocial());
		rideCab.setRuc(lCabeceraFactura.getlEmpresa().getlIdentificacion());
		rideCab.setSubtotalsinimpuestos(new BigDecimal(lCabeceraFactura.getTotalSinImpuestos()));
		rideCab.setTipoemision(lCabeceraFactura.getTipoEmision());
		rideCab.setTotal(new BigDecimal(lCabeceraFactura.getImporteTotal()));
		rideCab.setPropina(new BigDecimal(0));
		rideCab.setGuiaremision("");
		rideCab.setDireccionComprador(lCabeceraFactura.getlCliente().getDireccionComprador());

		for (FactDetalle det : lCabeceraFactura.getlListaDetalles()) {

			HashMap<String, Object> lParametro = new HashMap<>();
			lParametro.put("idDetalle", det.getId());

			det.setlListaImpuestos(lServicioDetalleImp.ejecutarQueryNativoObjeto(
					"select * from ONIX_DETALLE_IMPUESTOS where ID_DETALLE = :idDetalle", lParametro,
					FactDetalleImpuesto.class));

			RideFacturaDet rideDet = new RideFacturaDet();
			rideDet.setCodigoprincipal(det.getCodigoPrincipal());
			rideDet.setCodigoauxiliar(det.getCodigoAuxiliar());

			rideDet.setNomadicional1("DET");
			rideDet.setValadicional1(det.getDetallesAdicionales());

			rideDet.setCantidad(new BigDecimal(det.getCantidad()));
			rideDet.setDescripcion(det.getDescripcion());
			rideDet.setDescuento(new BigDecimal(det.getDescuento()));
			rideDet.setPreciototal(new BigDecimal(det.getPrecioTotalSinImpuesto()));
			rideDet.setPreciounitario(new BigDecimal(det.getPrecioUnitario()));
			try {
				rideDet.setUnidadMedida("");
			} catch (Exception e) {
				e.printStackTrace();
			}
			BigDecimal impto = new BigDecimal(0);

			for (FactDetalleImpuesto imp : det.getlListaImpuestos()) {
				impto = impto.add(new BigDecimal(imp.getValor()));
			}
			rideDet.setValorimpuesto(impto);
			rideCab.addDetalle(rideDet);
		}

		List<RideFormaPago> formPago = new ArrayList<RideFormaPago>();
		for (FactFormaPagosFactura pag : lCabeceraFactura.getlFormaPagos()) {
			RideFormaPago fPago = new RideFormaPago();
			fPago.setFormaPago(pag.getlFormaPago().getDescripcion());
			fPago.setValor(new BigDecimal(pag.getlTotal()));
			fPago.setPlazo(new BigDecimal(pag.getPlazo()));
			fPago.setTiempo(pag.getUnidadTiempo());
			formPago.add(fPago);
		}

		rideCab.setlFormaPago(formPago);

		BigDecimal comp = new BigDecimal(0);
		boolean tieneCompensacion = false;

		rideCab.setTieneCompensacion(tieneCompensacion);
		rideCab.setCompensacion(comp);

		getTotales(lCabeceraFactura, rideCab);
		l_fac.add(rideCab);
		return l_fac;
	}

	private void getTotales(FactCabecera factura, RideFacturaCab cab) {
		BigDecimal totalIva12 = new BigDecimal(0.0D);
		BigDecimal totalIva0 = new BigDecimal(0.0D);
		BigDecimal iva12 = new BigDecimal(0.0D);
		BigDecimal totalICE = new BigDecimal(0.0D);
		BigDecimal totalSinImpuesto = new BigDecimal(0.0D);
		BigDecimal descuento = new BigDecimal(0);
		BigDecimal totalFob = new BigDecimal(0.0D);
		BigDecimal totalCif = new BigDecimal(0.0D);
		BigDecimal totalCfr = new BigDecimal(0.0D);
		BigDecimal totalIrbpnr = new BigDecimal(0.0D);
		String tarifa = "";

		HashMap<String, Object> lParam = new HashMap<>();
		lParam.put("idFactura", factura.getId());

		factura.setlListaCabeceraImpuesto(lServicioImpuestoFact.ejecutarQueryNativoObjeto(
				"select * from ONIX_CAB_FACTURA_IMPUESTO where ID_FACTURA = :idFactura ", lParam,
				FactCabeceraImpuesto.class));

		for (FactCabeceraImpuesto ti : factura.getlListaCabeceraImpuesto()) {

			if ((INombresParametros.TIPOIMPUESTO_IVA.equals(ti.getCodigo()))
					&& (INombresParametros.TIPOIMPUESTO_IVA_VENTA0.equals(ti.getCodigoPorcentaje()))) {
				totalIva0 = totalIva0.add(new BigDecimal(ti.getBaseImponible()));
			}
			if ((INombresParametros.TIPOIMPUESTO_IVA.equals(ti.getCodigo()))
					&& (INombresParametros.TIPOIMPUESTO_IVA_NOSUJETO.equals(ti.getCodigoPorcentaje()))) {
				totalSinImpuesto = totalSinImpuesto.add(new BigDecimal(ti.getBaseImponible()));
			}
			if (INombresParametros.TIPOIMPUESTO_ICE.equals(ti.getCodigo())) {
				totalICE = totalICE.add(new BigDecimal(ti.getValor()));
			}
			if (INombresParametros.TIPOIMPUESTO_IRBPNR.equals(ti.getCodigo())) {
				totalIrbpnr = totalIrbpnr.add(new BigDecimal(ti.getValor()));
			}

			if ((INombresParametros.TIPOIMPUESTO_IVA.equals(ti.getCodigo()))
					&& !(INombresParametros.TIPOIMPUESTO_IVA_VENTA0.equals(ti.getCodigoPorcentaje())
							|| INombresParametros.TIPOIMPUESTO_IVA_NOSUJETO.equals(ti.getCodigoPorcentaje()))) {
				totalIva12 = totalIva12.add(new BigDecimal(ti.getBaseImponible()));
				iva12 = iva12.add(new BigDecimal(ti.getValor()));
				tarifa = ti.getTarifa().toString();

			}

		}

		for (FactDetalle detalle : factura.getlListaDetalles()) {
			descuento = descuento.add(new BigDecimal(detalle.getDescuento()));
		}

		cab.setIva(iva12);
		cab.setSubtotal0(totalIva0);
		cab.setSubtotal12(totalIva12);
		cab.setIce(totalICE);
		cab.setSubtotalnoiva(totalSinImpuesto);
		cab.setDescuento(descuento);
		cab.setTotalCfr(totalCfr);
		cab.setTotalCif(totalCif);
		cab.setTotalFob(totalFob);
		cab.setIrbpnr(totalIrbpnr);
		cab.setTarifa(tarifa == null ? "" : tarifa);
	}

}
