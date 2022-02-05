import React, { ChangeEvent, useState } from 'react';
import Persona from './Persona';
import ComponentePadre from './ComponentePadre';
interface IProps { persona: Persona, modificaPersona: Function }
interface IState { imc: number }
let arr: Array<Persona>;
let peso: number;
let altura: number;


class PersonaCard extends React.Component<IProps, IState> {
    inputApellidos: React.RefObject<HTMLInputElement>;
    inputNombre: React.RefObject<HTMLInputElement>;
    inputAltura: React.RefObject<HTMLInputElement>;
    inputEdad: React.RefObject<HTMLInputElement>;
    inputPeso: React.RefObject<HTMLInputElement>;
    personaCard:Persona;
    modificadorPersona:Function;
    
    constructor(props: IProps) {
        super(props);
        this.state = { imc: 0 };
        arr = [];
        this.personaCard= this.props.persona;
        this.modificadorPersona = this.props.modificaPersona;
        this.inputApellidos = React.createRef();
        this.inputNombre = React.createRef();
        this.inputAltura = React.createRef();
        this.inputPeso = React.createRef();
        this.inputEdad = React.createRef();
    }


    render() {
        const handleChange =  (event:ChangeEvent<HTMLInputElement>) => {
            event.preventDefault();

            this.personaCard.nombre = this.inputNombre.current?.value;

            this.personaCard.apellido = this.inputApellidos.current?.value;

            this.personaCard.altura = this.inputAltura.current?.valueAsNumber;

            this.personaCard.edad = this.inputEdad.current?.valueAsNumber;

            this.personaCard.peso = this.inputPeso.current?.valueAsNumber;

            this.personaCard.imc = this.personaCard.calcularIMC();

            this.setState({ imc: this.personaCard.calcularIMC()});   

            console.log(this.personaCard.imc);
            /*
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
            }*/

            this.modificadorPersona(this.personaCard);
        }

        return (
            <div>
                Id: {this.personaCard.id}
                <tr/>
                Nombre: <input name="nombre" onChange={handleChange} type="text" ref={this.inputNombre} />
                <tr/>
                Apellido: <input name="apellido" onChange={handleChange} type="text" ref={this.inputApellidos} />
                <tr/>
                Altura: <input name="altura" onChange={handleChange} type="number" ref={this.inputAltura} />
                <tr/>
                Edad: <input name="edad" onChange={handleChange} type="number" ref={this.inputEdad} />
                <tr/>
                Peso: <input onChange={handleChange} name="peso" type="number" ref={this.inputPeso} />
                <tr/>
                IMC: {this.state.imc}
            </div>
        );
    }
}
export default PersonaCard;