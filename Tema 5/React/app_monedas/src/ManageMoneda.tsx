import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';
import AppMonedas from './Monedas';
import MonedasF from './MonedasF';

interface IState{ moneda ?: Monedas.Moneda }

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

    useEffect(() => {
        const getMoneda = async (monedaid: string|undefined) =>{
            let rutaDeMoneda = "http://localhost:8080/api/v1/monedas/";
            let { data } = await axios.get(rutaDeMoneda + monedaid);
            let moneda:Monedas.Moneda = data;
            console.log(moneda);
            setStmoneda({moneda});
 }
 getMoneda(idmoneda);
 },
 [idmoneda]
 )
 return (
    <>
  <div>
  <h3>Datos de la moneda: </h3>
  <h4>Id: {stmoneda.moneda?.idmoneda} || Nombre: {stmoneda.moneda?.nombre} || País: {stmoneda.moneda?.pais}</h4>
  <br/>
  <h5>Históricos:</h5> 
  {stmoneda.moneda?.historicos?.map((h: Monedas.Historico)=>{
      return (
            <li>Id: {h.idhistoricocambioeuro} || Equivalencia: {h.equivalenteeuro} || Fecha: {h.fecha} </li>
    );
  })}
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