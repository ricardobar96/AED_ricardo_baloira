import axios from 'axios';
import React from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps{ }
interface IState{
 monedas ?: Array<Monedas.Moneda>;
}

declare module Monedas {

  export interface Historico {
      idhistoricocambioeuro: number;
      fecha: string;
      equivalenteeuro: number;
  }

  export interface Moneda {
      idmoneda: number;
      nombre: string;
      pais: string;
      historicos: Historico[];
  }

}

class AppMonedas extends React.Component<IProps, IState>{
 ip: string;
 puerto: number;
 rutaBase: string;
 rutaMonedas: string;

 constructor(props: IProps){
 super(props);

 this.state = {
 monedas: []
 };
 this.ip = "localhost";
 this.puerto = 8080;
 this.rutaBase = "http://" + this.ip + ":" + this.puerto;
 this.rutaMonedas = this.rutaBase + "/monedas";
 }
 render(){
 const {monedas} = this.state;
 return (
  <>
  <div>
  <h3>Monedas: </h3>
  <ul>
  {
  monedas?.map( (m:Monedas.Moneda) => {
  return (
    <Link to={{pathname:"/moneda/" + m.idmoneda}}>
  <li>Id: {m.idmoneda} || Nombre: {m.nombre} || País: {m.pais} </li>
   </Link>
  );
  })
  }
  </ul>
  </div>
  </>
  ); 
 }

 public async componentDidMount(){
  let ruta = this.rutaMonedas;
  console.log(ruta);
  let respuesta = await axios.get(ruta);
 console.log(respuesta.data);
 this.setState( { monedas: respuesta.data });
 }
}

export default AppMonedas;