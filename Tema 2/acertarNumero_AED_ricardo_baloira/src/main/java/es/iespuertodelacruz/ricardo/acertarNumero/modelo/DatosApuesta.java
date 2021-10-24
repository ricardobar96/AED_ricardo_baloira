package es.iespuertodelacruz.ricardo.acertarNumero.modelo;

import java.util.Date;

public class DatosApuesta {
	int numero;
	Date hora;
	
	public DatosApuesta(int numero, Date hora) {
		super();
		this.numero = numero;
		this.hora = hora;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}
	
	
}
