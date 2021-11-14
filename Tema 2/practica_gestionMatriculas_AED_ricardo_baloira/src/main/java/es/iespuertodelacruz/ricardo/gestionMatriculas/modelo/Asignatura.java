package es.iespuertodelacruz.ricardo.gestionMatriculas.modelo;

public class Asignatura {
	int idasignatura;
	String nombre;
	String curso;
	
	
	public Asignatura(int idasignatura, String nombre, String curso) {
		super();
		this.idasignatura = idasignatura;
		this.nombre = nombre;
		this.curso = curso;
	}
	
	public Asignatura(String nombre, String curso) {
		super();
		this.nombre = nombre;
		this.curso = curso;
	}

	public int getIdasignatura() {
		return idasignatura;
	}
	public void setIdasignatura(int idasignatura) {
		this.idasignatura = idasignatura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Asignatura [idasignatura=" + idasignatura + ", nombre=" + nombre + ", curso=" + curso + "]";
	}
}
