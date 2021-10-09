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
@XmlRootElement(name="moneda")
public class Moneda implements Serializable{

    Integer idMoneda;
    String nombre;
    String pais;
    
    public ArrayList<Historico> getHistoricos() {
        return historicos;
    }

    
    public void setHistoricos(ArrayList<Historico> historicos) {
        this.historicos = historicos;
    }  
    
    ArrayList<Historico> historicos = new ArrayList<Historico>();

    public Moneda(){}
    
    public Moneda(Integer idMoneda, String nombre, String pais) {
        this.idMoneda = idMoneda;
        this.nombre = nombre;
        this.pais = pais;
    }

    public Integer getIdMoneda() {
        return idMoneda;
    }

    public void setIdMoneda(Integer idMoneda) {
        this.idMoneda = idMoneda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
    @Override
    public String toString(){
        
        return "" + idMoneda + " " + nombre + " país: " + pais;
    }
    
}
