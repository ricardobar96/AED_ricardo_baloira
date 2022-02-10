import React, { useState, useEffect, useRef } from 'react';
import { Link, Route, BrowserRouter, Routes, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';

interface IState { alumno?: Instituto.Alumno }

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

export default function ManageAlumno() {
  let dniAlumno: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let nombreAlumno: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let apellidosAlumno: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let fechaAlumno: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);

  let navigate = useNavigate();
  const [stAlumno, setStAlumno] = useState<IState>({});
  const { id } = useParams();

  useEffect(() => {
    const getAlumno = async (dni: string | undefined) => {
      let rutaDeAlumno = "http://localhost:8080/api/v1/alumnos/";
      let { data } = await axios.get(rutaDeAlumno + dni);
      let alumno: Instituto.Alumno = data;
      console.log(alumno);
      setStAlumno({ alumno });
    }
    getAlumno(id);
  },
    [id]
  )

  function BorrarAlumnoApi(dniA: string | undefined) {
    const alumno = {
      "id": dniA
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

    navigate("/alumnos");
  }

  function ModificarAlumnoApi() {
    let dni = dniAlumno.current?.value;
    let nombre = nombreAlumno.current?.value;
    let apellidos = apellidosAlumno.current?.value;
    let fecha = fechaAlumno.current?.value;

    const newAlumno = {
      "id": dni,
      "nombre": nombre,
      "apellidos": apellidos,
      "fechanacimiento": fecha
    }
    let ruta = "http://localhost:8080/api/v1/alumnos";
    const axiosput = async (rutaDeAlumno: string) => {
      try {
        const { data } = await axios.put(rutaDeAlumno + "/" + newAlumno.id, newAlumno)
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    }
    axiosput(ruta);

    navigate("/alumnos");
  }

  return (
    <>
      <div>
        <h3>Datos del Alumno: </h3>
        <h4>DNI: <input type="text" ref={dniAlumno} value={stAlumno.alumno?.id} /> - Nombre: <input type="text" ref={nombreAlumno} defaultValue={stAlumno.alumno?.nombre} /> - Apellidos: <input type="text" ref={apellidosAlumno} defaultValue={stAlumno.alumno?.apellidos} /> - Fecha Nacimiento: <input type="text" ref={fechaAlumno} defaultValue={stAlumno.alumno?.fechanacimiento} /></h4>
        <h5>Matrículas:</h5>
        {stAlumno.alumno?.matriculas?.map((m: Instituto.Matricula) => {
          return (
            <Link to={{ pathname: "/matricula/" + m.id}}>
              <li>Id: {m.id} || Año: {m.year}</li>
            </Link>
          );
        })}
        <br />
        <Link to={{ pathname: "/crearMatricula" }}> Agregar Matrícula </Link> &nbsp;
        <br />
        <br />
        <button onClick={ModificarAlumnoApi}>Modificar Alumno</button>
        <br />
        <br />
        <button onClick={() => BorrarAlumnoApi(id)}>Borrar Alumno</button>
      </div>
    </>
  );
}