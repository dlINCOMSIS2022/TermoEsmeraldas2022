package com.stp.plataforma.vista.aplicacion.mantenimiento;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.TransferEvent;
import org.primefaces.model.DualListModel;

import com.stp.plataforma.dominio.aplicacion.OmsAccesosDirecto;
import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.eao.aplicacion.OmsAccesosDirectoEAO;
import com.stp.plataforma.servicio.aplicacion.mantenimiento.ServicioMantenedorAccesoDirecto;

import librerias.exceptions.ErrorServicioNegocio;
import librerias.exceptions.ErrorValidacionGeneral;
import librerias.vista.JsfUtil;
import librerias.vista.anotaciones.ITestUsuarioSession;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.PostConstructListener;
import librerias.vista.beans.oyentes.PostTransaccionListener;
import librerias.vista.interfaces.IUsuarioSession;

// TODO: Auto-generated Javadoc
/**
 * The Class BeanAccesosDirectos.
 */
@ManagedBean
@ViewScoped
public class BeanAccesosDirectos
		extends BeanMantenedorGenerico<ServicioMantenedorAccesoDirecto, Long, OmsAccesosDirecto, OmsAccesosDirectoEAO> {

	/** The lista seleccion acceso. */
	private DualListModel<String> listaSeleccionAcceso;

	/** The usuario session. */
	@Inject
	@ITestUsuarioSession
	private IUsuarioSession<OmsUsuario> usuarioSession;

	/** The l lista accesos activos. */
	private List<OmsAccesosDirecto> lListaAccesosActivos;

	/** The l servicio mantenedor. */
	@EJB
	private ServicioMantenedorAccesoDirecto lServicioMantenedor;

	/**
	 * Instantiates a new bean accesos directos.
	 */
	public BeanAccesosDirectos() {
		super(OmsAccesosDirecto.class);
		listaSeleccionAcceso = new DualListModel<>();
		addPostTransaccion(new PostTransaccionListener() {

			@Override
			public void metodoPostTransaccion() {
				setListaEntidades(lServicioMantenedor.obtenerAccesosDirectosUsuario(usuarioSession.getUsuarioSession().getId()));

			}
		});

		addPostConstructuListener(new PostConstructListener() {
			public void metodoPostConstruct() {
				lListaAccesosActivos = lServicioMantenedor.obtenerAccesosDirectosUsuario(usuarioSession.getUsuarioSession().getId());
				cargatListaAccesos();
			}
		});

	}

	/**
	 * Cargat lista accesos.
	 */
	private void cargatListaAccesos() {
		listaSeleccionAcceso = new DualListModel<>(
				lServicioMantenedor.obtenerAccesosPorAsignar(usuarioSession.getUsuarioSession().getId()),
				lServicioMantenedor.obtenerAccesosAsignadosActivos(usuarioSession.getUsuarioSession().getId()));
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#cargarListaEtiquetas()
	 */
	@Override
	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Accesos Directoss");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Accesos Directoss registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(),
				"Actualización de Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Accesos Directos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Accesos Directoss registrados");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);

	}

	/* (non-Javadoc)
	 * @see com.onix.modulo.librerias.vista.beans.BeanMantenedorGenerico#getServicioMantenedor()
	 */
	@Override
	protected ServicioMantenedorAccesoDirecto getServicioMantenedor() {
		return lServicioMantenedor;
	}

	/**
	 * Gets the lista seleccion acceso.
	 *
	 * @return the lista seleccion acceso
	 */
	public DualListModel<String> getListaSeleccionAcceso() {
		return listaSeleccionAcceso;
	}

	/**
	 * Sets the lista seleccion acceso.
	 *
	 * @param listaSeleccionAcceso the new lista seleccion acceso
	 */
	public void setListaSeleccionAcceso(DualListModel<String> listaSeleccionAcceso) {
		this.listaSeleccionAcceso = listaSeleccionAcceso;

	}
	
	/**
	 * Cargar lista accesos directos.
	 */
	public void cargarListaAccesosDirectos()
	{
		lListaAccesosActivos = lServicioMantenedor.obtenerAccesosDirectosUsuario(usuarioSession.getUsuarioSession().getId());
	}
	

	/**
	 * Control transferencia.
	 *
	 * @param pEvento the evento
	 */
	public void controlTransferencia(TransferEvent pEvento) {

		Long referencia = obtenerObjetoSesion(JsfUtil.REFERENCIA_SESION);
		List<String> lAccesosSeleccionados = (List<String>) pEvento.getItems();
		try {
			if (pEvento.isAdd())

				lServicioMantenedor.asigarAcceso(lAccesosSeleccionados, usuarioSession.getUsuarioSession().getId(),
						referencia, usuarioSession.getUsuarioSession().getUsuario());

			else {
				if (pEvento.isRemove())
					lServicioMantenedor.inactivarAcceso(lAccesosSeleccionados,
							usuarioSession.getUsuarioSession().getId(),
							usuarioSession.getUsuarioSession().getUsuario());

			}
			cargatListaAccesos();
			lListaAccesosActivos = lServicioMantenedor.obtenerAccesosDirectosUsuario(usuarioSession.getUsuarioSession().getId());
		} catch (ErrorServicioNegocio e) {
			e.printStackTrace();
			addError(e.getMessage());

		} catch (ErrorValidacionGeneral e) {
			e.printStackTrace();
			addError(e.getMessage());
		}
	}

	/**
	 * Gets the l lista accesos activos.
	 *
	 * @return the l lista accesos activos
	 */
	public List<OmsAccesosDirecto> getlListaAccesosActivos() {
		return lListaAccesosActivos;
	}

	/**
	 * Sets the l lista accesos activos.
	 *
	 * @param lListaAccesosActivos the new l lista accesos activos
	 */
	public void setlListaAccesosActivos(List<OmsAccesosDirecto> lListaAccesosActivos) {
		this.lListaAccesosActivos = lListaAccesosActivos;
	}

}
