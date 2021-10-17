/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerpersonas;

import java.io.FileNotFoundException;
import leerpersonas.vista.Vista;

/**
 *
 * @author ricardo baloira
 */
public class LeerPersonas {
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
  
        Vista vista = new Vista();
        vista.iniciarVista(); 
    }
    
}
