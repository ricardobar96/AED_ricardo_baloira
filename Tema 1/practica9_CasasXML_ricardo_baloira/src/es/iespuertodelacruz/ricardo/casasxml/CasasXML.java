/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.ricardo.casasxml;

import es.iespuertodelacruz.ricardo.casasxml.entities.Casa;
import es.iespuertodelacruz.ricardo.casasxml.entities.Propietario;
import es.iespuertodelacruz.ricardo.casasxml.entities.Propietarios;
import es.iespuertodelacruz.ricardo.casasxml.utils.ManejoFichero;
import es.iespuertodelacruz.ricardo.casasxml.xml.PropietarioXML;
import java.io.FileNotFoundException;
import javax.xml.bind.JAXBException;

/**
 *
 * @author ricardo baloira
 */
public class CasasXML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, JAXBException {
        PropietarioXML propietarioXML = new PropietarioXML();
        
        Propietario p = new Propietario(1, "Carla", "Lopez Tejeda");
        Propietario p2 = new Propietario(2, "Cristian", "Barreto Lorenzo");
        Propietario p3 = new Propietario(3, "Julio", "Rodriguez Perez");
        Propietario p4 = new Propietario(4, "Marta", "Perez Pais");
        Propietario p5 = new Propietario(5, "Antonio", "Diaz de la Fuente");
        
        Casa c = new Casa(1, "C/ Las Cabezas, numero 11", 3, 80000);
        Casa c2 = new Casa(2, "C/ Fernandez, numero 18", 5, 120000);
        Casa c3 = new Casa(3, "C/ Triana, numero 6", 2, 50000);
        Casa c4 = new Casa(4, "C/ Nube, numero 15", 2, 45000);
        
        c.getPropietarios().add(p);
        c2.getPropietarios().add(p2);
        c2.getPropietarios().add(p3);
        c3.getPropietarios().add(p4);
        c3.getPropietarios().add(p5);
        c4.getPropietarios().add(p);
        
        p.getCasa().add(c);
        p.getCasa().add(c4);
        p2.getCasa().add(c2);
        p3.getCasa().add(c2);
        p4.getCasa().add(c3);
        p5.getCasa().add(c3);
        
        Propietarios propietarios = new Propietarios();
        propietarios.getPropietario().add(p);
        propietarios.getPropietario().add(p2);
        propietarios.getPropietario().add(p3);
        propietarios.getPropietario().add(p4);
        propietarios.getPropietario().add(p5);
        
        ManejoFichero mf = new ManejoFichero("CasasXML.txt");
        System.out.println("---- Guardando casas y propietarios en xml ----");
        for (Propietario propietario : propietarios.getPropietario()) {
            System.out.println(propietarioXML.objToStringXML(propietario));
        }
  
        mf.agregarPropietarios(propietarios);

        System.out.println("\n---- Leyendo casas y propietarios ----");
        mf.leerTodo("CasasXML.txt");
        
        System.out.println("\n---- Borrando y agregando nueva casa y propietario ----");
        mf.borrarYAgregar(1);
        mf.leerTodo("nuevoPropietarioXML.txt");
    }
    
}
