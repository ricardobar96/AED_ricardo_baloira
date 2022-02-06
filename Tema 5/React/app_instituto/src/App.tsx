import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import AppAlumnos from './AxiosAlumnos/Alumnos';
import ManageAlumno from './AxiosAlumnos/ManageAlumno';
import CreateAlumno from './AxiosAlumnos/CreateAlumno';
import BorrarAlumno from './AxiosAlumnos/BorrarAlumno';
import ModificarAlumno from './AxiosAlumnos/ModificarAlumno';
import AppAsignatura from './AxiosAsignaturas/Asignaturas'
import CreateAsignatura from './AxiosAsignaturas/CreateAsignatura';
import BorrarAsignatura from './AxiosAsignaturas/BorrarAsignatura';
import ModificarAsignatura from './AxiosAsignaturas/ModificarAsignatura';

interface IProps { }
interface IState { }

 const App = () => {
    
    return (
      <BrowserRouter>
        <h1>Aplicación Instituto</h1>
        <Navbar />
        <Routes>
          <Route path="/alumnos" element={<AppAlumnos />} />
          <Route path="/alumno/:dni" element={<ManageAlumno />} />
          <Route path="/crearAlumno" element={<CreateAlumno />} />
          <Route path="/borrarAlumno" element={<BorrarAlumno />} />
          <Route path="/modificarAlumno" element={<ModificarAlumno />} />
          <Route path="/asignaturas" element={<AppAsignatura />} />
          <Route path="/crearAsignatura" element={<CreateAsignatura />} />
          <Route path="/borrarAsignatura" element={<BorrarAsignatura />} />
          <Route path="/modificarAsignatura" element={<ModificarAsignatura />} />
        </Routes>
      </BrowserRouter>
    );
  }

const Navbar = () =>{

  return (
    <nav>
      <Link to="/"> Inicio </Link> &nbsp;
      <br/>
      <Link to="/alumnos"> Alumnos </Link> &nbsp;
      <Link to="/crearAlumno"> Crear Alumno </Link> &nbsp;
      <Link to="/borrarAlumno"> Borrar Alumno </Link> &nbsp;
      <Link to="/modificarAlumno"> Modificar Alumno </Link> &nbsp;
      <br/>
      <Link to="/asignaturas"> Asignaturas </Link> &nbsp;
      <Link to="/crearAsignatura"> Crear Asignatura </Link> &nbsp;
      <Link to="/borrarAsignatura"> Borrar Asignatura </Link> &nbsp;
      <Link to="/modificarAsignatura"> Modificar Asignatura </Link> &nbsp;
    </nav>
  );
}
export default App;