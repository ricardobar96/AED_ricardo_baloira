package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorBBDD {
	String ddbb;
	String usuario;
	String password;
	
	private static  void cargarDriverMysql(){
	      try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	      } catch(ClassNotFoundException ex) {
	        System.err.println("no carga el driver");
	        System.exit(1);
	      }        
	}  
	
	public GestorBBDD(String ddbb, String usuario, String password) {
		this.ddbb = ddbb;
		this.usuario = usuario;
		this.password = password;
		
		cargarDriverMysql();
	}
	
	public Connection getConnection() {
		Connection cn = null;
        try {
            cn = DriverManager.getConnection(
            		"jdbc:mysql://localhost/"+ddbb+"?serverTimezone=UTC",usuario,password);
        } catch (SQLException ex) { 
        	ex.printStackTrace();
        }    
		return cn;
	}
}
