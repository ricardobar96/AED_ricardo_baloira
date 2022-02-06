import React, { useRef } from 'react'
import axios from 'axios';
export default function CreateMatricula() {

const dniMatricula = useRef<HTMLInputElement>(null);
const yearMatricula = useRef<HTMLInputElement>(null);

 const agregarMatriculaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let dni = dniMatricula.current?.value;
 let year = yearMatricula.current?.value;

 const newMatricula = {
 "dni": dni,
 "year": year
 }
 let ruta = "http://localhost:8080/api/v1/matriculas";
 const axiospost = async(rutaDeMatricula:string)=>{
 try{
 const { data } = await axios.post(rutaDeMatricula, newMatricula )
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiospost(ruta);
 }
 return (
 <>
 <form onSubmit={agregarMatriculaApi}>
 Dni: <input type="text" ref={dniMatricula} /><br />
 Año: <input type="text" ref={yearMatricula} /> <br />
 <button type="submit">Crear </button>
 </form>
 </>
 )
}