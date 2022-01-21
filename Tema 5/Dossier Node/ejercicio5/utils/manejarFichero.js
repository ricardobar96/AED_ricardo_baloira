const fs = require('fs');


async function escribirFichero(fichero, data){
    fs.writeFileSync(fichero, data);
}

exports.manejarFichero = escribirFichero;