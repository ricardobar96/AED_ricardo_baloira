import React from 'react';

class Persona{
    id: number;
    nombre: string|undefined;
    apellido: string|undefined;
    altura: any;
    edad: any;
    peso: any;
    imc: number;

    constructor(id: number, nombre: string, apellido: string, altura: number, edad: number, peso: number, imc: number) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.altura = altura;
        this.edad = edad;
        this.peso = peso;
        this.imc = imc;
    }

    calcularIMC(){
        return this.imc = ((this.peso*this.peso)/this.altura);
    }

}
export default Persona;