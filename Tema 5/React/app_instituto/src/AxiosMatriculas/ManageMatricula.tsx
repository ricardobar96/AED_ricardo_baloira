import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import AppMatriculas from './Matriculas';

interface IState{ matricula ?: AppMatriculas }

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

export default function ManageMatricula() {
    const [stMatricula, setStMatricula] = useState<IState>({});
    const { idmatricula }= useParams();
    let lista:any = [stMatricula, setStMatricula];
    
    useEffect(() => {
        const getMatricula = async (idmatricula: string|undefined) =>{
            let rutaDeMatricula = "http://localhost:8080/api/v1/matriculas/";
            let { data } = await axios.get(rutaDeMatricula + idmatricula);
            let matricula:AppMatriculas = data;
            let lista = data;
            console.log(matricula);
            setStMatricula({matricula});
 }
 getMatricula(idmatricula);
 },
 [idmatricula]
 )
 return (
    <>
  <div>
  <h3>Datos de la Matrícula: </h3>
  {JSON.stringify(stMatricula.matricula)}
  </div>
  </>
  ); 
 }