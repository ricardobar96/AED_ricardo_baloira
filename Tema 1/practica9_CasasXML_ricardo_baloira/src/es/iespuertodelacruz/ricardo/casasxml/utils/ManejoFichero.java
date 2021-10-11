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
    
    public void borrarYAgregar(int idPropietario){
        try{
            Propietarios datos = unmarshalPropietarios("CasasXML.txt");
            datos.getPropietario().removeIf((Propietario propietarioBorrar) -> idPropietario==(propietarioBorrar.getIdPropietario()));
            Propietario nuevoPropietario = new Propietario(6, "Susana", "Ramos Vicente");
            Casa c5 = new Casa(5, "C/ Rua de la Oca, numero 13", 5, 130000);
            nuevoPropietario.getCasa().add(c5);
            datos.getPropietario().add(nuevoPropietario);
            marshalPropietarios(datos, "nuevoPropietarioXML.txt");
        }
        catch(JAXBException ex){
            System.out.println("Ha ocurrido un error");
        }  
    }
    
    public void leerTodo(String archivoLeer){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Propietarios.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();        
            Propietarios propietarios = (Propietarios) jaxbUnmarshaller.unmarshal( new File(archivoLeer) );
            
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
    
    private static Propietarios unmarshalPropietarios(String archivo) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Propietarios.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (Propietarios) jaxbUnmarshaller.unmarshal(new File(archivo));
    }
    
    private static void marshalPropietarios(Propietarios datos, String archivo) throws JAXBException{
        JAXBContext jaxbContext = JAXBContext.newInstance(Propietarios.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(datos, new File(archivo));
    }   
}
