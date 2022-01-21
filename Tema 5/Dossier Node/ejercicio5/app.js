const {manejarFichero} = require("./utils/manejarFichero");
const {tabla} = require("./modelo/tabla");

manejarFichero("tabla.txt", tabla(7) )
    .then(console.log("ok grabado"))
    .catch(err=> console.log(err));