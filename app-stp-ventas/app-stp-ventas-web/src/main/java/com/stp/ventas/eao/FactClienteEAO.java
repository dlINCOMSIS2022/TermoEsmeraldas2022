package com.stp.ventas.eao;

import javax.ejb.Stateless;

import com.stp.plataforma.eao.OnixEAO;
import com.stp.ventas.dominio.FactCliente;

@Stateless
public class FactClienteEAO extends OnixEAO<FactCliente, Long>  {

}
