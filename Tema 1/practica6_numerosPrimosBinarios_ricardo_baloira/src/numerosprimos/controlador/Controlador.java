/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numerosprimos.controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ricardo baloira
 */
public class Controlador {
    ArrayList<Integer> listaPrimos = new ArrayList<>();
    int limite = 10000000;
    File f = new File("numeros.txt");
    
    
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
            FileOutputStream fis=new FileOutputStream(f);
            DataOutputStream dos=new DataOutputStream(fis);
            for (Integer primo : listaPrimos) {
                dos.writeInt(primo);
            }
            dos.close();
            fis.close();
        }
        catch(IOException ex){
            System.out.println("Ha ocurrido un error");
        }
        
    }
    
    public void mostrarPrimos() throws FileNotFoundException{
        int contador = 0;
        FileInputStream fis=new FileInputStream(f);
        DataInputStream dis=new DataInputStream(fis);
        try{
            do{
                System.out.println("Numero " + (contador + 1));
                System.out.println(dis.readInt()); 
                contador++;
            }
            while(contador<100);
            dis.close();
            fis.close();
        }
        catch(IOException ex){
            System.out.println("Ha ocurrido un error");
        }
    }
}
