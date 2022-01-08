package es.iespuertodelacruz.daniel.matriculasrest.dto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import es.iespuertodelacruz.daniel.matriculasrest.entity.Alumno;
import es.iespuertodelacruz.daniel.matriculasrest.entity.Matricula;

public class AlumnoDTO {
	public AlumnoDTO() {}
	private String dni, nombre, apellidos;
	private List<Matricula> matriculasList;
	private BigInteger fechaNacimiento;
	
	public AlumnoDTO(Alumno alumno) {
		dni = alumno.getDni();
		nombre = alumno.getNombre();
		apellidos = alumno.getApellidos();
		fechaNacimiento = alumno.getFechanacimiento();
		matriculasList = alumno.getMatriculas();
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public BigInteger getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(BigInteger fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public List<Matricula> getMatriculasList() {
		return matriculasList;
	}
	public void setMatriculasList(List<Matricula> matriculasList) {
		this.matriculasList = matriculasList;
	}
	
}
