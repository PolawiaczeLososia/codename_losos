var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var Client = require('./Client.model');
var Car = require('./Car.model');
var CarModel = require('./CarModel.model');
var Producent = require('./Producent.model');
var Product = require('./Product.model');
var Warehouse = require('./Warehouse.model');



var db = 'mongodb://localhost/warehouse';
mongoose.connect(db)

var port = 8080;

app.listen(port, function(){
    console.log('app listening on port ' + port);
});


