#!/usr/bin/env node

var express = require('express');

var logger = require('morgan');
var bodyParser = require('body-parser');
var routes = require('./routes');
var multer = require('multer');
var upload = multer();
var conf = require('./conf/http-endpoint.conf.js');



//var port = (process.env.PORT || 3000);
var port = (process.env.VCAP_APP_PORT || conf.port);

var app = express();

app.use(logger('dev')); /* 'default', 'short', 'tiny', 'dev' */

app.use(bodyParser.urlencoded({
    extended: false
}));
app.use(bodyParser.json());

console.log('registering event routes with express');
app.post('/dummyreceiver', upload.array(), routes.message);

console.log('About to start listening');
app.listen(port);
console.log('Listening on port: ', port);
