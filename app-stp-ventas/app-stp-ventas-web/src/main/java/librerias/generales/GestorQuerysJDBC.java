package librerias.generales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GestorQuerysJDBC 
{
	
	private static String QUERY = "select a.query from onix_querys a where upper(a.descripcion) = upper(?)";
	
	public static String obtenerQueryNombre(String nombre, Connection conexion) throws Throwable
	{
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
//		BufferedReader br = null;
		StringBuffer queryFinal = new StringBuffer("");
		try {
			sentencia = conexion.prepareStatement(QUERY);
			sentencia.setString(1, nombre);
			resultado = sentencia.executeQuery();
			String aux;
			
			if (resultado.next()) 
			{
				
				queryFinal.append(resultado.getString("query"));
//				br  = new BufferedReader(resultado.getClob("query").
//					      getCharacterStream());
//				
//				while ((aux=br.readLine())!=null) {
//					queryFinal.append(aux);
//				    }
				
			}
			return queryFinal.toString();
		} finally {
			if (resultado != null) {
				resultado.close();
			}
			if (sentencia != null) {
				sentencia.close();
			}	
			
		}
		
		
	}
}
