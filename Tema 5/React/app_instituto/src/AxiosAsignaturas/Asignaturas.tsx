import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps{}
interface IState{asignaturas ?: Array<Instituto.Asignatura>;}

declare module Instituto {

    export interface Alumno {
        id: string;
        nombre: string;
        apellidos: string;
        fechanacimiento: number;
        matriculas: Matricula[];
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
                    <Link to={{pathname:"/asignatura/" + a.id}}>
                        <li>Id: {a.id} || Nombre: {a.nombre} || Curso: {a.curso}</li>
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