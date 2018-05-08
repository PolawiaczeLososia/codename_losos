var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var CarModelSchema = new Schema ({
    _carId: String,
    name:String
})

module.exports = mongoose.model('CarModel', CarModelSchema)
