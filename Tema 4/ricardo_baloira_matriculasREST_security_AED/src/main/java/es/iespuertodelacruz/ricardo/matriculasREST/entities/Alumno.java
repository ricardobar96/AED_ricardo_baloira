package es.iespuertodelacruz.ricardo.matriculasREST.entities;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the alumnos database table.
 * 
 */
@Entity
@Table(name="alumnos")
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a")


public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String apellidos;
	
	private BigInteger fechanacimiento;

	private String nombre;

	//bi-directional many-to-one association to Matricula
	@JsonIgnore
	@OneToMany(mappedBy="alumno")
	private List<Matricula> matriculas;

	public Alumno() {
	}

	public Alumno(String dni, String apellidos, BigInteger fechanacimiento, String nombre, List<Matricula> matriculas) {
		super();
		this.dni = dni;
		this.apellidos = apellidos;
		this.fechanacimiento = fechanacimiento;
		this.nombre = nombre;
		this.matriculas = matriculas;
	}

	public Alumno(String dni, String apellidos, BigInteger fechanacimiento, String nombre) {
		super();
		this.dni = dni;
		this.apellidos = apellidos;
		this.fechanacimiento = fechanacimiento;
		this.nombre = nombre;
	}
	
	public Alumno(String dni) {
		super();
		this.dni = dni;
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public BigInteger getFechanacimiento() {
		return this.fechanacimiento;
	}

	public void setFechanacimiento(BigInteger fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
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

	public Matricula addMatricula(Matricula matricula) {
		getMatriculas().add(matricula);
		matricula.setAlumno(this);

		return matricula;
	}

	public Matricula removeMatricula(Matricula matricula) {
		getMatriculas().remove(matricula);
		matricula.setAlumno(null);

		return matricula;
	}

	@Override
	public String toString() {
		return "Alumno [dni=" + dni + ", apellidos=" + apellidos + ", fechanacimiento=" + fechanacimiento + ", nombre="
				+ nombre + ", matriculas=" + matriculas + "]";
	}
}