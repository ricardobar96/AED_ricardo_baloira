import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import AppAlumnos from './Alumnos';
import ManageAlumno from './ManageAlumno';
import CreateAlumno from './CreateAlumno';
import BorrarAlumno from './BorrarAlumno';
import ModificarAlumno from './ModificarAlumno';

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
    </nav>
  );
}
export default App;