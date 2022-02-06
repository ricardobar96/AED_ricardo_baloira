import axios from 'axios';
import React, { useState, useEffect } from 'react';
interface IProps{}
interface IState{monedas ?: Array<Monedas.Moneda>;}

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

export const MonedasF = () => {
    const [monedas,setMoneda] = useState<IState>();
    const ip:string = "localhost";
    const puerto:number = 8080;
    const rutaBase:string = "http://"+ip+":"+puerto;
    const rutaMonedas:string = rutaBase+"/monedas"; 

    useEffect(() => {
        const getMoneda = async()=> {
            let ruta = rutaMonedas;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setMoneda({ monedas: respuesta.data });
        }

    }, []);

    return (
        <>
            <h3>Monedas:</h3>
            <div>
                 {JSON.stringify(monedas)}
            </div>
        </>
    );
    }
    export default MonedasF;

