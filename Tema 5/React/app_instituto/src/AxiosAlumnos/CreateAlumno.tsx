import React, { useRef } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
export default function CreateAlumno() {
    let navigate = useNavigate();
    const idAlumno = useRef<HTMLInputElement>(null);
    const nombreAlumno = useRef<HTMLInputElement>(null);
    const apellidosAlumno = useRef<HTMLInputElement>(null);
    const fechaAlumno = useRef<HTMLInputElement>(null);

    const agregarAlumnoApi = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        let formulario: HTMLFormElement = event.currentTarget;
        let id = idAlumno.current?.value;
        let nombre = nombreAlumno.current?.value;
        let apellidos = apellidosAlumno.current?.value;
        let fecha = fechaAlumno.current?.value;

        const newAlumno = {
            "id": id,
            "nombre": nombre,
            "apellidos": apellidos,
            "fechanacimiento": fecha
        }
        let ruta = "http://localhost:8080/api/v1/alumnos";
        const axiospost = async (rutaDeAlumno: string) => {
            try {
                const { data } = await axios.post(rutaDeAlumno, newAlumno)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiospost(ruta).then(respuesta =>{
            navigate("/alumnos")
        });
    }
    return (
        <>
            <form onSubmit={agregarAlumnoApi}>
                id: <input type="text" ref={idAlumno} /><br />
                Nombre: <input type="text" ref={nombreAlumno} /><br />
                Apellidos: <input type="text" ref={apellidosAlumno} /> <br />
                Fecha Nacimiento: <input type="number" ref={fechaAlumno} /> <br />
                <button type="submit">Crear </button>
            </form>
        </>
    )
}