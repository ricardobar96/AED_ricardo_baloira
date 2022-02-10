import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import Alumnos from './AxiosAlumnos/Alumnos';
import ManageAlumno from './AxiosAlumnos/ManageAlumno';
import CreateAlumno from './AxiosAlumnos/CreateAlumno';
import BorrarAlumno from './AxiosAlumnos/BorrarAlumno';
import ModificarAlumno from './AxiosAlumnos/ModificarAlumno';
import Asignaturas from './AxiosAsignaturas/Asignaturas';
import CreateAsignatura from './AxiosAsignaturas/CreateAsignatura';
import BorrarAsignatura from './AxiosAsignaturas/BorrarAsignatura';
import ModificarAsignatura from './AxiosAsignaturas/ModificarAsignatura';
import ManageAsignatura from './AxiosAsignaturas/ManageAsignatura';
import Matriculas from './AxiosMatriculas/Matriculas';
import CreateMatricula from './AxiosMatriculas/CreateMatricula';
import BorrarMatricula from './AxiosMatriculas/BorrarMatricula';
import ModificarMatricula from './AxiosMatriculas/ModificarMatricula';
import ManageMatricula from './AxiosMatriculas/ManageMatricula';
import Inicio from './Inicio';

interface IProps { }
interface IState { }

const App = () => {
//<Route path="/" element={<div><Alumnos /><Asignaturas /></div>} />
  return (
    <BrowserRouter>
      <h1>Aplicación Instituto</h1>
      <Navbar />
      <Routes>
        <Route path="/" element={<Inicio/>} />
        <Route path="/alumnos" element={<Alumnos />} />
        <Route path="/alumno/:id" element={<ManageAlumno />} />
        <Route path="/crearAlumno" element={<CreateAlumno />} />
        <Route path="/alumno/:id/borrarAlumno" element={<BorrarAlumno />} />
        <Route path="/alumno/:id/modificarAlumno" element={<ModificarAlumno />} />

        <Route path="/asignaturas" element={<Asignaturas />} />
        <Route path="/crearAsignatura" element={<CreateAsignatura />} />
        <Route path="/asignatura/:id/borrarAsignatura" element={<BorrarAsignatura />} />
        <Route path="/asignatura/:id/modificarAsignatura" element={<ModificarAsignatura />} />
        <Route path="/asignatura/:id" element={<ManageAsignatura />} />

        <Route path="/matriculas" element={<Matriculas />} />
        <Route path="/crearMatricula" element={<CreateMatricula />} />
        <Route path="/matricula/:id/borrarMatricula" element={<BorrarMatricula />} />
        <Route path="/matricula/:id/modificarMatricula" element={<ModificarMatricula />} />
        <Route path="/matricula/:id" element={<ManageMatricula />} />
      </Routes>
    </BrowserRouter>
  );
}

const Navbar = () => {

  return (
    <nav>
      <Link to="/"> Inicio </Link> &nbsp;
      <br />
      <br />
      <Link to="/alumnos"> Alumnos </Link> &nbsp;
      <Link to="/asignaturas"> Asignaturas </Link> &nbsp;
      <Link to="/matriculas"> Matrículas </Link> &nbsp;
      <br />
      <br />
    </nav>
  );
}
export default App;