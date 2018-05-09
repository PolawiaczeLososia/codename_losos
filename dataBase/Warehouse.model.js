
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var WarehouseSchema = new Schema({
    _id: String,
    name: String,
    isActive: Boolean,

})

module.exports = mongoose.model('Warehouse', WarehouseSchema);