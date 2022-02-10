import React, { useRef } from 'react'
import axios from 'axios';
export default function BorrarAlumno() {

    const idAlumno = useRef<HTMLInputElement>(null);
    const borrarAlumnoApi = (event: React.FormEvent<HTMLFormElement>) => {

        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;

        let id = idAlumno.current?.value;

        const alumno = {
            "id": id
        }
        let ruta = "http://localhost:8080/api/v1/alumnos";
        const axiosdelete = async (rutaDeAlumno: string) => {
            try {
                const { data } = await axios.delete(rutaDeAlumno + "/" + alumno.id)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosdelete(ruta);
    }
    return (
        <>
            <form onSubmit={borrarAlumnoApi}>
                Id: <input type="text" ref={idAlumno} /><br />
                <button type="submit">Eliminar </button>
            </form>
        </>
    )
}