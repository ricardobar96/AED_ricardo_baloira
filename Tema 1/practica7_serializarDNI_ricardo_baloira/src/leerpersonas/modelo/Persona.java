/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leerpersonas.modelo;

import java.io.Serializable;

/**
 *
 * @author ricardo baloira
 */
public class Persona implements Serializable{
    String nombre;
    String apellido;
    DNI dni;
    int edad;
    double peso;
    double altura;

    public Persona(String nombre, String apellido, DNI dni, int edad, double peso, double altura) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.peso = peso;
        this.altura = altura;
    }

    public Persona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public DNI getDni() {
        return dni;
    }

    public void setDni(DNI dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
    
    public static class DNI implements Serializable{
        int dniNumero;
        char dniLetra;

        public DNI(int dniNumero, char dniLetra) throws Exception {
            if(ObtenerNIF(dniNumero, dniLetra)){
               this.dniNumero = dniNumero;
               this.dniLetra = dniLetra; 
            }
            else{
                System.out.println("El DNI no es correcto");
            }
        }

        public DNI() {
        }

        public int getDniNumero() {
            return dniNumero;
        }

        public void setDniNumero(int dniNumero) {
            this.dniNumero = dniNumero;
        }

        public char getDniLetra() {
            return dniLetra;
        }

        public void setDniLetra(char dniLetra) {
            this.dniLetra = dniLetra;
        }
    }
    
    public static boolean ObtenerNIF(int dniNumero, char dniLetra){
        char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int resto = dniNumero % 23;   
        char resultado = letras[resto];
        if(resultado==dniLetra){
            return true;
        }
        return false;
        
    }
    
}
