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
	
	public void borrarLapiz(int idBorrar) throws SQLException{
		Connection cn = gestorBBDD.getConnection();
		Statement s = cn.createStatement();  
        String sql = "DELETE from lapices WHERE idLapiz = " + idBorrar;
        s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = s.getGeneratedKeys();
        
        int count = s.getUpdateCount();
        System.out.println("Numero de filas borradas: "+ count);
        
        s.close();
	}
	
	public Lapiz modificarLapiz(Lapiz l) throws SQLException{
		Lapiz resultado = null;
		Connection cn = gestorBBDD.getConnection();
		PreparedStatement ps = cn.prepareStatement("UPDATE lapices SET marca = ?, numero = ? WHERE idLapiz = ?");
		
		ps.setString(1, l.getMarca());
		ps.setInt(2, l.getNumero());
		ps.setInt(3, l.getIdLapiz());
        ps.executeUpdate();
       
        int count = ps.getUpdateCount();
        System.out.println("Numero de filas actualizadas: "+ count);
        resultado = new Lapiz(l.getIdLapiz(), l.getNumero(), l.getMarca());
        
        ps.close();
        return resultado;
	}
	
	public List<Lapiz>leerTodos() throws SQLException{
		ArrayList<Lapiz> lapices = new ArrayList<>();
		Connection cn = gestorBBDD.getConnection();
		Statement s = cn.createStatement();  
        String sql = "select * from lapices";
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