/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ricardo baloira
 */

@XmlRootElement(name = "monedas")
public class Almacen implements Serializable{
    
    ArrayList<Moneda> moneda = new ArrayList<Moneda>();

    public Almacen() {
    }

    public ArrayList<Moneda> getMoneda() {
        return moneda;
    }

    public void setMoneda(ArrayList<Moneda> moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return "Almacen{" + "monedas=" + moneda + '}';
    }
}
