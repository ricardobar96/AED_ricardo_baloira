package es.iespuertodelacruz.ricardo.matriculasREST.dto;

import es.iespuertodelacruz.ricardo.matriculasREST.entities.Usuarioconrol;

public class UsuarioDTO {
	private int idusuario;
	private String nombre;
	private String password;
	private String rol;
	
	public 	UsuarioDTO() {
		super();
	}

	public UsuarioDTO(Usuarioconrol u) {
		super();
		this.idusuario = u.getIdusuario();
		this.nombre = u.getNombre();
		this.password = u.getPassword();
		this.rol = u.getRol();
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
}
