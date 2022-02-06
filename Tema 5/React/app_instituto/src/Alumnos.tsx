import axios from 'axios';
import React from 'react';
interface IProps{ }
interface IState{
 alumnos ?: Array<Instituto.Alumno>;
}

declare module Instituto {

  export interface Alumno {
      dni: string;
      nombre: string;
      apellidos: string;
      fechanacimiento: number;
  }

  export interface Asignatura {
      idasignatura: number;
      nombre: string;
      curso: string;
  }

  export interface Matricula {
    idmatricula: number;
    dni: string;
    year: number;
}

}

class AppAlumnos extends React.Component<IProps, IState>{
 ip: string;
 puerto: number;
 rutaBase: string;
 rutaAlumnos: string;

 constructor(props: IProps){
 super(props);

 this.state = {
 alumnos: []
 };
 this.ip = "localhost";
 this.puerto = 8080;
 this.rutaBase = "http://" + this.ip + ":" + this.puerto;
 this.rutaAlumnos = this.rutaBase + "/alumnos";
 }
 render(){
 const {alumnos} = this.state;
 return (
  <>
  <div>
  <h3>Alumnos: </h3>
  <ul>
  {
  alumnos?.map( (a:Instituto.Alumno) => {
  return (
  <li>DNI: {a.dni} || Nombre: {a.nombre} || Apellidos: {a.apellidos} || Fecha nacimiento: {a.fechanacimiento}</li>
  );
  })
  }
  </ul>
  </div>
  </>
  ); 
 }

 public async componentDidMount(){
  let ruta = this.rutaAlumnos;
  console.log(ruta);
  let respuesta = await axios.get(ruta);
 console.log(respuesta.data);
 this.setState( { alumnos: respuesta.data });
 }
}

export default AppAlumnos;