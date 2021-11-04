package preparedST;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Lapiz;
import utils.GestorBBDD;
import utils.GestorLapices;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner entrada = new Scanner(System.in);
		Lapiz lapizInsertar;
		Lapiz lapizModificar;
		Lapiz leerInsertado;
		int idInsertar, numeroInsertar, idBorrar, idModificar, nuevoNumero;
		String marcaInsertar, nuevaMarca;
		GestorBBDD gestorBBDD = new GestorBBDD("oficina", "root", "");
		GestorLapices gl = new GestorLapices(gestorBBDD);
		
		try {
			System.out.println("Introduzca id del lapiz a modificar: ");
			idModificar = entrada.nextInt();
			System.out.println("Introduzca nueva marca para el lapiz: ");
			nuevaMarca = entrada.next();
			entrada.nextLine();
			System.out.println("Introduzca nuevo numero para el lapiz: ");
			nuevoNumero = entrada.nextInt();
			
			lapizModificar = new Lapiz(idModificar, nuevoNumero, nuevaMarca);
			
			gl.modificarLapiz(lapizModificar);
		}
		catch(Exception ex) {
			System.out.println("Ha ocurrido un error");
		}
		finally {
			gestorBBDD.getConnection().close();
		}
		
		try {
			System.out.println("Introduzca id del lapiz a borrar: ");
			idBorrar = entrada.nextInt();
			gl.borrarLapiz(idBorrar);

		}
		catch(Exception ex) {
			System.out.println("Ha ocurrido un error");
		}
		finally {
			gestorBBDD.getConnection().close();
		}
		
		try {
			ArrayList<Lapiz> lapicesComprobar = new ArrayList<>();
			lapicesComprobar = (ArrayList<Lapiz>) gl.leerTodos();
			System.out.println("Introduzca id del lapiz a insertar: ");
			idInsertar = entrada.nextInt();
			System.out.println("Introduzca numero del lapiz a insertar: ");
			numeroInsertar = entrada.nextInt();
			System.out.println("Introduzca marca del lapiz a insertar: ");
			marcaInsertar = entrada.next();
			
			lapizInsertar = new Lapiz(idInsertar, numeroInsertar, marcaInsertar);
			gl.saveLapiz(lapizInsertar, lapicesComprobar);
			
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
