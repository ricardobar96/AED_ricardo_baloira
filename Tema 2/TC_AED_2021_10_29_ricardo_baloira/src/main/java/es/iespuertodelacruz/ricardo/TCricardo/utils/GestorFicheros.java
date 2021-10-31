package es.iespuertodelacruz.ricardo.TCricardo.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorFicheros {
public File file;
	
	public GestorFicheros(String file) {
		this.file = new File(file);
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void escribirFichero(String texto) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file);
			writer.write(texto);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			writer.close();
		}
	}
	

	public String leerFichero() throws IOException {
		BufferedReader reader = null;
		String texto = "";
		try {
			reader = new BufferedReader(new FileReader(file));		
			String line = reader.readLine();
			while(line!=null) {
				texto+=line;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			reader.close();
		}
		return texto;		
	}

}

