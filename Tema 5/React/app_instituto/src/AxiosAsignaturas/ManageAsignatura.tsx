import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import AppAsignaturas from './Asignaturas';

interface IState{ asignatura ?: AppAsignaturas }

declare module Instituto {

    export interface Alumno {
        dni: string;
        nombre: string;
        apellidos: string;
        fechanacimiento: number;
    }
  
    export interface Asignatura {
        idasignatura: number;
        nombre: string;
        curso: string;
    }
  
    export interface Matricula {
      idmatricula: number;
      dni: string;
      year: number;
  }
  
  }

export default function ManageAsignatura() {
    const [stAsignatura, setStAsignatura] = useState<IState>({});
    const { idasignatura }= useParams();
    let lista:any = [stAsignatura, setStAsignatura];
    
    useEffect(() => {
        const getAsignatura = async (idasignatura: string|undefined) =>{
            let rutaDeAsignatura = "http://localhost:8080/api/v1/asignaturas/";
            let { data } = await axios.get(rutaDeAsignatura + idasignatura);
            let asignatura:AppAsignaturas = data;
            let lista = data;
            console.log(asignatura);
            setStAsignatura({asignatura});
 }
 getAsignatura(idasignatura);
 },
 [idasignatura]
 )
 return (
    <>
  <div>
  <h3>Datos de la Asignatura: </h3>
  {JSON.stringify(stAsignatura.asignatura)}
  </div>
  </>
  ); 
 }