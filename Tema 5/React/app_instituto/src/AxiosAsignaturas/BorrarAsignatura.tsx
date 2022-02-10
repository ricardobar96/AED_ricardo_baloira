import React, { useRef } from 'react'
import axios from 'axios';
export default function BorrarAsignatura() {

 const id = useRef<HTMLInputElement>(null);
 const borrarAsignaturaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let idA = id.current?.value;

 const asignatura = {
 "id": idA
 }
 let ruta = "http://localhost:8080/api/v1/asignaturas";
 const axiosdelete = async(rutaDeAsignatura:string)=>{
 try{
 const { data } = await axios.delete(rutaDeAsignatura +  "/" + asignatura.id )
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiosdelete(ruta);
 }
 return (
 <>
 <form onSubmit={borrarAsignaturaApi}>
 Id: <input type="number" ref={id} /><br />
 <button type="submit">Eliminar </button>
 </form>
 </>
 )
}