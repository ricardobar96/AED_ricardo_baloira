import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import AppAlumnos from './Alumnos';

interface IState{ alumno ?: AppAlumnos }

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

export default function ManageAlumno() {
    const [stAlumno, setStAlumno] = useState<IState>({});
    const { dni }= useParams();
    let lista:any = [stAlumno, setStAlumno];
    
    useEffect(() => {
        const getAlumno = async (dni: string|undefined) =>{
            let rutaDeAlumno = "http://localhost:8080/api/v1/alumnos/";
            let { data } = await axios.get(rutaDeAlumno + dni);
            let alumno:AppAlumnos = data;
            let lista = data;
            console.log(alumno);
            setStAlumno({alumno});
 }
 getAlumno(dni);
 },
 [dni]
 )
 return (
    <>
  <div>
  <h3>Datos del Alumno: </h3>
  {JSON.stringify(stAlumno.alumno)}
  </div>
  </>
  ); 
 }