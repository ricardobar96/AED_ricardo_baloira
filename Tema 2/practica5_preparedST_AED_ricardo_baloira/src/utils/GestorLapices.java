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
	
	public void modificarLapiz(Lapiz l) throws SQLException{
		Lapiz resultado = null;
		Connection cn = gestorBBDD.getConnection();
		PreparedStatement ps = cn.prepareStatement("UPDATE lapices SET marca = ?, numero = ? WHERE idLapiz = ?");
		
		ps.setString(1, l.getMarca());
		ps.setInt(2, l.getNumero());
		ps.setInt(3, l.getIdLapiz());
        ps.executeUpdate();
       
        int count = ps.getUpdateCount();
        System.out.println("Numero de filas actualizadas: "+ count);
        
        ps.close();
	}
	
	public List<Lapiz>leerTodos() throws SQLException{
		ArrayList<Lapiz> lapices = new ArrayList<>();
		Connection cn = gestorBBDD.getConnection();
		PreparedStatement ps = cn.prepareStatement("select idlapiz, numero, marca from lapices");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
       	 int id = rs.getInt("idlapiz");
       	 String marca = rs.getString("marca");
       	 int numero = rs.getInt("numero");
       	 lapices.add(new Lapiz(id, numero, marca));
       }
           
       ps.close();
       return lapices;
	}
	
	public void borrarLapiz(int idBorrar) throws SQLException{
		Connection cn = gestorBBDD.getConnection();
		PreparedStatement ps = cn.prepareStatement("DELETE from lapices WHERE idLapiz = ?");
		ps.setInt(1, idBorrar);
        ps.executeUpdate();
        
        int count = ps.getUpdateCount();
        System.out.println("Numero de filas borradas: "+ count);
        
        ps.close();
	}
}