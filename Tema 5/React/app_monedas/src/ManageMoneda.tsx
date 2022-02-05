import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import AppMonedas from './Monedas';

interface IState{ moneda ?: AppMonedas }

export default function ManageMoneda() {
    const [stmoneda, setStmoneda] = useState<IState>({});
    const { idmoneda }= useParams();
    
    useEffect(() => {
        const getMoneda = async (monedaid: string|undefined) =>{
            let rutaDeMoneda = "http://localhost:8080/api/v1/monedas/";
            let { data } = await axios.get(rutaDeMoneda + monedaid);
            let moneda:AppMonedas = data;
            console.log(moneda);
            setStmoneda({moneda});
 }
 getMoneda(idmoneda);
 },
 [idmoneda]
 )
 return (
    <div>
    {JSON.stringify(stmoneda.moneda)}
    </div>
    )
   }