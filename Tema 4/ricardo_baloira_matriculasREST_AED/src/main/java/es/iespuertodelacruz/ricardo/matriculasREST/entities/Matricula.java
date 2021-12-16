package es.iespuertodelacruz.ricardo.matriculasREST.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the matriculas database table.
 * 
 */
@Entity
@Table(name="matriculas")
@NamedQuery(name="Matricula.findAll", query="SELECT m FROM Matricula m")
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idmatricula;

	private int year;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	@JoinColumn(name="dni")
	private Alumno alumno;
	
	@ManyToMany(mappedBy="matriculas", fetch = FetchType.EAGER)
	private List<Asignatura> asignaturas;

	public Matricula() {
	}
	
	public Matricula(int idmatricula, int year, Alumno alumno, List<Asignatura> asignaturas) {
		super();
		this.idmatricula = idmatricula;
		this.year = year;
		this.alumno = alumno;
		this.asignaturas = asignaturas;
	}

	public int getIdmatricula() {
		return this.idmatricula;
	}

	public void setIdmatricula(int idmatricula) {
		this.idmatricula = idmatricula;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	@Override
	public String toString() {
		return "Matricula [idmatricula=" + idmatricula + ", year=" + year + ", alumno=" + alumno + ", asignaturas="
				+ asignaturas + "]";
	}
}