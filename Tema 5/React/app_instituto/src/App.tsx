import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import Alumnos from './AxiosAlumnos/Alumnos';
import ManageAlumno from './AxiosAlumnos/ManageAlumno';
import CreateAlumno from './AxiosAlumnos/CreateAlumno';
import BorrarAlumno from './AxiosAlumnos/BorrarAlumno';
import ModificarAlumno from './AxiosAlumnos/ModificarAlumno';
import AsignaturasF from './AxiosAsignaturas/Asignaturas';
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
          <Route path="/alumnos" element={<Alumnos />} />
          <Route path="/alumno/:dni" element={<ManageAlumno />} />
          <Route path="/crearAlumno" element={<CreateAlumno />} />
          <Route path="/alumno/:dni/borrarAlumno" element={<BorrarAlumno />} />
          <Route path="/alumno/:dni/modificarAlumno" element={<ModificarAlumno />} />

          <Route path="/asignaturas" element={<AsignaturasF />} />
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
      <Link to="/crearAlumno"> Crear Alumno </Link> &nbsp;
      <Link to="/alumno/:dni/borrarAlumno"> Borrar Alumno </Link> &nbsp;
      <Link to="/alumno/:dni/modificarAlumno"> Modificar Alumno </Link> &nbsp;
      <br/>
      <br/>
      <Link to="/asignaturas"> Asignaturas </Link> &nbsp;
      <Link to="/crearAsignatura"> Crear Asignatura </Link> &nbsp;
      <Link to="/asignatura/:idasignatura/borrarAsignatura"> Borrar Asignatura </Link> &nbsp;
      <Link to="/asignatura/:idasignatura/modificarAsignatura"> Modificar Asignatura </Link> &nbsp;
      <br/>
      <br/>
      <Link to="/matriculas"> Matrículas </Link> &nbsp;
      <Link to="/crearMatricula"> Crear Matrícula </Link> &nbsp;
      <Link to="/matricula/:idmatricula/borrarMatricula"> Borrar Matrícula </Link> &nbsp;
      <Link to="/matricula/:idmatricula/modificarMatricula"> Modificar Matrícula </Link> &nbsp;
      <br/>
      <br/>
    </nav>
  );
}
export default App;