var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ProductSchema = new Schema ({
    _warechouseId: String,
    _producentId: String,
    _warehouseSectorId: String,
    _subCategoryId: String,
    _productCarModelId: String,
    quantity: Number,
    isActive: Boolean,
    prices: [{
        sellPrice: Number,
        buyPrice: Number,
        date: Date
    }],
    description: String
    

})

module.exports = mongoose.model('Product', ProductSchema)