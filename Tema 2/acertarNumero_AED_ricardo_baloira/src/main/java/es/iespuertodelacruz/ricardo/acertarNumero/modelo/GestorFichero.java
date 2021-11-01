package es.iespuertodelacruz.ricardo.acertarNumero.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

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

	public void escribirFichero(String ganador, int secreto, long segundos) throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter(file, true);
			writer.write(ganador + ";" + secreto + ";" + segundos + System.lineSeparator());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			writer.close();
		}
	}
}


