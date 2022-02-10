import React, { useRef } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


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
        asignaturas: any;
        alumno: [Asignatura];
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
        let asignaturas:any = asignaturasMatricula.current?.value;

        var arrayAsig:[] = asignaturas.split(',');

        var asignaturasEncontradas:any = [];

        for (let i = 0; i<arrayAsig.length; i++) {
            let rutaDeAsignatura = "http://localhost:8080/api/v1/asignaturas/";
            let { data } = await axios.get(rutaDeAsignatura + arrayAsig[i]);
            let asignaturaActual: Instituto.Asignatura = data;
            asignaturasEncontradas.push(asignaturaActual);
          }

        let rutaDeAlumno = "http://localhost:8080/api/v1/alumnos/";
        let { data } = await axios.get(rutaDeAlumno + dni);
        let alumnoExistente: Instituto.Alumno = data;

        const newMatricula = {
            "alumno": alumnoExistente,
            "year": year,
            "asignaturas": asignaturasEncontradas
        }
        let ruta = "http://localhost:8080/api/v1/matriculas";
            try {
                const { data } = await axios.post(ruta, newMatricula)
                console.log(data);
            } catch (error) {
                console.log(error);

            }
        navigate("/matriculas");
    }
    return (
        <>
            <form onSubmit={agregarMatriculaApi}>
                DNI Alumno: <input type="text" ref={dniMatricula} /><br />
                Año: <input type="text" ref={yearMatricula} /> <br />
                Id Asignaturas (separados por ","): <input type="text" ref={asignaturasMatricula} /> <br />
                <button type="submit">Crear </button>
            </form>
        </>
    )
}