import React, { useState } from "react";

const FCContador = (props) => {
    const [contador, incrementar] = useState(1);
    const multiplicar = ()=>{
        if(contador<10){
            incrementar(contador+1);
        }else{
            incrementar(0);
        }
    }
    return (
        <>
            <h1>Tabla del {props.tabla}</h1>
            <p>{props.tabla} * {contador} = {contador * props.tabla}</p>
            <button onClick={multiplicar}>
                {props.tabla} x ?
            </button>
        </>
        );
    }
    export default FCContador;