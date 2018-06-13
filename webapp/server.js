var http = require('http');
var path = require('path');
var fs = require('fs');

http.createServer(function (request, response) {
    var filepath = "." + request.url;
    var extname = path.extname(filepath);
    var contentType = 'text/html';
    switch (extname) {
        case '.js':
            contentType = 'text/javascript';
            break;
        case '.css':
            contentType = 'text/css';
            break;
        case '.json':
            contentType = 'application/json';
            break;
        case '.png':
            contentType = 'image/png';
            break;
        case '.jpg':
            contentType = 'image/jpg';
            break;
        case '.wav':
            contentType = 'audio/wav';
            break;
    }

    if (~filepath.indexOf('/dist')) {
        fs.readFile(filepath, function (error, content) {
            if (error) {
                response.writeHead(500);
                return response.end();
            }

            response.writeHead(200, { 'Content-Type': 'text/html' });
            response.end(content, 'utf-8');
        });
    }

    fs.readFile('./dist/index.html', function (error, content) {
        response.writeHead(200, { 'Content-Type': 'text/html' });
        response.end(content, 'utf-8');
    });

}).listen(3000, '0.0.0.0');