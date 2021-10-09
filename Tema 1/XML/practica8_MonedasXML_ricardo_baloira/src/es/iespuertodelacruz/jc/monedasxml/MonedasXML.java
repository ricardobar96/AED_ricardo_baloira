/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml;

import es.iespuertodelacruz.jc.monedasxml.entities.Almacen;
import es.iespuertodelacruz.jc.monedasxml.entities.Historico;
import es.iespuertodelacruz.jc.monedasxml.entities.Moneda;
import es.iespuertodelacruz.jc.monedasxml.utils.ManejoFichero;
import es.iespuertodelacruz.jc.monedasxml.xml.HistoricoXML;
import es.iespuertodelacruz.jc.monedasxml.xml.MonedaXML;
import java.io.FileNotFoundException;
import java.util.Date;
import javax.xml.bind.JAXBException;

/**
 *
 * @author ricardo baloira
 */
public class MonedasXML {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     * @throws javax.xml.bind.JAXBException
     */
    public static void main(String[] args) throws FileNotFoundException, JAXBException {
        MonedaXML monedaXML = new MonedaXML();
        HistoricoXML hXML = new HistoricoXML();
        
        Moneda m = new Moneda(1,"libra","uk"); 
        Moneda m2 = new Moneda(2,"euro","alemania"); 
        Moneda m3 = new Moneda(3, "peso", "mexico");          
        
        Historico h = new Historico();
        h.setMoneda(m);
        h.setEquivalenteEuro(2.3);
        h.setFecha(new Date());
        h.setIdHistorico(1);
        
        m.getHistoricos().add(h);
    
        Historico h2 = new Historico();
        h2.setMoneda(m2);
        h2.setEquivalenteEuro(1.0);
        h2.setFecha(new Date());
        h2.setIdHistorico(2);
        
        m2.getHistoricos().add(h2);
  
        Historico h3 = new Historico();
        h3.setMoneda(m3);
        h3.setEquivalenteEuro(16.3);
        h3.setFecha(new Date());
        h3.setIdHistorico(3);
        
        m3.getHistoricos().add(h3);

        Almacen almacen = new Almacen();
        almacen.getMoneda().add(m);
        almacen.getMoneda().add(m2);
        almacen.getMoneda().add(m3);

        ManejoFichero mf = new ManejoFichero("miXML.txt");
        System.out.println("---- Guardando monedas e historicos en xml ----");
        mf.agregarAlmacen(almacen);

        System.out.println("\n---- Leyendo monedas e historicos ----");
        mf.leerTodo();
    }  
}
