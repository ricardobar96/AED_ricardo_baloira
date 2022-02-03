import React, { ChangeEvent, useState } from 'react';
import Persona from './Persona';
import ComponentePadre from './ComponentePadre';
interface IProps { }
interface IState { imc: number }

let arr: Array<Persona>;
let persona;
let peso: number;
let altura: number;

const idRecibido = (props: IProps) => {
    return (
        <>
            <p>Datos recibido en el componente: {}</p>
        </>
    );
}

class PersonaCard extends React.Component<IProps, IState> {
    inputApellidos: React.RefObject<HTMLInputElement>;
    inputNombre: React.RefObject<HTMLInputElement>;
    inputAltura: React.RefObject<HTMLInputElement>;
    inputEdad: React.RefObject<HTMLInputElement>;
    inputPeso: React.RefObject<HTMLInputElement>;

    constructor(props: IProps) {
        super(props);
        this.state = { imc: 0 };
        arr = [];
        persona = { Persona };
        this.inputApellidos = React.createRef();
        this.inputNombre = React.createRef();
        this.inputAltura = React.createRef();
        this.inputPeso = React.createRef();
        this.inputEdad = React.createRef();
    }


    render() {
        const handleChange =  (event:ChangeEvent<HTMLInputElement>) => {
            event.preventDefault();
            if(event.currentTarget.name === "peso"){

                let {imc} = this.state;
                peso = event.currentTarget.valueAsNumber;
                console.log("PESO: " + peso + " ALTURA: " + altura);
                this.setState({ imc: peso / (altura*altura)*10000});

                //var alturaN = document.getElementsByName("altura").values;
                //this.setState({ imc: event.currentTarget.valueAsNumber/5});
            }
            if(event.currentTarget.name === "altura"){

                let {imc} = this.state;
                altura = event.currentTarget.valueAsNumber;
                console.log("PESO: " + peso + " ALTURA: " + altura);     
                this.setState({ imc: peso / (altura * altura)*10000});         
            }
        }

        return (
            <div>
                Id: {}
                <tr/>
                Nombre: <input name="nombre" type="text" ref={this.inputNombre} />
                <tr/>
                Apellido: <input name="apellido" type="text" ref={this.inputApellidos} />
                <tr/>
                Altura: <input name="altura" onChange={handleChange} type="number" ref={this.inputAltura} />
                <tr/>
                Edad: <input name="edad" type="number" ref={this.inputEdad} />
                <tr/>
                Peso: <input onChange={handleChange} name="peso" type="number" ref={this.inputPeso} />
                <tr/>
                IMC: {this.state.imc}
            </div>
        );
    }
}
export default PersonaCard;