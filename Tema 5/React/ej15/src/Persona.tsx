import React from 'react';

class Persona{
    id: number;
    nombre: string;
    apellido: string;
    altura: number;
    edad: number;
    peso: number;

    constructor(id: number, nombre: string, apellido: string, altura: number, edad: number, peso: number) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.altura = altura;
        this.edad = edad;
        this.peso = peso;
    }
}
export default Persona;