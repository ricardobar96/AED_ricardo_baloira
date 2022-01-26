import React, { useState } from "react";
import FCContador from "./ComponenteApp";

const Tablas = (props) => {
    const nums = [2,3,4,5,6,7,8,9,10];
    
    return(
        <>
            {nums.map( (n =>{
                return <FCContador tabla={n}/>
            }))}   
        </>

    );
}
export default Tablas;