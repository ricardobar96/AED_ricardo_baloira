package marcaLapices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Lapiz;
import utils.GestorBBDD;
import utils.GestorLapices;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner entrada = new Scanner(System.in);
		String marcaBuscar;
		GestorBBDD gestorBBDD = new GestorBBDD("oficina", "root", "");
		GestorLapices gl = new GestorLapices(gestorBBDD);
		
		try {
			System.out.println("Introduzca marca a buscar: ");
			marcaBuscar = entrada.nextLine();
	
			ArrayList<Lapiz> lapices = new ArrayList<>();
			lapices = (ArrayList<Lapiz>) gl.leerTodos(marcaBuscar);
			for (Lapiz lapiz : lapices) {
				System.out.println(lapiz.getIdLapiz()+ " " +lapiz.getMarca() + " " + lapiz.getNumero());
			}
		}
		catch(Exception ex) {
			System.out.println("Ha ocurrido un error");
		}
		finally {
			gestorBBDD.getConnection().close();
		}
	}

}
