import React from 'react'
import axios from 'axios';
export default function ModificarMoneda() {
 const modificarMonedaApi = (event:React.FormEvent<HTMLFormElement>) =>{

 event.preventDefault();
 let formulario: HTMLFormElement = event.currentTarget;

 let inputidmoneda: HTMLInputElement|any = formulario.id;
 let inputnombremoneda: HTMLInputElement = formulario.nombremoneda;
 let inputpaismoneda: HTMLInputElement = formulario.paismoneda;

 let id:number = inputidmoneda.value;
 let nombre:string = inputnombremoneda.value;
 let pais:string = inputpaismoneda.value;
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
 Id: <input type="number" name="id" /><br />
 Nombre: <input type="text" name="nombremoneda" /><br />
 País: <input type="text" id="paismoneda" /> <br />
 <button type="submit">Modificar </button>
 </form>
 </>
 )
}