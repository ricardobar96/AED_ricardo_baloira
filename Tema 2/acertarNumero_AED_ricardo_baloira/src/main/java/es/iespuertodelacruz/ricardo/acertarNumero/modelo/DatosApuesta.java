package es.iespuertodelacruz.ricardo.acertarNumero.modelo;

import java.util.Date;

public class DatosApuesta {
	String nombre;
	int numero;
	Date hora;
	String comparacion;
	
	public DatosApuesta(String nombre, int numero, Date hora, String comparacion) {
		super();
		this.nombre = nombre;
		this.numero = numero;
		this.hora = hora;
		this.comparacion = comparacion;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public String getComparacion() {
		return comparacion;
	}

	public void setComparacion(String comparacion) {
		this.comparacion = comparacion;
	}
	
	
}
