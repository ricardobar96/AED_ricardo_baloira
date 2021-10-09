/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerpersonas.controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import leerpersonas.modelo.Persona;

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
    
    public void leerPersonasFichero() throws FileNotFoundException{
        try{
            System.out.println("Especifica el fichero a leer con objetos Persona");
            String ruta = entrada.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader(ruta));
            String line;
            while((line = reader.readLine())!=null){
                listaPersonas.add(new Persona(line.split(";")[0], line.split(";")[1], line.split(";")[2], Integer.parseInt(line.split(";")[3]), Double.parseDouble(line.split(";")[4]), Double.parseDouble(line.split(";")[5])));
            }
            reader.close();
        }
        catch(IOException ex){
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public void mostrarPersonas(){
        int numero = 1;
        try{
            for (Persona p : listaPersonas) {    
                System.out.println("\n--- PERSONA " + numero + " ---");
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Apellido: " + p.getApellido());
                System.out.println("DNI: " + p.getDni());
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
        System.out.println("2. Leer fichero Existente con objetos Persona");
        System.out.println("3. Salir");
    }
    
    public void crearPersona(){
        Scanner entrada = new Scanner(System.in);
        int contador = 0;
        String nombre, apellido, dni;
        int edad;
        double peso, altura;
        try{
            do{
              System.out.println("\nPersona " + (contador+1));
              System.out.println("Nombre: ");
              nombre = entrada.nextLine();
              System.out.println("Apellido: ");
              apellido = entrada.nextLine();
              System.out.println("DNI: ");
              dni = entrada.nextLine();
              System.out.println("Edad: ");
              edad = entrada.nextInt();
              System.out.println("Peso: ");
              peso = entrada.nextDouble();
              System.out.println("Altura: ");
              altura = entrada.nextDouble();
              Persona persona = new Persona(nombre, apellido, dni, edad, peso, altura);
              listaPersonas.add(persona);  
              contador++;
              entrada.nextLine();
            }
            while(contador<5);
            
        }
        catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
    
    public void guardarPersonas(){
        try{
            System.out.println("Especifica el fichero donde guardar los objetos Persona creados");
            String ruta = entrada.nextLine();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ruta)));
            for (Persona p : listaPersonas) {
                pw.write("\n" + p.getNombre() + ";" + p.getApellido() + ";" + p.getDni() + ";" + p.getEdad() + ";" + p.getPeso() + ";" + p.getAltura());
            }
            pw.close();
        }
        catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
}
