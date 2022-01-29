import React, { ChangeEvent } from "react";
interface IProps { }
interface IState { cambioTexto: any }

class MostrarInput extends React.Component<IProps, IState> {

    inputNombre : any;

    constructor(props: IProps) {
        super(props);
        this.state = { cambioTexto: "" };
        this.inputNombre = React.createRef();
    }

    render() {
        const handleChange =  (event:ChangeEvent<HTMLInputElement>) => {
            event.preventDefault();
            let {cambioTexto} = this.state;
            this.setState({ cambioTexto: event.currentTarget.value});
        }

        return (
            <div>
                Nombre: <input type="text"  onChange={handleChange} ref={this.inputNombre} />
                <br/>
                <h5>Has escrito: {this.state.cambioTexto}</h5>
            </div>
        );
    }
}
export default MostrarInput;