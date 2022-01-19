const fs = require('fs');
function getTabla( tabla){
let limite = 200;
let primos = [];
let respuesta =
`---------------------------
NUMEROS PRIMOS POR DEBAJO DE ${tabla}
---------------------------
`;

for (let i = 2; i <= limite; i++) {
    if(esPrimo(i)){
        primos.push(i);
        //respuesta += `${i} \n`;
    }
}

for(let i = 0; i <= primos.length; i++){
    respuesta += `${primos[i]} \n`;
}
return respuesta;
}

function esPrimo(numero){
    for(let i=2; i*i<=numero; i++){
        if(numero % i ==0){
            return false;
        }
    }        
    return true;   
}

fs.writeFile('primos.txt',getTabla(200),(err)=>{
    if(err){
        console.log("No se ha podido grabar");
    }
    else{
        console.log("se ha grabado");
    }      
}
);