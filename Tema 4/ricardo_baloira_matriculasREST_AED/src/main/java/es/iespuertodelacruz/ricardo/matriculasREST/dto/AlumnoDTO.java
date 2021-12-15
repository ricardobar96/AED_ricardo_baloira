package es.iespuertodelacruz.ricardo.matriculasREST.dto;

import java.math.BigInteger;
import java.util.List;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Alumno;
import es.iespuertodelacruz.ricardo.matriculasREST.entities.Matricula;

public class AlumnoDTO {
	private String dni;

	private String apellidos;

	private BigInteger fechanacimiento;

	private String nombre;
	
	private List<Matricula> matriculas;
	
	public AlumnoDTO() {
		super();
	}

	public AlumnoDTO(Alumno a) {
		super();
		this.dni = a.getDni();
		this.apellidos = a.getApellidos();
		this.fechanacimiento = a.getFechanacimiento();
		this.nombre = a.getNombre();
		this.matriculas = a.getMatriculas();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public BigInteger getFechanacimiento() {
		return fechanacimiento;
	}

	public void setFechanacimiento(BigInteger fechanacimiento) {
		this.fechanacimiento = fechanacimiento;
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
