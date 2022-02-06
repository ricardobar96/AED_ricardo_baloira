import React, { useRef } from 'react'
import axios from 'axios';
export default function CreateAsignatura() {

const nombreAsignatura = useRef<HTMLInputElement>(null);
const cursoAsignatura = useRef<HTMLInputElement>(null);

 const agregarAsignaturaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let nombre = nombreAsignatura.current?.value;
 let curso = cursoAsignatura.current?.value;

 const newAsignatura = {
 "nombre": nombre,
 "curso": curso
 }
 let ruta = "http://localhost:8080/api/v1/asignaturas";
 const axiospost = async(rutaDeAsignatura:string)=>{
 try{
 const { data } = await axios.post(rutaDeAsignatura, newAsignatura )
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiospost(ruta);
 }
 return (
 <>
 <form onSubmit={agregarAsignaturaApi}>
 Nombre: <input type="text" ref={nombreAsignatura} /><br />
 Curso: <input type="text" ref={cursoAsignatura} /> <br />
 <button type="submit">Crear </button>
 </form>
 </>
 )
}