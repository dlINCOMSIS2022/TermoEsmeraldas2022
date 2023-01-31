package com.stp.ventas.vista;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.stp.ventas.dominio.FactEmpresa;
import com.stp.ventas.dominio.PosTipoProducto;
import com.stp.ventas.eao.PosTipoProductoEAO;
import com.stp.ventas.servicios.ServicioMantenedorFactEmpresa;
import com.stp.ventas.servicios.ServicioMantenedorTipoProducto;

import librerias.vista.JsfUtil;
import librerias.vista.beans.BeanMantenedorGenerico;
import librerias.vista.beans.NombresEtiquetas;
import librerias.vista.beans.oyentes.ControlListaEntidadesPersonalizada;
import librerias.vista.beans.oyentes.PostConstructListener;
import librerias.vista.beans.oyentes.PostSeleccionEntidadListener;
import librerias.vista.beans.oyentes.PostTransaccionListener;
import librerias.vista.beans.oyentes.PreTransaccionListener;
import librerias.vista.exceptions.ErrorAccionesPreTransaccion;

@ManagedBean
@ViewScoped
public class BeanMantenedorTipoProducto
		extends BeanMantenedorGenerico<ServicioMantenedorTipoProducto, Long, PosTipoProducto, PosTipoProductoEAO> {
	@EJB
	private ServicioMantenedorTipoProducto servicioMantenedor;

	@Inject
	private ServicioMantenedorFactEmpresa lServicioUsuarioEmpresa;

	private List<FactEmpresa> lListaEmpresaCombo;

	private Long lEmpresaID;

	public BeanMantenedorTipoProducto() {
		super(PosTipoProducto.class);

		addPostTransaccion(new PostTransaccionListener() {
			@Override
			public void metodoPostTransaccion() {
				lEmpresaID = 0L;
			}
		});

		addPreTransaccionServicioListener(new PreTransaccionListener() {

			@Override
			public void accionPreTransaccion() throws ErrorAccionesPreTransaccion {
				FactEmpresa lEmpresa = new FactEmpresa();
				lEmpresa.setId(lEmpresaID);
				entidadRegistrar.setlEmpresa(lEmpresa);

			}
		});

		addPostConstructuListener(new PostConstructListener() {

			@Override
			public void metodoPostConstruct() {
				lEmpresaID = 0L;
				lListaEmpresaCombo = obtenerEmpresaUsuario();
			}
		});

		addControlListaEntidadesPersonalizada(new ControlListaEntidadesPersonalizada() {

			@Override
			public void cargarListaEntidades() {
				HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
				lParametrosEmpresa.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
				String lQuery = "select *  from POS_TIPO_PRODUCTO where ID_EMPRESA  in ( "
						+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b "
						+ "where a.usuario = :usuario " + "and a.estado = 'A' " + "and b.id_usuario = a.id "
						+ "and b.estado = 'A') " + "and estado = 'A'";
				List<PosTipoProducto> lListaTipo = servicioMantenedor.ejecutarQueryNativoObjeto(lQuery,
						lParametrosEmpresa, PosTipoProducto.class);
				setListaEntidades(lListaTipo);
			}
		});

		addPostSeleccionRegistroListener(new PostSeleccionEntidadListener<PosTipoProducto, Long>() {

			@Override
			public void postSeleccionRegistro(PosTipoProducto pEntidadSeleccionada) {
				lEmpresaID = pEntidadSeleccionada.getlEmpresa().getId();

			}
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServicioMantenedorTipoProducto getServicioMantenedor() {
		// TODO Auto-generated method stub
		return servicioMantenedor;
	}

	protected void cargarListaEtiquetas() {
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TITULOPAGINA.toString(), "Mantenimiento Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.DESCRIPCIONPAGINA.toString(), "Mantenedor Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.AYUDAPAGINA.toString(), "Cree o edite Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TAB.toString(), "Datos  Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERATABLA.toString(), "Lista de Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERADIALOGO.toString(), "Actualización de Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.CABECERAPANELDIALOGO.toString(), "Datos Tipo Productos");
		this.listaEtiquetasPantalla.put(NombresEtiquetas.TABLAVACIA.toString(), JsfUtil.MENSAJE_INFO_SINRESULTADO);
	}

	private List<FactEmpresa> obtenerEmpresaUsuario() {
		HashMap<String, Object> lParametrosEmpresa = new HashMap<>();
		lParametrosEmpresa.put("usuario", JsfUtil.getUsuarioAutenticado().getName());
		String lQuery = "select * from onix_empresa where id in ( "
				+ "select b.id_empresa from onix_usuarios a, onix_usuario_empresa b " + "where a.usuario = :usuario "
				+ "and a.estado = 'A' " + "and b.id_usuario = a.id " + "and b.estado = 'A') " + "and estado = 'A'";
		List<FactEmpresa> lListaEmpresa = lServicioUsuarioEmpresa.ejecutarQueryNativoObjeto(lQuery, lParametrosEmpresa,
				FactEmpresa.class);
		return lListaEmpresa;
	}

	public List<FactEmpresa> getlListaEmpresaCombo() {
		return lListaEmpresaCombo;
	}

	public void setlListaEmpresaCombo(List<FactEmpresa> lListaEmpresaCombo) {
		this.lListaEmpresaCombo = lListaEmpresaCombo;
	}

	public Long getlEmpresaID() {
		return lEmpresaID;
	}

	public void setlEmpresaID(Long lEmpresaID) {
		this.lEmpresaID = lEmpresaID;
	}

}
