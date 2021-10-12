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
import java.util.Date;
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
    
    public void borrarYAgregar(int idMoneda){
        try{
            Almacen datos = unmarshalAlmacen("miXML.xml");
            datos.getMoneda().removeIf((Moneda monedaBorrar) -> idMoneda==(monedaBorrar.getIdMoneda()));
            Moneda nuevaMoneda = new Moneda(4, "dolar", "eeuu");
            Historico h = new Historico();
            h.setMoneda(nuevaMoneda);
            h.setEquivalenteEuro(0.2);
            h.setFecha(new Date());
            h.setIdHistorico(4);
            nuevaMoneda.getHistoricos().add(h);
            datos.getMoneda().add(nuevaMoneda);
            marshalAlmacen(datos, "nuevoXML.xml");
        }
        catch(JAXBException ex){
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public void leerTodo(String archivoLeer){
        try {
            Almacen almacen = unmarshalAlmacen(file.toString());
            
            for (Moneda m : almacen.getMoneda()){
                for (Historico h : m.getHistoricos()) {
                    System.out.println("Id Moneda: " + m.getIdMoneda() + " || Nombre: " + m.getNombre() + " || Pais: " + m.getPais() + " || Id Historico: " + h.getIdHistorico() + " || Fecha: " + h.getFecha() + " || Equivalencia Euros: " + h.getEquivalenteEuro());
                }             
            }
        } 
        catch (JAXBException e) {
            System.out.println("Ha ocurrido un error");
        } 
    }
    
    public Almacen unmarshalAlmacen(String archivo) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Almacen.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Almacen) jaxbUnmarshaller.unmarshal(new File(archivo));
    }
    
    public void marshalAlmacen(Almacen datos, String archivo) throws JAXBException{
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(datos.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(datos, new File(archivo));
        }
        catch(JAXBException e){
            System.out.println("Ha ocurrido un error");
        }
    }
}
