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

@XmlRootElement (name="propietarios")
public class Propietarios implements Serializable{
    
    ArrayList<Propietario> propietario = new ArrayList<>();

    public Propietarios() {
    }

    public ArrayList<Propietario> getPropietario() {
        return propietario;
    }

    public void setPropietario(ArrayList<Propietario> propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        return "Propietarios{" + "propietario=" + propietario + '}';
    }
}
