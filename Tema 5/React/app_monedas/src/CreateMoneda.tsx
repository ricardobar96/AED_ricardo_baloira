import React, { useRef } from 'react'
import axios from 'axios';
export default function CreateMoneda() {

const nombremoneda = useRef<HTMLInputElement>(null);
const paismoneda = useRef<HTMLInputElement>(null);

 const agregarMonedaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let nombre = nombremoneda.current?.value;
 let pais = paismoneda.current?.value;
 
 const newmoneda = {
 "nombre": nombre,
 "pais": pais
 }
 let ruta = "http://localhost:8080/api/v1/monedas";
 const axiospost = async(rutaDeMoneda:string)=>{
 try{
 const { data } = await axios.post(rutaDeMoneda, newmoneda )
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiospost(ruta);
 }
 return (
 <>
 <form onSubmit={agregarMonedaApi}>
 Nombre: <input type="text" ref={nombremoneda} /><br />
 País: <input type="text" ref={paismoneda} /> <br />
 <button type="submit">Crear </button>
 </form>
 </>
 )
}