import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps{}
interface IState{asignaturas ?: Array<Instituto.Asignatura>;}

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

export const Asignaturas = () => {
    const [asignaturas,setAsignatura] = useState<IState>();
    const ip:string = "localhost";
    const puerto:number = 8080;
    const rutaBase:string = "http://"+ip+":"+puerto;
    const rutaAsignaturas:string = rutaBase+"/asignaturas"; 

    useEffect(() => {
        const getAsignatura = async()=> {
            let ruta = rutaAsignaturas;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setAsignatura({ asignaturas: respuesta.data });
        }
        getAsignatura();
    }, []);

    return (
        <>
            <h3>Asignaturas:</h3>
            <ul>
                {
                asignaturas?.asignaturas?.map( (a:Instituto.Asignatura) => {
                    return (
                    <Link to={{pathname:"/asignatura/" + a.idasignatura}}>
                        <li>Id: {a.idasignatura} || Nombre: {a.nombre} || Curso: {a.curso}</li>
                    </Link>
                );
            })
            }
            </ul>
            <br/>
            <Link to={{pathname:"/crearAsignatura"}}> Crear Asignatura </Link> &nbsp;
        </>
        );
    }
    export default Asignaturas;