/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerosprimos.controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ricardo baloira
 */
public class Controlador {
    ArrayList<Integer> listaPrimos = new ArrayList<>();
    int limite = 10000000;
    
    
    public void calcularPrimos(){
        for(int i=1; i<limite; i++){
            if(esPrimo(i)){
                listaPrimos.add(i);
            }
        }
    }
    
    public boolean esPrimo(int numero){
        for(int i=2; i*i<=numero; i++){
            if(numero % i ==0){
                return false;
            }
        }        
        return true;   
    }
    
    public void leerPrimos() throws IOException{
        try{
            FileWriter fw = new FileWriter("./src/numeros.txt");
            for (Integer primo : listaPrimos) {
                System.out.println(primo);
                fw.write(Integer.toString(primo));
                fw.write(System.lineSeparator());
            }
            fw.close();
        }
        catch(Exception ex){
            System.out.println("Ha ocurrido un error");
        }
        
    }
}
