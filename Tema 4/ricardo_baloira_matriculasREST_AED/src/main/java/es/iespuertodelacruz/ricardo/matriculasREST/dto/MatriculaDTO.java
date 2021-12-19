package es.iespuertodelacruz.ricardo.matriculasREST.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;

public class MatriculaDTO {
	private int idmatricula;
	private Alumno alumno;
	@JsonProperty("año")
	private int year;
	private List<Asignatura> asignaturas;
	
	public MatriculaDTO() {
		super();
	}

	public MatriculaDTO(Matricula m) {
		super();
		this.idmatricula = m.getIdmatricula();
		this.alumno = m.getAlumno();
		this.year = m.getYear();
		this.asignaturas = m.getAsignaturas();
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

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
}
