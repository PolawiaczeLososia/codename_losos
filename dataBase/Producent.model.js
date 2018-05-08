var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ProducentSchema = new Schema ({
    _id: String,
    name: String
})


module.exports = mongoose.model('Producent', ProducentSchema)