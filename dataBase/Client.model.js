var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var ClientSchema = new Schema(
    {
        name: String,
        nip: String,
        regon: String,
        pesel: String,
        firstName: String,
        lastName: String,
        isActive: Boolean,
        address: {
            city: String,
            zipCode: String,
            housNumber: String,
            flatNumber: Number,
            isActive: Boolean
        },
        contacts: [{
            name: String,
            value: String,
            isActive: Boolean
        }]  
    }
)
module.exports = mongoose.model('Client', ClientSchema);
/*


mongoose.model('Client');

var clientSchema = new ClientSchema ({regon: })


mongoose.model('Client').create(


    */