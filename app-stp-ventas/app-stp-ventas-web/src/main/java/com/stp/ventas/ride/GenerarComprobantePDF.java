package com.stp.ventas.ride;

import javax.inject.Inject;
import javax.inject.Named;

import com.stp.ventas.dominio.FactCabecera;

@Named
public class GenerarComprobantePDF {
	private static final String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	private static final String FACTURA_JASPER = "factura.jasper";
	private static final String SEPARADOR = "/";
	
	@Inject
	private GeneradorRideFacturas lGenerador;

	public byte[] generarComprobantesPDF(FactCabecera lCabeceraFactura) {
		try {
			String rutaJasper = "";
			byte[] archivoGenerado = null;
			String lRutaJasper = "C:/app_new/reporte_new";
			rutaJasper = lRutaJasper + SEPARADOR + FACTURA_JASPER;
			archivoGenerado = lGenerador.generarRideComprobante(lCabeceraFactura, rutaJasper, DD_MM_YYYY_HH_MM_SS);

			return archivoGenerado;
		} catch (Throwable e) {
			e.printStackTrace();
			return new byte[0];
		}
	}

}
