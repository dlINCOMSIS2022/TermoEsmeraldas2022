package com.stp.ventas.servicios;

import java.util.HashMap;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.stp.plataforma.dominio.aplicacion.OmsUsuario;
import com.stp.plataforma.servicio.ServicioMantenedorControlAuditoria;
import com.stp.ventas.dominio.FactEmpresa;
import com.stp.ventas.dominio.FactUsuarioEmpresa;
import com.stp.ventas.eao.FactUsuarioEmpresaEAO;

@Stateless
public class ServicioMantenedorFactUsurioEmpresa
		extends ServicioMantenedorControlAuditoria<FactUsuarioEmpresaEAO, FactUsuarioEmpresa, Long> {

	@EJB
	private FactUsuarioEmpresaEAO lFacturaUsuarioEmpresaEAO;

	@Override
	protected void cargarConfiguracionServicio() {
		System.out.println("SIN NADA QUE CONFIGURAR");
	}

	@Override
	protected FactUsuarioEmpresaEAO getCrud() {
		return lFacturaUsuarioEmpresaEAO;
	}

	
	public List<String> obtenerUsuariosAsignados(Long id) {

		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idEmpresa", id);

		return lFacturaUsuarioEmpresaEAO.ejecutarNativeQueryList("select a.usuario from " + "ONIX_USUARIOS a, "
				+ "ONIX_USUARIO_EMPRESA b " + "where b.ID_EMPRESA = :idEmpresa " + "and b.estado = 'A' "
				+ "and a.id = b.ID_USUARIO " + "and a.estado = 'A'", lParametros, String.class);
	}

	public List<String> obtenerUsuariosPorAsignar(Long id) {
		HashMap<String, Object> lParametros = new HashMap<>();
		lParametros.put("idEmpresa", id);

		return lFacturaUsuarioEmpresaEAO.ejecutarNativeQueryList("select c.usuario from " + "ONIX_USUARIOS c "
				+ "where c.id not in ( " + "select a.id from " + "ONIX_USUARIOS a, " + "ONIX_USUARIO_EMPRESA b "
				+ "where b.ID_EMPRESA = :idEmpresa " + "and b.estado = 'A' " + "and a.id = b.ID_USUARIO "
				+ "and a.estado = 'A' " + ") " + "and c.estado = 'A'"

				, lParametros, String.class);
	}
	
	public void eliminarAsignacion(OmsUsuario lUsuario, FactEmpresa lEmpresa)
	{
		lFacturaUsuarioEmpresaEAO.getAdminEntidad().createNativeQuery(
				"delete from onix_usuario_empresa where id_empresa = :idEmpresa and id_usuario = :idUsuario"
				).setParameter("idEmpresa", lEmpresa.getId()).setParameter("idUsuario", lUsuario.getId()).executeUpdate();
	}
	
	
	
	
	
	
	

}
