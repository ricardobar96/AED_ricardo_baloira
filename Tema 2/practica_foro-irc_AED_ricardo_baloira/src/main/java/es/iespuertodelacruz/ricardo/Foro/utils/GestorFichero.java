package es.iespuertodelacruz.ricardo.Foro.utils;

import java.io.File;

/**
 * @author ricardo baloira
 *
 */
public class GestorFichero {
	public File file = new File("foro.txt");

	public GestorFichero(File file) {
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}
