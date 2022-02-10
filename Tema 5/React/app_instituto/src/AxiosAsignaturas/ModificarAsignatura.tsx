import React, { useRef } from 'react'
import axios from 'axios';
export default function ModificarAsignatura() {

   const idAsignatura = useRef<HTMLInputElement>(null);
   const nombreAsignatura = useRef<HTMLInputElement>(null);
   const cursoAsignatura = useRef<HTMLInputElement>(null);
   const modificarAsignaturaApi = (event: React.FormEvent<HTMLFormElement>) => {

      event.preventDefault();
      let formulario: HTMLFormElement = event.currentTarget;

      let id = idAsignatura.current?.value;
      let nombre = nombreAsignatura.current?.value;
      let curso = cursoAsignatura.current?.value;

      const newAsignatura = {
         "id": id,
         "nombre": nombre,
         "curso": curso
      }
      let ruta = "http://localhost:8080/api/v1/asignaturas";
      const axiosput = async (rutaDeAsignatura: string) => {
         try {
            const { data } = await axios.put(rutaDeAsignatura + "/" + newAsignatura.id, newAsignatura)
            console.log(data);
         } catch (error) {
            console.log(error);
         }
      }
      axiosput(ruta);
   }
   return (
      <>
         <form onSubmit={modificarAsignaturaApi}>
            Id: <input type="number" ref={idAsignatura} /><br />
            Nombre: <input type="text" ref={nombreAsignatura} /><br />
            Curso: <input type="text" ref={cursoAsignatura} /> <br />
            <button type="submit">Modificar </button>
         </form>
      </>
   )
}