const fs = require('fs');
function getTabla( tabla){
let limite = 10;
let respuesta =
`---------------------------
TABLA DEL ${tabla}
---------------------------
`;

for (let i = 1; i <= limite; i++) {
    respuesta += `${tabla} * ${i} = ${tabla * i} \n`;
}
return respuesta;
}

fs.writeFile('tabla.txt',getTabla(4),(err)=>{
    if(err){
        console.log("No se ha podido grabar");
    }
    else{
        console.log("se ha grabado");
    }      
}
);