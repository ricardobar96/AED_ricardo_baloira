package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Lapiz;

public class GestorLapices {
GestorBBDD gestorBBDD;
	
	public GestorLapices(String ddbb, String user, String password) {
		this.gestorBBDD = new GestorBBDD(ddbb, user, password);
	}
	
	public GestorLapices(GestorBBDD gestorBBDD) {
		this.gestorBBDD = gestorBBDD;
	}
	
	public List<Lapiz>obtenerLapicesPorMarca(String marcaBuscar) throws SQLException{
		ArrayList<Lapiz> lapices = new ArrayList<>();
		Connection cn = gestorBBDD.getConnection();
		Statement s = cn.createStatement();  
        String sql = "select idlapiz, marca, numero from lapices WHERE marca = '" + marcaBuscar + "'";
        ResultSet rs = s.executeQuery(sql);
        
        while(rs.next()){
        	 int id = rs.getInt("idlapiz");
        	 String marca = rs.getString("marca");
        	 int numero = rs.getInt("numero");
        	 lapices.add(new Lapiz(id, numero, marca));
        }
        
        s.close();
		return lapices;
	}
}