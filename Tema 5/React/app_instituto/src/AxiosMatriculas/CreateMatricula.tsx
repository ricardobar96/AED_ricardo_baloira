import React, { useRef } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


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

export default function CreateMatricula() {
    let navigate = useNavigate();
    const dniMatricula = useRef<HTMLInputElement>(null);
    const yearMatricula = useRef<HTMLInputElement>(null);
    const asignaturasMatricula = useRef<HTMLInputElement>(null);

    const agregarMatriculaApi = async (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let dni = dniMatricula.current?.value;
        let year = yearMatricula.current?.value;
        let asignaturas = asignaturasMatricula.current?.value;

        let rutaDeAlumno = "http://localhost:8080/api/v1/alumnos/";
        let { data } = await axios.get(rutaDeAlumno + dni);
        let alumnoExistente: Instituto.Alumno = data;

        const newMatricula = {
            "alumno": alumnoExistente,
            "year": year,
            "asignaturas": asignaturas
        }
        let ruta = "http://localhost:8080/api/v1/matriculas";
        const axiospost = async (rutaDeMatricula: string) => {
            try {
                const { data } = await axios.post(rutaDeMatricula, newMatricula)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(ruta);

        let dniAl = alumnoExistente.id
        let nombreAl = alumnoExistente.nombre;
        let apellidosAl = alumnoExistente.apellidos;
        let fechaAl = alumnoExistente.fechanacimiento;
        let matriculasAl = newMatricula;

        const newAlumno = {
            "id": dniAl,
            "nombre": nombreAl,
            "apellidos": apellidosAl,
            "fechanacimiento": fechaAl,
            "matriculas": [matriculasAl]
        }

        let rutaAl = "http://localhost:8080/api/v1/alumnos";
        const axiosput = async (rutaDeAlumno: string) => {
            try {
                const { data } = await axios.put(rutaDeAlumno + "/" + newAlumno.id, newAlumno)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(rutaAl);
        navigate("/matriculas");
    }
    return (
        <>
            <form onSubmit={agregarMatriculaApi}>
                DNI Alumno: <input type="text" ref={dniMatricula} /><br />
                Año: <input type="text" ref={yearMatricula} /> <br />
                <button type="submit">Crear </button>
            </form>
        </>
    )
}