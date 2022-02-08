import React, { useState, useEffect, useRef } from 'react';
import axios from 'axios';
import { Link, useParams, useNavigate } from 'react-router-dom';

interface IState { matricula?: Instituto.Matricula }

declare module Instituto {

  export interface Alumno {
    dni: string;
    nombre: string;
    apellidos: string;
    fechanacimiento: number;
    matriculas: Matricula[];
  }

  export interface Asignatura {
    idasignatura: number;
    nombre: string;
    curso: string;
  }

  export interface Matricula {
    idmatricula: number;
    year: number;
    asignaturas: Asignatura[];
    alumno: Alumno[];
  }

}

export default function ManageMatricula() {
  let idMatriculaRef: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let yearMatricula: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let alumnoMatricula: React.RefObject<HTMLInputElement> = useRef<HTMLInputElement>(null);
  let navigate = useNavigate();
  const [stMatricula, setStMatricula] = useState<IState>({});
  const { idmatricula } = useParams();

  useEffect(() => {
    const getMatricula = async (idmatricula: string | undefined) => {
      let rutaDeMatricula = "http://localhost:8081/api/v1/matriculas/";
      let { data } = await axios.get(rutaDeMatricula + idmatricula);
      let matricula: Instituto.Matricula = data;
      console.log(matricula);
      setStMatricula({ matricula });
    }
    getMatricula(idmatricula);
  },
    [idmatricula]
  )

  function BorrarMatriculaApi(idM: string | undefined) {
    const matricula = {
      "idmatricula": idM
    }
    let ruta = "http://localhost:8081/api/v1/matriculas";
    const axiosdelete = async (rutaDeMatricula: string) => {
      try {
        const { data } = await axios.delete(rutaDeMatricula + "/" + matricula.idmatricula)
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    }
    axiosdelete(ruta);

    navigate("/matriculas");
  }

  function ModificarMatriculaApi() {
    let id = idMatriculaRef.current?.value;
    let dni = alumnoMatricula.current?.value;
    let year = yearMatricula.current?.value;

    const newMatricula = {
      "idmatricula": id,
      "alumno.dni": dni,
      "year": year
    }
    let ruta = "http://localhost:8081/api/v1/matriculas";
    const axiosput = async (rutaDeMatricula: string) => {
      try {
        const { data } = await axios.put(rutaDeMatricula + "/" + newMatricula.idmatricula, newMatricula)
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
        <h4>Id: <input type="text" ref={idMatriculaRef} value={stMatricula.matricula?.idmatricula} /> - Año: <input type="number" ref={yearMatricula} defaultValue={stMatricula.matricula?.year} /></h4>
        <h5>Asignaturas:</h5>
        {stMatricula.matricula?.asignaturas?.map((a: Instituto.Asignatura) => {
          return (
            <Link to={{ pathname: "/asignatura/" + a.idasignatura }}>
              <li>Id: {a.idasignatura} || Nombre: {a.nombre} || Curso: {a.curso}</li>
            </Link>
          );
        })}
        <br />
        <br />
        <button onClick={ModificarMatriculaApi}>Modificar Matrícula</button>
        <br />
        <br />
        <button onClick={() => BorrarMatriculaApi(idmatricula)}>Borrar Matrícula</button>
      </div>
    </>
  );
}