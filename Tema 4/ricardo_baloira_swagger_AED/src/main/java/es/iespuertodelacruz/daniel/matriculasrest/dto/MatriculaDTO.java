package es.iespuertodelacruz.daniel.matriculasrest.dto;

import java.util.List;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;

public class MatriculaDTO {
	private int year, idMatr;
	private Alumno alumno;
	private List<Asignatura> asignaturas;
	
	public MatriculaDTO() {}

	public MatriculaDTO(Matricula matricula) {
		this.year = matricula.getYear();
		this.alumno = matricula.getAlumno();
		this.asignaturas = matricula.getAsignaturas();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public int getIdMatr() {
		return idMatr;
	}

	public void setIdMatr(int idMatr) {
		this.idMatr = idMatr;
	}
	
}
