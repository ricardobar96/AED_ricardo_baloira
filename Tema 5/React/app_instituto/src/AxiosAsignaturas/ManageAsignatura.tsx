import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { useParams, Link, useNavigate } from 'react-router-dom';

interface IState { asignatura?: Instituto.Asignatura }

declare module Instituto {
    export interface Asignatura {
        id: number;
        nombre: string;
        curso: string;
    }
}

export default function ManageAsignatura() {
    let idAsignatura: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let nombreAsignatura: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let cursoAsignatura: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
    let navigate = useNavigate();

    const [stAsignatura, setStAsignatura] = useState<IState>({});
    const { id } = useParams();

    useEffect(() => {
        const getAsignatura = async (id: string | undefined) => {
            let rutaDeAsignatura = "http://localhost:8080/api/v1/asignaturas/";
            let { data } = await axios.get(rutaDeAsignatura + id);
            let asignatura: Instituto.Asignatura = data;
            console.log(asignatura);
            setStAsignatura({ asignatura });
        }
        getAsignatura(id);
    },
        [id]
    )

    function BorrarAsignaturaApi(idA: string | undefined) {
        const asignatura = {
            "id": idA
        }
    
        let ruta = "http://localhost:8080/api/v1/asignaturas";
        const axiosdelete = async (rutaDeAsignatura: string) => {
            try {
                const { data } = await axios.delete(rutaDeAsignatura + "/" + asignatura.id)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosdelete(ruta).then(respuesta =>{
            navigate("/asignaturas")
        });
    }
    
    
    function ModificarAsignaturaApi() { 
        let id = idAsignatura.current?.value;
        let nombre = nombreAsignatura.current?.value;
        let curso = cursoAsignatura.current?.value;
    
        const newAsignatura = {
            "id": id,
            "nombre": nombre,
            "curso": curso
        }
        let ruta = "http://localhost:8080/api/v1/asignaturas";
        const axiosput = async (rutaDeAsignatura: string) => {
            try {
                const { data } = await axios.put(rutaDeAsignatura + "/" + newAsignatura.id, newAsignatura)
                console.log(data);
            } catch (error) {
                console.log(error);
            }
        }
        axiosput(ruta).then(respuesta =>{
            navigate("/asignaturas")
        });
    }

    return (
        <>
            <div>
                <h3>Datos de la Asignatura: </h3>
                <h4>Id: <input type="text" ref={idAsignatura} value={stAsignatura.asignatura?.id} /> - Nombre: <input type="text" ref={nombreAsignatura} defaultValue={stAsignatura.asignatura?.nombre} /> - Curso: <input type="text" ref={cursoAsignatura} defaultValue={stAsignatura.asignatura?.curso} /></h4>
                <br />
                <br />
                <button onClick={ModificarAsignaturaApi}>Modificar Asignatura</button>
                <br />
                <br />
                <button onClick={() => BorrarAsignaturaApi(id)}>Borrar Asignatura</button>
            </div>
        </>
    );
}