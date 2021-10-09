/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerpersonas.controlador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import leerpersonas.modelo.Persona;
import leerpersonas.modelo.Persona.DNI;

/**
 *
 * @author ricardo baloira
 */
public class Controlador {
    ArrayList<Persona> listaPersonas = new ArrayList<>();
    Scanner entrada = new Scanner(System.in);

    public Controlador() {
    }

    public ArrayList getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
   
    public void mostrarPersonas(){
        int numero = 1;
        try{
            for (Persona p : listaPersonas) {    
                System.out.println("\n--- PERSONA " + numero + " ---");
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Apellido: " + p.getApellido());
                System.out.println("DNI: " + p.getDni().getDniNumero() + p.getDni().getDniLetra());
                System.out.println("Edad: " + p.getEdad());
                System.out.println("Peso: " + p.getPeso());
                System.out.println("Altura: " + p.getAltura());
                System.out.println("IMC: " + p.getPeso() / (p.getAltura() * p.getAltura()));
                numero++;
            }
        }
        catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public void menu(){
        System.out.println("\nElige una opcion: ");
        System.out.println("1. Crear fichero con 5 objetos Persona");
        System.out.println("2. Leer fichero ya existente");
        System.out.println("3. Salir");
    }
    
    public void crearPersona(){
        Scanner entrada = new Scanner(System.in);
        int contador = 0;
        String nombre, apellido;
        char dniLetra;
        int edad, dniNumero;
        double peso, altura;
        try{
            do{
              System.out.println("\nPersona " + (contador+1));
              System.out.println("Nombre: ");
              nombre = entrada.nextLine();
              System.out.println("Apellido: ");
              apellido = entrada.nextLine();
              System.out.println("Numero DNI: ");
              dniNumero = entrada.nextInt();
              System.out.println("Letra DNI: ");
              dniLetra = entrada.next().charAt(0);
              System.out.println("Edad: ");
              edad = entrada.nextInt();
              System.out.println("Peso: ");
              peso = entrada.nextDouble();
              System.out.println("Altura: ");
              altura = entrada.nextDouble();
              DNI dni = new DNI(dniNumero, dniLetra);
              Persona persona = new Persona(nombre, apellido, dni, edad, peso, altura);
              listaPersonas.add(persona);  
              contador++;
              entrada.nextLine();
            }
            while(contador<2);
            
        }
        catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public void guardarPersonas(){
        try{
            System.out.println("Especifica el fichero donde guardar los objetos Persona creados");
            String ruta = entrada.nextLine();
            FileOutputStream fos = new FileOutputStream(new File(ruta));
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            for (Persona p : listaPersonas) {
                oos.writeObject(p);
            }
            oos.close();
            bos.close();
            fos.close();
        }
        catch(IOException ex){
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public void leerPersonasFichero() throws FileNotFoundException, IOException, ClassNotFoundException{
        boolean finDeFichero = false;
        int numero = 1;
        try{
            System.out.println("Especifica el fichero a leer con objetos Persona");
            String ruta = entrada.nextLine();
            FileInputStream fis = new FileInputStream(new File(ruta));
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            while(!finDeFichero){
                Persona auxiliar = (Persona)ois.readObject();
                System.out.println("\n--- PERSONA " + numero + " ---");
                System.out.println("Nombre: " + auxiliar.getNombre());
                System.out.println("Apellido: " + auxiliar.getApellido());
                System.out.println("DNI: " + auxiliar.getDni().getDniNumero() + auxiliar.getDni().getDniLetra());
                System.out.println("Edad: " + auxiliar.getEdad());
                System.out.println("Peso: " + auxiliar.getPeso());
                System.out.println("Altura: " + auxiliar.getAltura());
                System.out.println("IMC: " + auxiliar.getPeso() / (auxiliar.getAltura() * auxiliar.getAltura()));
                numero++;
            }
            ois.close();
            bis.close();
            fis.close();
        }
        catch(EOFException ex){
            System.out.println("\nFin del fichero");
            finDeFichero = true;
        }
    }
}
