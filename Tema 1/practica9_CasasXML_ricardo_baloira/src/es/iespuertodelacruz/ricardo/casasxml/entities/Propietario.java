/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ricardo.casasxml.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo baloira
 */
@XmlRootElement(name="propietario")
public class Propietario implements Serializable{
    
    Integer idPropietario;
    String nombre;
    String apellidos;
    ArrayList<Casa> casa = new ArrayList<Casa>();

    public Propietario(Integer idPropietario, String nombre, String apellidos) {
        this.idPropietario = idPropietario;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Propietario() {
    }

    public Integer getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
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

    public ArrayList<Casa> getCasa() {
        return casa;
    }

    public void setCasa(ArrayList<Casa> casa) {
        this.casa = casa;
    }

    @Override
    public String toString() {
        return "Propietario{" + "idPropietario=" + idPropietario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", casas=" + casa + '}';
    }
}
