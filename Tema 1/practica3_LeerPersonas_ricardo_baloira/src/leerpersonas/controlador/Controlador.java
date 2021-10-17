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
import leerpersonas.modelo.Persona;
import leerpersonas.vista.Vista;

/**
 *
 * @author ricardo baloira
 */
public class Controlador {
    ArrayList<Persona> listaPersonas = new ArrayList<>();
    Vista vista = new Vista();

    public Controlador() {
    }

    public ArrayList getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(ArrayList<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }
    
    public void registrarPersona(String nombre, String apellido, String dni, int edad, double peso, double altura){
        Persona persona = new Persona(nombre, apellido, dni, edad, peso, altura);
        listaPersonas.add(persona);
    }
    
    public void leerPersonasFichero(String ruta) throws FileNotFoundException{
        try{
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
            listaPersonas.clear();
        }
        catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }  
   
    public void guardarPersonas(String ruta){
        try{
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(ruta)));
            for (Persona p : listaPersonas) {
                pw.write(p.getNombre() + ";" + p.getApellido() + ";" + p.getDni() + ";" + p.getEdad() + ";" + p.getPeso() + ";" + p.getAltura()+"\n" );
            }
            pw.close();
        }
        catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
    }
}
