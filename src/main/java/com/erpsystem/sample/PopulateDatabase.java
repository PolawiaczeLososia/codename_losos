package com.erpsystem.sample;

import com.erpsystem.entity.address.Address;
import com.erpsystem.entity.address.AddressService;
import com.erpsystem.entity.car.Car;
import com.erpsystem.entity.car.CarService;
import com.erpsystem.entity.carmodel.CarModel;
import com.erpsystem.entity.carmodel.CarModelService;
import com.erpsystem.entity.client.Client;
import com.erpsystem.entity.client.ClientService;
import com.erpsystem.entity.contact.Contact;
import com.erpsystem.entity.contact.ContactService;
import com.erpsystem.entity.price.Price;
import com.erpsystem.entity.price.PriceService;
import com.erpsystem.entity.producent.Producent;
import com.erpsystem.entity.producent.ProducentService;
import com.erpsystem.entity.product.Product;
import com.erpsystem.entity.product.ProductService;
import com.erpsystem.entity.warehouse.Warehouse;
import com.erpsystem.entity.warehouse.WarehouseService;
import com.erpsystem.entity.warehousesector.WarehouseSector;
import com.erpsystem.entity.warehousesector.WarehouseSectorService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

@Component
public class PopulateDatabase {

  @Autowired
  private AddressService addressService;
  @Autowired
  private CarService carService;
  @Autowired
  private CarModelService carModelService;
  @Autowired
  private ClientService clientService;
  @Autowired
  private ContactService contactService;
  @Autowired
  private PriceService priceService;
  @Autowired
  private ProducentService producentService;
  @Autowired
  private ProductService productService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private WarehouseSectorService warehouseSectorService;

  public void resetDatabase() {
    addressService.deleteAll();
    carService.deleteAll();
    carModelService.deleteAll();
    clientService.deleteAll();
    contactService.deleteAll();
    priceService.deleteAll();
    producentService.deleteAll();
    productService.deleteAll();
    warehouseService.deleteAll();
    warehouseSectorService.deleteAll();
  }

  public void populateDatabaseWithSampleData() throws ParseException {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    var car1 = carService.save(new Car("Mercedes"));
    var car2 = carService.save(new Car("BMW"));
    var car3 = carService.save(new Car("Ford"));

    var carModel1 = carModelService.save(new CarModel("Benz", "1995", car1));
    var carModel2 = carModelService.save(new CarModel("Z3", "1981", car2));
    var carModel3 = carModelService.save(new CarModel("Shelby", "1992", car3));

    var address2 = addressService.save(new Address("Warsaw", "02-036", "13",
        0, true));
    var address3 = addressService.save(new Address("Ostrov", "07-300", "69",
        3, true));

    var contact1 = contactService.save(new Contact("Some CEO guy", "23", true));
    var contact2 = contactService.save(new Contact("Yet another CEO", "69", true));
    var contact3 = contactService.save(new Contact("Sales engineer", "234", true));

    Contact contacts2[] = {contact1, contact3};
    Contact contacts3[] = {contact1, contact2, contact3};

    var client2 = clientService.save(new Client("J-labs", "234 5435 654", "5634542356",
        "89342543242", "Witold", "Zieba", true, address2,
        new ArrayList<Contact>(Arrays.asList(contacts2))));
    var client3 = clientService.save(new Client("X-com", "324 3424 454", "04790290834",
        "87923847982", "Witkacy", "Mrozek", true, address3,
        new ArrayList<Contact>(Arrays.asList(contacts3))));

    var producent1 = producentService.save(new Producent("Elstar"));
    var producent3 = producentService.save(new Producent("Asmet"));
    var producent4 = producentService.save(new Producent("Rolstal"));

    var warehouse1 = warehouseService.save(new Warehouse("Magassino", true));
    var warehouse3 = warehouseService.save(new Warehouse("gran revista don corleone", true));

    var warehouseSector1 = warehouseSectorService.save(new WarehouseSector("A1"));
    var warehouseSector2 = warehouseSectorService.save(new WarehouseSector("A2"));
    var warehouseSector3 = warehouseSectorService.save(new WarehouseSector("B1"));

    var price1 = priceService.save(new Price(40, 34, simpleDateFormat.parse("31/12/2015")));
    var price2 = priceService.save(new Price(234, 215, simpleDateFormat.parse("31/12/2015")));
    var price3 = priceService.save(new Price(1000, 953, simpleDateFormat.parse("31/12/2015")));
    var price4 = priceService.save(new Price(28, 27, simpleDateFormat.parse("31/12/2015")));

    List<CarModel> carModels1 = new ArrayList<>(Arrays.asList(carModel1, carModel2, carModel3));
    List<CarModel> carModels2 = new ArrayList<>(Arrays.asList(carModel1, carModel3));

    List<Price> prices1 = new ArrayList<>(Arrays.asList(price1, price3));
    List<Price> prices2 = new ArrayList<>(Arrays.asList(price2, price4));

    var product1 = productService
        .save(new Product("product 1", 34, true, "", warehouse1, producent1, carModels1, prices1));
    var product2 = productService
        .save(new Product("product 2", 7, true, "", warehouse3, producent4, carModels2, prices2));
  }
}
