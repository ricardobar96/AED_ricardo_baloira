package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
	
	public void saveLapiz(Lapiz l, ArrayList<Lapiz> lapicesComprobar) throws SQLException{		
		Lapiz resultado = null;
		Connection cn = gestorBBDD.getConnection();
		PreparedStatement ps = cn.prepareStatement("INSERT INTO lapices (idlapiz, numero, marca) VALUES (?, ?, ?)");
		if(lapicesComprobar.contains(l.getIdLapiz())) {
			System.out.println("El id ya existe en la tabla");
		}
		else {
			ps.setInt(1, l.getIdLapiz());
			ps.setInt(2, l.getNumero());
			ps.setString(3, l.getMarca());
			ps.executeUpdate();
		}
		
		ps.close();
		}
	
	public List<Lapiz>leerTodos() throws SQLException{
		ArrayList<Lapiz> lapices = new ArrayList<>();
		Connection cn = gestorBBDD.getConnection();
		Statement s = cn.createStatement();  
        String sql = "select idlapiz, numero, marca from lapices";
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