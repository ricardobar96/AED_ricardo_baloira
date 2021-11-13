package es.iespuertodelacruz.ricardo.gestionMatriculas.modelo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Matricula {
	int idmatricula;
	Alumno alumno;
	@JsonProperty("año")
	int year;
	ArrayList<Asignatura> asignaturas;
	
	public Matricula(int idmatricula, Alumno alumno, int year) {
		super();
		this.idmatricula = idmatricula;
		this.alumno = alumno;
		this.year = year;
	}	
	
	
	public Matricula(int idmatricula, Alumno alumno, int year, ArrayList<Asignatura> asignaturas) {
		super();
		this.idmatricula = idmatricula;
		this.alumno = alumno;
		this.year = year;
		this.asignaturas = asignaturas;
	}


	public Matricula(Alumno alumno, int year, ArrayList<Asignatura> asignaturas) {
		super();
		this.alumno = alumno;
		this.year = year;
		this.asignaturas = asignaturas;
	}

	public Matricula(Alumno alumno, int year) {
		super();
		this.alumno = alumno;
		this.year = year;
	}

	public int getIdmatricula() {
		return idmatricula;
	}
	public void setIdmatricula(int idmatricula) {
		this.idmatricula = idmatricula;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public ArrayList<Asignatura> getAsignaturas() {
		return asignaturas;
	}
	public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}


	@Override
	public String toString() {
		return "Matricula [idmatricula=" + idmatricula + ", alumno=" + alumno + ", year=" + year + "]";
	}
	
	
}


