package es.iespuertodelacruz.ricardo.gestionMatriculas.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class GestorConexionDDBB {
    String jdbcUrl;
    String usuario;
    String clave;
 
    public Connection getConnection() {
        
        Connection con=null;
        try {
        	con = basicDataSource.getConnection();
        } catch (SQLException ex) {
            System.exit(1);
        }
        return con;
    }
    BasicDataSource basicDataSource;
    
    public GestorConexionDDBB(String ddbb,String nombreUsuario, String password)
{
        jdbcUrl = "jdbc:mysql://localhost/"+ddbb+"?serverTimezone=UTC";
        usuario = nombreUsuario;
        clave = password;
        cargarDriverMysql();
        
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl(jdbcUrl);
        basicDataSource.setUsername(nombreUsuario);
        basicDataSource.setPassword(password);
    }
    
    private static  void cargarDriverMysql(){
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
      } catch(ClassNotFoundException ex) {
        System.err.println("no carga el driver");
        System.exit(1);
      }        
    }
}
