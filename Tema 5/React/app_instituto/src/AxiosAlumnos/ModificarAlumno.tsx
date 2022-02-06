import React, { useRef } from 'react'
import axios from 'axios';
export default function ModificarAlumno() {
 
const dniAlumno = useRef<HTMLInputElement>(null);
const nombreAlumno = useRef<HTMLInputElement>(null);
const apellidosAlumno = useRef<HTMLInputElement>(null);
const fechaAlumno = useRef<HTMLInputElement>(null);
 const modificarAlumnoApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let dni = dniAlumno.current?.value;
 let nombre = nombreAlumno.current?.value;
 let apellidos = apellidosAlumno.current?.value;
 let fecha = fechaAlumno.current?.value;

 const newAlumno = {
    "dni": dni,
    "nombre": nombre,
    "apellidos": apellidos,
    "fechanacimiento": fecha
 }
 let ruta = "http://localhost:8080/api/v1/alumnos";
 const axiosput = async(rutaDeAlumno:string)=>{
 try{
 const { data } = await axios.put(rutaDeAlumno + "/" + newAlumno.dni, newAlumno)
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiosput(ruta);
 }
 return (
 <>
 <form onSubmit={modificarAlumnoApi}>
 DNI: <input type="text" ref={dniAlumno} /><br />
 Nombre: <input type="text" ref={nombreAlumno} /><br />
 Apellidos: <input type="text" ref={apellidosAlumno} /> <br />
 Fecha Nacimiento: <input type="number" ref={fechaAlumno} /> <br />
 <button type="submit">Modificar </button>
 </form>
 </>
 )
}