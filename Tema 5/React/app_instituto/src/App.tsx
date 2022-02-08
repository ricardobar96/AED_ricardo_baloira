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

interface IProps { }
interface IState { }

 const App = () => {
    
    return (
      <BrowserRouter>
        <h1>Aplicación Instituto</h1>
        <Navbar />
        <Routes>
        <Route path="/" element={<div><Alumnos/><Asignaturas/></div>}/>
          <Route path="/alumnos" element={<Alumnos />} />
          <Route path="/alumno/:dni" element={<ManageAlumno />} />
          <Route path="/crearAlumno" element={<CreateAlumno />} />
          <Route path="/alumno/:dni/borrarAlumno" element={<BorrarAlumno />} />
          <Route path="/alumno/:dni/modificarAlumno" element={<ModificarAlumno />} />

          <Route path="/asignaturas" element={<Asignaturas />} />
          <Route path="/crearAsignatura" element={<CreateAsignatura />} />
          <Route path="/asignatura/:idasignatura/borrarAsignatura" element={<BorrarAsignatura />} />
          <Route path="/asignatura/:idasignatura/modificarAsignatura" element={<ModificarAsignatura />} />
          <Route path="/asignatura/:idasignatura" element={<ManageAsignatura />} />

          <Route path="/matriculas" element={<Matriculas />} />
          <Route path="/crearMatricula" element={<CreateMatricula />} />
          <Route path="/matricula/:idmatricula/borrarMatricula" element={<BorrarMatricula />} />
          <Route path="/matricula/:idmatricula/modificarMatricula" element={<ModificarMatricula />} />
          <Route path="/matricula/:idmatricula" element={<ManageMatricula />} />
        </Routes>
      </BrowserRouter>
    );
  }

const Navbar = () =>{

  return (
    <nav>
      <Link to="/"> Inicio </Link> &nbsp;
      <br/>
      <br/>
      <Link to="/alumnos"> Alumnos </Link> &nbsp;
      <Link to="/asignaturas"> Asignaturas </Link> &nbsp;
      <Link to="/matriculas"> Matrículas </Link> &nbsp;
      <br/>
      <br/>
    </nav>
  );
}
export default App;