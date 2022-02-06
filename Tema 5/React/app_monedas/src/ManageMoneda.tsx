import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import AppMonedas from './Monedas';

interface IState{ moneda ?: AppMonedas }

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

export default function ManageMoneda() {
    const [stmoneda, setStmoneda] = useState<IState>({});
    const { idmoneda }= useParams();
    let lista:any = [stmoneda, setStmoneda];
    
    useEffect(() => {
        const getMoneda = async (monedaid: string|undefined) =>{
            let rutaDeMoneda = "http://localhost:8080/api/v1/monedas/";
            let { data } = await axios.get(rutaDeMoneda + monedaid);
            let moneda:AppMonedas = data;
            let lista = data;
            console.log(moneda);
            setStmoneda({moneda});
            //lista = JSON.stringify(stmoneda.moneda);
 }
 getMoneda(idmoneda);
 },
 [idmoneda]
 )
 return (
    <>
  <div>
  <h3>Datos de la moneda: </h3>
  {JSON.stringify(stmoneda.moneda)}
  <br/>
  <br/>
  <Link to={{pathname:"/moneda/" + idmoneda + "/modificarmoneda"}}> Modificar Moneda </Link> &nbsp;
  <br/>
  <br/>
  <Link to={{pathname:"/moneda/" + idmoneda + "/borrarmoneda"}}> Borrar Moneda </Link> &nbsp;
  </div>
  </>
  ); 
 }