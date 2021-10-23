package es.iespuertodelacruz.ricardo.Foro.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author ricardo baloira
 *
 */
public class GestorFichero {
	public File file;
	
	public GestorFichero(String file) {
		this.file = new File(file);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void escribirFichero(ArrayList<String> mensajeforo) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			for(String mensaje: mensajeforo) {
				writer.write(mensaje + System.lineSeparator());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			writer.close();
		}
	}
	
	public ArrayList<String> leerFichero() throws IOException {
		BufferedReader reader = null;
		ArrayList<String> mensajeforo = new ArrayList<String>();
		try {
			reader = new BufferedReader(new FileReader(file));		
			String line = reader.readLine();
			while(line!=null) {
				mensajeforo.add(line);
				line = reader.readLine();
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			reader.close();
		}
		return mensajeforo;		
	}
}