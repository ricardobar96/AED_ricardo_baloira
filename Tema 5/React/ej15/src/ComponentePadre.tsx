import React, { MouseEvent, useState  } from 'react';
import { idText } from 'typescript';
import Persona from './Persona';
import PersonaCard from './PersonaCard';
interface IProps { }

interface IState { personas: number }

let arr: Array<Persona>;
let persona;

class ComponentePadre extends React.Component<IProps, IState> {

    constructor(props: IProps) {
        super(props);
        this.state = { personas: 0 };
        arr = [];
        //key={this.state.personas}
    }

    render() {
        const generar = (event: MouseEvent<HTMLButtonElement>) => {
            event.preventDefault();
            let { personas } = this.state;
            this.setState({ personas: personas + 1 });
            let id = this.state.personas + 1;
            persona = new Persona(id, "", "", 0, 0, 0);
            arr.push(persona);
        }
    
        return (
            <div>
                <button onClick={generar} > + </button>
                Personas creadas: {arr.length}
                {arr.map((n) =>{
                    return <PersonaCard/>
                })}
            </div>
        );
    }
}
export default ComponentePadre;