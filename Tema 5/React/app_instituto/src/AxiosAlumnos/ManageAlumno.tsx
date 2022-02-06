import React, { useState, useEffect } from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import axios from 'axios';
import { useParams } from 'react-router-dom';

interface IState{ alumno ?: Instituto.Alumno }

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
            let alumno:Instituto.Alumno = data;
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
  <h4>DNI: {stAlumno.alumno?.dni} || Nombre: {stAlumno.alumno?.nombre} || Apellidos: {stAlumno.alumno?.apellidos} || Fecha Nacimiento: {stAlumno.alumno?.fechanacimiento}</h4>
  <br/>
  <br/>
  <Link to={{pathname:"/alumno/" + dni + "/modificarAlumno"}}> Modificar Alumno </Link> &nbsp;
  <br/>
  <br/>
  <Link to={{pathname:"/alumno/" + dni + "/borrarAlumno"}}> Borrar Alumno </Link> &nbsp;
  </div>
  </>
  ); 
 }