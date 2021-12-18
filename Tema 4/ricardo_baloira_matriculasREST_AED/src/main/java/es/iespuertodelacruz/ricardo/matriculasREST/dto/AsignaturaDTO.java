package es.iespuertodelacruz.ricardo.matriculasREST.dto;

import java.util.List;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Asignatura;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;

public class AsignaturaDTO {
	private int idasignatura;

	private String curso;

	private String nombre;
	
	private List<Matricula> matriculas;

	public AsignaturaDTO(Asignatura a) {
		super();
		this.idasignatura = a.getIdasignatura();
		this.curso = a.getCurso();
		this.nombre = a.getNombre();
	}

	public AsignaturaDTO() {
		super();
	}

	public int getIdasignatura() {
		return idasignatura;
	}

	public void setIdasignatura(int idasignatura) {
		this.idasignatura = idasignatura;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Matricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}
}
