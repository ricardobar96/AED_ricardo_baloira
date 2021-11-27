package es.iespuertodelacruz.ricardo.matriculasJPA.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the asignaturas database table.
 * 
 */
@Entity
@Table(name="asignaturas")
@NamedQuery(name="Asignatura.findAll", query="SELECT a FROM Asignatura a")
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idasignatura;

	private String curso;

	private String nombre;

	//bi-directional many-to-many association to Matricula
	@ManyToMany
	@JoinTable( name="asignatura_matricula",
	joinColumns = @JoinColumn(name="idasignatura"),
	inverseJoinColumns = @JoinColumn(name="idmatricula")
	)

	private List<Matricula> matriculas;

	public Asignatura() {
	}

	public int getIdasignatura() {
		return this.idasignatura;
	}

	public void setIdasignatura(int idasignatura) {
		this.idasignatura = idasignatura;
	}

	public String getCurso() {
		return this.curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Matricula> getMatriculas() {
		return this.matriculas;
	}

	public void setMatriculas(List<Matricula> matriculas) {
		this.matriculas = matriculas;
	}

	@Override
	public String toString() {
		return "Asignatura [curso=" + curso + ", nombre=" + nombre + "]";
	}
	
	
}