import axios from 'axios';
import React from 'react';
interface IProps{ }
interface IState{
 asignaturas ?: Array<Instituto.Asignatura>;
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

class AppAsignatura extends React.Component<IProps, IState>{
 ip: string;
 puerto: number;
 rutaBase: string;
 rutaAsignatura: string;

 constructor(props: IProps){
 super(props);

 this.state = {
 asignaturas: []
 };
 this.ip = "localhost";
 this.puerto = 8080;
 this.rutaBase = "http://" + this.ip + ":" + this.puerto;
 this.rutaAsignatura = this.rutaBase + "/asignaturas";
 }
 render(){
 const {asignaturas} = this.state;
 return (
  <>
  <div>
  <h3>Asignaturas: </h3>
  <ul>
  {
  asignaturas?.map( (a:Instituto.Asignatura) => {
  return (
  <li>Id: {a.idasignatura} || Nombre: {a.nombre} || Curso: {a.curso}</li>
  );
  })
  }
  </ul>
  </div>
  </>
  ); 
 }

 public async componentDidMount(){
  let ruta = this.rutaAsignatura;
  console.log(ruta);
  let respuesta = await axios.get(ruta);
 console.log(respuesta.data);
 this.setState( { asignaturas: respuesta.data });
 }
}

export default AppAsignatura;