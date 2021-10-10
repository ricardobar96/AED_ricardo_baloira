/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.iespuertodelacruz.accesoaleatoriopersona.controlador;

import es.iespuertodelacruz.accesoaleatoriopersona.modelo.Persona;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ricardo baloira
 */
public class Controlador {
    ArrayList<Persona> lista = new ArrayList<>();
    RandomAccessFile raf;
    Persona p;

    public Controlador() {
    }

    public ArrayList<Persona> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Persona> lista) {
        this.lista = lista;
    }

    public RandomAccessFile getRaf() {
        return raf;
    }

    public void setRaf(RandomAccessFile raf) {
        this.raf = raf;
    }
    
    public void crearPersona() throws IOException{
        Scanner entrada = new Scanner(System.in);
        boolean fin = false;
        String nombre, apellidos, dni;
        String edad;
        int contador = 0;
        try{
            do{
              System.out.println("\nPersona " + (contador+1));
              System.out.println("Nombre: ");
              nombre = entrada.nextLine();
              
              System.out.println("Apellido: ");
              apellidos = entrada.nextLine();
              
              System.out.println("DNI: ");
              dni = entrada.nextLine();             
              
              System.out.println("Edad: ");
              edad = entrada.nextLine();
          
              p = new Persona(nombre, apellidos, dni, edad);
              lista.add(p);
              contador++;
              
              System.out.println("¿Crear mas objetos Persona? S/N");
              String respuesta = entrada.nextLine();
              if(respuesta.equalsIgnoreCase("n")){
                  fin = true;
              }
            }
            while(fin!=true);           
        }
        catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public void guardarPersonas() throws IOException{
        try{
            raf = new RandomAccessFile(new File("personas.txt"), "rw");
            raf.seek(raf.length());
            
            for (Persona p : lista) {     
                raf.writeUTF(p.getNombre().toLowerCase());
                escribirUTF(p.getNombre(), p.getSize_nombre());
                
                raf.writeUTF(p.getApellidos());
                escribirUTF(p.getApellidos(), p.getSize_apellidos());
                
                raf.writeUTF(p.getDni());
                escribirUTF(p.getDni(), p.getSize_dni());
                
                raf.writeUTF(p.getEdad());
                escribirUTF(p.getEdad(), p.getSize_edad());
                
                raf.writeUTF("\n");
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("Ha ocurrido un error");
        }
        finally{
            raf.close();
        }
    }
    
    public void escribirUTF(String string, int size) throws FileNotFoundException, IOException{
        for(int i=0; i<size-string.length(); i++){
            raf.writeByte(size);
        }     
    }
    
    public void leerUTF(String string, int size) throws FileNotFoundException, IOException{
        for(int i=0; i<size-string.length(); i++){
            raf.readByte();
        }     
    }
    
    public void buscarNombre() throws IOException{
        raf = new RandomAccessFile(new File("personas.txt"), "rw");
        String nombre = null;
        String apellidos = null; 
        String dni = null;
        String edad = null;
        boolean encontrado = false;
        Scanner entrada = new Scanner(System.in);
        System.out.println("Introduzca nombre de la persona a buscar: ");
        String nombreBuscar = entrada.nextLine().toLowerCase();
        try{
            long tamanoArchivo = raf.length();
            long numRecords = tamanoArchivo / p.getSize_bytes();
            raf.seek(0);
            for(int i =0; i<numRecords; i++){
                nombre = raf.readUTF();
                leerUTF(nombre, p.getSize_nombre());  

                apellidos = raf.readUTF(); 
                leerUTF(apellidos, p.getSize_apellidos());
                
                dni = raf.readUTF();
                leerUTF(dni, p.getSize_dni());
                
                edad = raf.readUTF();
                leerUTF(edad, p.getSize_edad());
                
                raf.readLine();
                
                if(nombreBuscar.equals(nombre)){
                    System.out.println("Nombre: " + nombre + " || Apellidos: " + apellidos + " || DNI: " + dni + " || Edad: " + edad);
                    encontrado = true;
                    System.out.println("\nIntroduzca nuevo dni para " + nombre + ":");
                    dni = entrada.nextLine();
                    raf.seek(p.getSize_apellidos()*i+64);
                    raf.writeUTF(dni);
                }
            }
            if(encontrado!=true){
                System.out.println("No se ha encontrado el nombre en el fichero");
            }           
        }
        catch(EOFException ex){
            System.out.println("\nFin del fichero");
        }
        finally{
            raf.close();
        }
    }

}
