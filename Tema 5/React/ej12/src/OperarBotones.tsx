import React, { MouseEvent } from "react";
interface IProps { }
interface IState { valor: any }

class OperarBotones extends React.Component<IProps, IState> {

    constructor(props: IProps) {
        super(props);
        this.state = { valor: 400 };
    }

    render() {
        const cambio =  (event:MouseEvent<HTMLButtonElement>) => {
            event.preventDefault();
            let {valor} = this.state;
            if(event.currentTarget.innerText.includes("/")){
                this.setState({ valor: valor/2});
            } 
            if(event.currentTarget.innerText.includes("*")){
                this.setState({ valor: valor*2});
            }           
        }

        return (
            <div>
                <h3>Valor: {this.state.valor}</h3>
                <button onClick={cambio} > {this.state.valor}/2 </button>
                <button onClick={cambio} > {this.state.valor}*2 </button>
            </div>
        );
    }
}
export default OperarBotones;
