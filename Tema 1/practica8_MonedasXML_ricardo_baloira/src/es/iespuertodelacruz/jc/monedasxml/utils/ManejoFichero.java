/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.jc.monedasxml.utils;

import es.iespuertodelacruz.jc.monedasxml.entities.Almacen;
import es.iespuertodelacruz.jc.monedasxml.entities.Historico;
import es.iespuertodelacruz.jc.monedasxml.entities.Moneda;
import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author ricardo baloira
 */
public class ManejoFichero {
    
    File file;
    
    public ManejoFichero(String nombre){
        file = new File(nombre);
    }
    
    public void agregarAlmacen(Almacen almacen) throws FileNotFoundException, JAXBException{
        try {
            JAXBContext contexto = JAXBContext.newInstance(almacen.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(almacen, file);
        } 
        catch (JAXBException e) {
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public boolean borrarYAgregar(String texto){
        return false;    
    }
    
    public void leerTodo(){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Almacen.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();        
            Almacen almacen = (Almacen) jaxbUnmarshaller.unmarshal( new File("miXML.txt") );
            
            for(Moneda m : almacen.getMoneda()){
                for (Historico h : m.getHistoricos()) {
                    System.out.println("Id Moneda: " + m.getIdMoneda() + " || Nombre: " + m.getNombre() + " || Pais: " + m.getPais() + " || Id Historico: " + h.getIdHistorico() + " || Fecha: " + h.getFecha() + " || Equivalencia Euros: " + h.getEquivalenteEuro());
                }             
            }
        } 
        catch (JAXBException e) {
            System.out.println("Ha ocurrido un error");
        } 
    }
}
