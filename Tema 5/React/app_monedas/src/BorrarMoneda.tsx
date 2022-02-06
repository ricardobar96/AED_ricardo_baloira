import React, { useRef } from 'react'
import axios from 'axios';
export default function BorrarMoneda() {

 const idmoneda = useRef<HTMLInputElement>(null);
 const borrarMonedaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let id = idmoneda.current?.value;

 const moneda = {
 "id": id
 }
 let ruta = "http://localhost:8080/api/v1/monedas";
 const axiosdelete = async(rutaDeMoneda:string)=>{
 try{
 const { data } = await axios.delete(rutaDeMoneda +  "/" + moneda.id )
 console.log(data);
 }catch(error){
 console.log(error);
 }
 }
 axiosdelete(ruta);
 }
 return (
 <>
 <form onSubmit={borrarMonedaApi}>
 Id: <input type="number" ref={idmoneda} /><br />
 <button type="submit">Eliminar </button>
 </form>
 </>
 )
}