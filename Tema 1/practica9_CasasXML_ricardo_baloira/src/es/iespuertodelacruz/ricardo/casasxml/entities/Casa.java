/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ricardo.casasxml.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ricardo baloira
 */
@XmlRootElement (name="casa")
public class Casa implements Serializable{
    Integer idCasa;
    String direccion;
    Integer habitaciones;
    double precio;
    ArrayList<Propietario> propietarios = new ArrayList<Propietario>();

    public Casa() {
    }

    public Casa(Integer idCasa, String direccion, Integer habitaciones, double precio) {
        this.idCasa = idCasa;
        this.direccion = direccion;
        this.habitaciones = habitaciones;
        this.precio = precio;
    }

    public Integer getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Integer idCasa) {
        this.idCasa = idCasa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ArrayList<Propietario> getPropietarios() {
        return propietarios;
    }
    
    @XmlTransient
    public void setPropietarios(ArrayList<Propietario> propietarios) {
        this.propietarios = propietarios;
    }

    @Override
    public String toString() {
        return "Casa{" + "idCasa=" + idCasa + ", direccion=" + direccion + ", habitaciones=" + habitaciones + ", precio=" + precio + ", propietarios=" + propietarios + '}';
    }
}
