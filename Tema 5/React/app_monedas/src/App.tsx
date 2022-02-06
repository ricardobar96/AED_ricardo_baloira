import React, { useState } from 'react';
import { Link, Route, BrowserRouter, Routes } from 'react-router-dom';
import AppMonedas from './Monedas';
import MonedasF from './MonedasF';
import ManageMoneda from './ManageMoneda';
import CreateMoneda from './CreateMoneda';
import BorrarMoneda from './BorrarMoneda';
import ModificarMoneda from './ModificarMoneda';

interface IProps { }
interface IState { }

 const App = () => {
    
    return (
      <BrowserRouter>
        <h1>Aplicación Monedas</h1>
        <Navbar />
        <Routes>
          <Route path="/monedas" element={<AppMonedas />} />
          <Route path="/moneda/:idmoneda" element={<ManageMoneda />} />
          <Route path="/crearmoneda" element={<CreateMoneda />} />
          <Route path="/moneda/:idmoneda/borrarmoneda" element={<BorrarMoneda />} />
          <Route path="/moneda/:idmoneda/modificarmoneda" element={<ModificarMoneda />} />
        </Routes>
      </BrowserRouter>
    );
  }

const Navbar = () =>{

  return (
    <nav>
      <Link to="/"> Inicio </Link> &nbsp;
      <Link to="/monedas"> Monedas </Link> &nbsp;
      <Link to="/crearmoneda"> Crear Moneda </Link> &nbsp;
      <Link to="/moneda/:idmoneda/borrarmoneda"> Borrar Moneda </Link> &nbsp;
      <Link to="/moneda/:idmoneda/modificarmoneda"> Modificar Moneda </Link> &nbsp;
    </nav>
  );
}
export default App;