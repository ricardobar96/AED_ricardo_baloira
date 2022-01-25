import React from "react";
const ComponenteApp = () => {
    const datos = ["Ricardo", "Baloira Armas", "2º DAM"];
    return  (
    <>
        <h1>Mis datos:</h1>
        <h4>{JSON.stringify(datos)}</h4>
    </>
    );
}
        
export default ComponenteApp;
