package insertLapices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Lapiz;
import utils.GestorBBDD;
import utils.GestorLapices;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner entrada = new Scanner(System.in);
		Lapiz l;
		Lapiz leerInsertado;
		int idInsertar, numeroInsertar;
		String marcaInsertar;
		GestorBBDD gestorBBDD = new GestorBBDD("oficina", "root", "");
		GestorLapices gl = new GestorLapices(gestorBBDD);
		
		try {
			ArrayList<Lapiz> lapicesComprobar = new ArrayList<>();
			lapicesComprobar = (ArrayList<Lapiz>) gl.leerTodos();
			System.out.println("Introduzca id del lapiz a insertar: ");
			idInsertar = entrada.nextInt();
			System.out.println("Introduzca numero del lapiz a insertar: ");
			numeroInsertar = entrada.nextInt();
			System.out.println("Introduzca marca del lapiz a insertar: ");
			marcaInsertar = entrada.next();
			
			l = new Lapiz(idInsertar, numeroInsertar, marcaInsertar);
			gl.saveLapiz(l, lapicesComprobar);
			
		}
		catch(SQLException e) {
			System.out.println("Error al insertar datos en la tabla");
		}
		catch(Exception ex) {
			System.out.println("Ha ocurrido un error");
		}
		finally {
			gestorBBDD.getConnection().close();
		}
		
		try {
			System.out.println("\nTabla final: ");
			ArrayList<Lapiz> lapices = new ArrayList<>();
			lapices = (ArrayList<Lapiz>) gl.leerTodos();
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
