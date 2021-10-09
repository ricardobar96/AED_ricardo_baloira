/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.entities;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ricardo baloira
 */
@XmlRootElement( name = "historico")
public class Historico implements Serializable{
    
    Integer idHistorico;

    public Historico(){}
    
    public Historico(Integer idHistorico, Moneda moneda, Date fecha, double equivalenteEuro) {
        this.idHistorico = idHistorico;
        this.moneda = moneda;
        this.fecha = fecha;
        this.equivalenteEuro = equivalenteEuro;
    }

    public Integer getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Integer idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    @XmlTransient
    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getEquivalenteEuro() {
        return equivalenteEuro;
    }

    public void setEquivalenteEuro(double equivalenteEuro) {
        this.equivalenteEuro = equivalenteEuro;
    }
    
    
   Moneda moneda;
   Date fecha;
   double equivalenteEuro;
    
}
