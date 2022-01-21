const http = require('http');
const url = require('url');

const puerto = 8000;
http.createServer( (req, res) => {
    res.write(`servidor funcionando en ${puerto} `);
    const queryObject = url.parse(req.url,true).query;
    console.log(queryObject);
    let texto = "";
    Object.entries(queryObject)
        .forEach( (par) => texto += (par[0] + ": " + par[1]));
    res.write(` se recibió como parámetro: ${texto}`);
    res.end();
}).listen(puerto);