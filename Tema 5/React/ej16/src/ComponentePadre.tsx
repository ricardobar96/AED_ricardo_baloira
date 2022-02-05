import React, { MouseEvent, useState  } from 'react';
import Persona from './Persona';
import PersonaCard from './PersonaCard';
interface IProps { }

interface IState { personas: Array<Persona> }

let persona;

class ComponentePadre extends React.Component<IProps, IState> {

    constructor(props: IProps) {
        super(props);
        this.state = { personas: new Array<Persona>() };
        //key={this.state.personas}
    }

    render() {
        const generar = (event: MouseEvent<HTMLButtonElement>) => {
            event.preventDefault();
            let { personas } = this.state;
            persona = new Persona(this.state.personas.length+1, "", "", 0, 0, 0, 0);
            let oldArr = [...this.state.personas];
            this.setState({personas: [...oldArr, persona]});
        }

        const modificarPersona = (p:Persona) =>{
            let index = this.state.personas.findIndex((n)=> n.id === p.id);
            let oldArr = [...this.state.personas];
            oldArr[index] = p;
            this.setState({personas: oldArr});
        }
    
        return (
            <div>
                <button onClick={generar} > + </button>
                {this.state.personas.map((n,i) =>{
                    return <PersonaCard key={i+"-card"} persona={n} modificaPersona={modificarPersona}/>
                })}
            </div>
        );
    }
}
export default ComponentePadre;