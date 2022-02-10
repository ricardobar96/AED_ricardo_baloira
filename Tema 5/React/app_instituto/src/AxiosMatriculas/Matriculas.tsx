import axios from 'axios';
import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
interface IProps{}
interface IState{matriculas ?: Array<Instituto.Matricula>;}

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

export const Matriculas = () => {
    const [matriculas,setMatricula] = useState<IState>();
    const ip:string = "localhost";
    const puerto:number = 8080;
    const rutaBase:string = "http://"+ip+":"+puerto;
    const rutaMatriculas:string = rutaBase+"/matriculas"; 

    useEffect(() => {
        const getMatricula = async()=> {
            let ruta = rutaMatriculas;
            console.log(ruta);
            let respuesta = await axios.get(ruta);
            console.log(respuesta.data);
            setMatricula({ matriculas: respuesta.data });
        }
        getMatricula();
    }, []);

    return (
        <>
            <h3>Matrículas:</h3>
            <ul>
                {
                matriculas?.matriculas?.map( (m:Instituto.Matricula) => {
                    return (
                    <Link to={{pathname:"/matricula/" + m.id}}>
                        <li>Id: {m.id} || Año: {m.year}</li>
                    </Link>
                );
            })
            }
            </ul>
            <br/>
            <Link to={{pathname:"/crearMatricula"}}> Crear Matrícula </Link> &nbsp;
        </>
        );
    }
    export default Matriculas;