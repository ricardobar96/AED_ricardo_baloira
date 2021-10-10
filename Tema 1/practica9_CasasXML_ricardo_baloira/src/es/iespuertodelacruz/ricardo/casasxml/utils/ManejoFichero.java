/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ricardo.casasxml.utils;

import es.iespuertodelacruz.ricardo.casasxml.entities.Casa;
import es.iespuertodelacruz.ricardo.casasxml.entities.Propietario;
import es.iespuertodelacruz.ricardo.casasxml.entities.Propietarios;
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

    public ManejoFichero(String nombre) {
        file = new File(nombre);
    }
    
    public void agregarPropietarios(Propietarios propietarios) throws FileNotFoundException, JAXBException{
        try {
            JAXBContext contexto = JAXBContext.newInstance(propietarios.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            marshaller.marshal(propietarios, file);
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
            JAXBContext jaxbContext = JAXBContext.newInstance(Propietarios.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();        
            Propietarios propietarios = (Propietarios) jaxbUnmarshaller.unmarshal( new File("CasasXML.txt") );
            
            for(Propietario p : propietarios.getPropietario()){
                for (Casa c : p.getCasa()) {
                    System.out.println("Id Propietario: " + p.getIdPropietario() + " || Nombre: " + p.getNombre() + " || Apellidos: " + p.getApellidos() + " || Id Casa: " + c.getIdCasa() + " || Direccion: " + c.getDireccion() + " || Habitaciones: " + c.getHabitaciones() + " || Precio: " + c.getPrecio());
                }             
            }
        } 
        catch (JAXBException e) {
            System.out.println("Ha ocurrido un error");
        } 
    }
    
}
