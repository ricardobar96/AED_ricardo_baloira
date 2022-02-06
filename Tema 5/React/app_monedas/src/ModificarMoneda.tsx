import React, { useRef } from 'react'
import axios from 'axios';
export default function ModificarMoneda() {
 
 const idmoneda = useRef<HTMLInputElement>(null);
 const nombremoneda = useRef<HTMLInputElement>(null);
 const paismoneda = useRef<HTMLInputElement>(null);
 const modificarMonedaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let id = idmoneda.current?.value;
 let nombre = nombremoneda.current?.value;
 let pais = paismoneda.current?.value;

 const newmoneda = {
 "id": id,
 "nombre": nombre,
 "pais": pais
 }
 let ruta = "http://localhost:8080/api/v1/monedas";
 const axiosput = async(rutaDeMoneda:string)=>{
 try{
 const { data } = await axios.put(rutaDeMoneda + "/" + newmoneda.id, newmoneda)
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiosput(ruta);
 }
 return (
 <>
 <form onSubmit={modificarMonedaApi}>
 Id: <input type="number" ref={idmoneda} /><br />
 Nombre: <input type="text" ref={nombremoneda} /><br />
 País: <input type="text" ref={paismoneda} /> <br />
 <button type="submit">Modificar </button>
 </form>
 </>
 )
}