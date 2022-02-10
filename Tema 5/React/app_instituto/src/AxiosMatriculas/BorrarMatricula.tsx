import React, { useRef } from 'react'
import axios from 'axios';
export default function BorrarMatricula() {

 const idMatricula = useRef<HTMLInputElement>(null);
 const borrarMatriculaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let id = idMatricula.current?.value;

 const matricula = {
 "id": id
 }
 let ruta = "http://localhost:8080/api/v1/matriculas";
 const axiosdelete = async(rutaDeMatricula:string)=>{
 try{
 const { data } = await axios.delete(rutaDeMatricula +  "/" + matricula.id )
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiosdelete(ruta);
 }
 return (
 <>
 <form onSubmit={borrarMatriculaApi}>
 Id: <input type="number" ref={idMatricula} /><br />
 <button type="submit">Eliminar </button>
 </form>
 </>
 )
}