package deleteUpdateLapices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Lapiz;
import utils.GestorBBDD;
import utils.GestorLapices;

public class Main {

	public static void main(String[] args) throws SQLException {
		Scanner entrada = new Scanner(System.in);
		int idBorrar, idModificar, nuevoNumero;
		String nuevaMarca;
		Lapiz resultado = new Lapiz();
		GestorBBDD gestorBBDD = new GestorBBDD("oficina", "root", "");
		GestorLapices gl = new GestorLapices(gestorBBDD);
		
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
			System.out.println("Introduzca id del lapiz a modificar: ");
			idModificar = entrada.nextInt();
			System.out.println("Introduzca nueva marca para el lapiz: ");
			nuevaMarca = entrada.next();
			entrada.nextLine();
			System.out.println("Introduzca nuevo numero para el lapiz: ");
			nuevoNumero = entrada.nextInt();

			gl.modificarLapiz(idModificar, nuevaMarca, nuevoNumero);
			System.out.println(resultado.getIdLapiz()+ " " +resultado.getMarca() + " " + resultado.getNumero());
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
