/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.accesoaleatoriopersona.modelo;

import java.io.IOException;

/**
 *
 * @author ricardo baloira
 */
public final class Persona {
    String nombre;
    String apellidos;
    String dni;
    String edad;
    final int size_nombre = 20;
    final int size_apellidos = 40; 
    final int size_dni = 9; 
    final int size_edad = 3; 
    final int size_bytes = 144;

    public Persona(String nombre, String apellidos, String dni, String edad) throws IOException {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.edad = edad;
    }

    public Persona() {
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public int getSize_nombre() {
        return size_nombre;
    }

    public int getSize_apellidos() {
        return size_apellidos;
    }

    public int getSize_dni() {
        return size_dni;
    }

    public int getSize_edad() {
        return size_edad;
    }

    public int getSize_bytes() {
        return size_bytes;
    }
}
