import React, { useRef } from 'react'
import axios from 'axios';
export default function ModificarMatricula() {
 
const idMatricula = useRef<HTMLInputElement>(null);
const dniMatricula = useRef<HTMLInputElement>(null);
const yearMatricula = useRef<HTMLInputElement>(null);
 const modificarMatriculaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let id = idMatricula.current?.value;
 let dni = dniMatricula.current?.value;
 let year = yearMatricula.current?.value;

 const newMatricula = {
    "idmatricula": id,
    "dni": dni,
    "year": year
 }
 let ruta = "http://localhost:8080/api/v1/matriculas";
 const axiosput = async(rutaDeMatricula:string)=>{
 try{
 const { data } = await axios.put(rutaDeMatricula + "/" + newMatricula.idmatricula, newMatricula)
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiosput(ruta);
 }
 return (
 <>
 <form onSubmit={modificarMatriculaApi}>
 Id: <input type="number" ref={idMatricula} /><br />
 DNI: <input type="text" ref={dniMatricula} /><br />
 Año: <input type="text" ref={yearMatricula} /> <br />
 <button type="submit">Modificar </button>
 </form>
 </>
 )
}