/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerpersonas;

import java.io.FileNotFoundException;
import java.util.Scanner;
import leerpersonas.controlador.Controlador;

/**
 *
 * @author ricardo baloira
 */
public class LeerPersonas {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        Controlador controlador = new Controlador();
        Scanner entrada = new Scanner(System.in);
        boolean fin = false;
        do{
            try{
               controlador.menu(); 
               int opcion = entrada.nextInt();
               switch(opcion){
                   case 1:
                       controlador.crearPersona();
                       controlador.guardarPersonas();
                       controlador.mostrarPersonas();
                   break;
                   case 2:
                       controlador.leerPersonasFichero();
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
    
}
