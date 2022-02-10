import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { Link, useParams, useNavigate } from 'react-router-dom';

interface IState { matricula?: Instituto.Matricula }

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

export default function ManageMatricula() {
  let idRef: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let yearMatricula: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let alumnoMatricula: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let navigate = useNavigate();
  const [stMatricula, setStMatricula] = useState<IState>({});
  const { id } = useParams();

  useEffect(() => {
    const getMatricula = async (id: string | undefined) => {
      let rutaDeMatricula = "http://localhost:8080/api/v1/matriculas/";
      let { data } = await axios.get(rutaDeMatricula + id);
      let matricula: Instituto.Matricula = data;
      console.log(matricula);
      setStMatricula({ matricula });
    }
    getMatricula(id);
  },
    [id]
  )

  function BorrarMatriculaApi(idM: string | undefined) {
    const matricula = {
      "id": idM
    }
    let ruta = "http://localhost:8080/api/v1/matriculas";
    const axiosdelete = async (rutaDeMatricula: string) => {
      try {
        const { data } = await axios.delete(rutaDeMatricula + "/" + matricula.id)
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    }
    axiosdelete(ruta);

    navigate("/matriculas");
  }

  async function ModificarMatriculaApi() {
    let id = idRef.current?.value;
    let dni = alumnoMatricula.current?.value;
    let year = yearMatricula.current?.value;

    let rutaDeAlumno = "http://localhost:8080/api/v1/alumnos/";
    let { data } = await axios.get(rutaDeAlumno + stMatricula.matricula?.alumno.id);
    let alumnoExistente: Instituto.Alumno = data;
        
    const newMatricula = {
      "id": id,
      "alumno": alumnoExistente,
      "year": year
    }
    let ruta = "http://localhost:8080/api/v1/matriculas";
    const axiosput = async (rutaDeMatricula: string) => {
      try {
        const { data } = await axios.put(rutaDeMatricula + "/" + newMatricula.id, newMatricula)
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    }
    axiosput(ruta);

    navigate("/matriculas");

  }
  return (
    <>
      <div>
        <h3>Datos de la Matrícula: </h3>
        <h4>Id: <input type="text" ref={idRef} value={stMatricula.matricula?.id} /> - Año: <input type="number" ref={yearMatricula} defaultValue={stMatricula.matricula?.year} /></h4>
        <h4>Alumno:</h4>
        <li>DNI: {stMatricula.matricula?.alumno.id} || Nombre: {stMatricula.matricula?.alumno.nombre} || Apellidos: {stMatricula.matricula?.alumno.apellidos} || Fecha Nacimiento: {stMatricula.matricula?.alumno.fechanacimiento}</li>
        <h5>Asignaturas:</h5>
        {stMatricula.matricula?.asignaturas?.map((a: Instituto.Asignatura) => {
          return (
            <Link to={{ pathname: "/asignatura/" + a.id }}>
              <li>Id: {a.id} || Nombre: {a.nombre} || Curso: {a.curso}</li>
            </Link>
          );
        })}
        <br />
        <br />
        <button onClick={ModificarMatriculaApi}>Modificar Matrícula</button>
        <br />
        <br />
        <button onClick={() => BorrarMatriculaApi(id)}>Borrar Matrícula</button>
      </div>
    </>
  );
}