import React from "react";
interface IProps { }
interface IState { tiempoRestante: number }

class Crono extends React.Component<IProps, IState> {

    inputTiempo : any;
    estadoBoton : string;
    timerID: number | any;

    constructor(props: IProps) {
        super(props);
        this.state = { tiempoRestante: 0 };
        this.inputTiempo = React.createRef();
        this.estadoBoton = "Iniciar";
    }

    componentWillUnmount() {
        clearInterval(this.timerID);
    }

    alerta() {
        alert("¡Finalizado!");
    }

    tick() { 
        let {tiempoRestante} = this.state;
        this.setState({ tiempoRestante:  tiempoRestante - 1});

        if( this.state.tiempoRestante == 0){
            this.componentWillUnmount();
            this.setState({ tiempoRestante:  0});
            this.estadoBoton = "Iniciar";
            this.alerta();
        }
    }

    render() {
        const iniciarCronometro = () => {
            let nodeInputTiempo = this.inputTiempo.current;
            let valorSegundos = Number(nodeInputTiempo.value);
            this.setState({ tiempoRestante:  valorSegundos});
            this.estadoBoton = "Detener";
            nodeInputTiempo.value = "";

            if(valorSegundos > 0){
                this.timerID = setInterval(
                    () => this.tick(),
                    1000
                    );
            }
            else{
                this.componentWillUnmount();
                this.setState({ tiempoRestante:  0});
                this.estadoBoton = "Iniciar";
                this.alerta();
            }
        }
        return (
            <div>
                <h1>Cronómetro</h1>
                Cantidad segundos: <input type="text" ref={this.inputTiempo} />
                <br /><button onClick={iniciarCronometro} > {this.estadoBoton} </button>
                <h2>Quedan: {this.state.tiempoRestante} segundos.</h2>
            </div>
        );
    }
}
export default Crono;