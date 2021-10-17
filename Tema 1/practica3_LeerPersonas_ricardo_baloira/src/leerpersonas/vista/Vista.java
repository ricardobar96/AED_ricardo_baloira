/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerpersonas.vista;

import java.util.Scanner;
import leerpersonas.controlador.Controlador;

/**
 *
 * @author ricardo baloira
 */
public class Vista {
    Scanner entrada = new Scanner(System.in);
    Controlador controlador;
    
    public Vista() {
    }
    
    public void iniciarVista(){
        controlador = new Controlador();
        boolean fin = false;
        do{
            try{
               menu(); 
               int opcion = entrada.nextInt();
               switch(opcion){
                   case 1:
                       crearPersona();
                       controlador.guardarPersonas(escogerFichero());
                       controlador.mostrarPersonas();
                   break;
                   case 2:
                       controlador.leerPersonasFichero(escogerFichero());
                       controlador.mostrarPersonas();
                   break;
                   case 3:
                       fin = true;
                   break;
                   default:
                       System.out.println("Debes introducir una opcion del 1 al 3");
                   break;
               }
            }
            catch(Exception ex){
                System.out.println("Ha ocurrido un error");
            }
        }
        while(fin!=true);    
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
              
              controlador.registrarPersona(nombre, apellido, dni, edad, peso, altura);
              
              contador++;
              entrada.nextLine();
            }
            while(contador<5);
            
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
    
    public String escogerFichero(){
        System.out.println("Especifica el fichero a leer con objetos Persona");
        entrada.nextLine();
        String ruta = entrada.nextLine();
        return ruta;
    }
}
