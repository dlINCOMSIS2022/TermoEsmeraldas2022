package com.stp.ventas;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stp.ventas.ride.GenerarComprobantePDF;
import com.stp.ventas.vista.BeanControlFactura;

/**
 * Servlet implementation class ComprobanteRideFactura
 */
@WebServlet("/ComprobanteRideFactura")
public class ComprobanteRideFactura extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private GenerarComprobantePDF lGenerarPDF;

	@Inject
	private BeanControlFactura lControlFactura;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ComprobanteRideFactura() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		byte[] pdfByte = lGenerarPDF.generarComprobantesPDF(lControlFactura.getlCabeceraFactura());
		response.setContentType("application/pdf");
		response.setContentLength(pdfByte.length);
		response.getOutputStream().write(pdfByte);

	}

}
