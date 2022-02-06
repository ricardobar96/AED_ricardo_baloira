import axios from 'axios';
import React from 'react';
interface IProps{ }
interface IState{
 matriculas ?: Array<Instituto.Matricula>;
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

class AppMatriculas extends React.Component<IProps, IState>{
 ip: string;
 puerto: number;
 rutaBase: string;
 rutaMatricula: string;

 constructor(props: IProps){
 super(props);

 this.state = {
 matriculas: []
 };
 this.ip = "localhost";
 this.puerto = 8080;
 this.rutaBase = "http://" + this.ip + ":" + this.puerto;
 this.rutaMatricula = this.rutaBase + "/matriculas";
 }
 render(){
 const {matriculas} = this.state;
 return (
  <>
  <div>
  <h3>Matrículas: </h3>
  <ul>
  {
  matriculas?.map( (m:Instituto.Matricula) => {
  return (
  <li>Id: {m.idmatricula} || Dni: {m.dni} || Año: {m.year}</li>
  );
  })
  }
  </ul>
  </div>
  </>
  ); 
 }

 public async componentDidMount(){
  let ruta = this.rutaMatricula;
  console.log(ruta);
  let respuesta = await axios.get(ruta);
 console.log(respuesta.data);
 this.setState( { matriculas: respuesta.data });
 }
}

export default AppMatriculas;