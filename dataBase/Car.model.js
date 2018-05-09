var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var CarSchema = new Schema ({
    _id: String,
    name: String
})

module.exports = mongoose.model('Car', CarSchema)