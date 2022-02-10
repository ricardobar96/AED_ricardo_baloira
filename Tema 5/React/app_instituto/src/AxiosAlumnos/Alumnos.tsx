import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps{}
interface IState{alumnos ?: Array<Instituto.Alumno>;}

declare module Instituto {

    export interface Alumno {
        id: string;
        nombre: string;
        apellidos: string;
        fechanacimiento: number;
      }
    
      export interface Asignatura {
        id: number;
        nombre: string;
        curso: string;
      }
    
      export interface Matricula {
        id: number;
        year: number;
        asignaturas: Asignatura[];
        alumno: Alumno;
      }
  
  }

export const Alumnos = () => {
    const [alumnos,setAlumno] = useState<IState>();
    const ip:string = "localhost";
    const puerto:number = 8080;
    const rutaBase:string = "http://"+ip+":"+puerto;
    const rutaAlumnos:string = rutaBase+"/alumnos"; 

    useEffect(() => {
        const getAlumno = async()=> {
            let ruta = rutaAlumnos;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setAlumno({ alumnos: respuesta.data });
        }
        getAlumno();
    }, []);

    return (
        <>
            <h3>Alumnos:</h3>
            <ul>
                {
                alumnos?.alumnos?.map( (a:Instituto.Alumno) => {
                    return (
                    <Link to={{pathname:"/alumno/" + a.id}}>
                        <li>DNI: {a.id} || Nombre: {a.nombre} || Apellidos: {a.apellidos} || Fecha Nacimiento: {a.fechanacimiento}</li>
                    </Link>
                );
            })
            }
            </ul>
            <br/>
            <Link to={{pathname:"/crearAlumno"}}> Crear Alumno </Link> &nbsp;
        </>
        );
    }
    export default Alumnos;
