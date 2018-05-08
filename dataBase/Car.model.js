var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var CarSchema = new Shema ({
    _id = String,
    name: String
})

module.exports = mongoose.model('CarModel', CarModelSchema)