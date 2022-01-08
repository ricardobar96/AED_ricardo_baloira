package es.iespuertodelacruz.daniel.matriculasrest.dto;

import java.util.List;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Asignatura;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;

public class AsignaturaDTO {
	private String curso;

	private String nombre;

	private List<Matricula> matriculasList;
	
	public AsignaturaDTO() {}
	
	public AsignaturaDTO(Asignatura asignatura) {
		this.curso = asignatura.getCurso();
		this.nombre = asignatura.getNombre();
		this.matriculasList = asignatura.getMatriculas();
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
		return matriculasList;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculasList = matriculas;
	}
	
}
