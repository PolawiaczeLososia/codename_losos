package com.erpsystem.context;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.erpsystem.entity.car.CarController;
import com.erpsystem.entity.carmodel.CarModelController;
import com.erpsystem.entity.client.ClientController;
import com.erpsystem.entity.producent.ProducentController;
import com.erpsystem.entity.product.ProductController;
import com.erpsystem.entity.warehouse.WarehouseController;
import com.erpsystem.entity.warehousesector.WarehouseSectorController;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ContextController {

  @RequestMapping
  public Resource<String> getOrdersList() {
    Resource<String> resource = new Resource<>("erpsystem");
    resource.add(linkTo(methodOn(CarController.class).findAll()).withRel("all cars"));
    resource.add(linkTo(methodOn(CarModelController.class).findAll()).withRel("all car models"));
    resource.add(linkTo(methodOn(ClientController.class).findAll()).withRel("all clients"));
    resource.add(linkTo(methodOn(ProducentController.class).findAll()).withRel("all producents"));
    resource.add(linkTo(methodOn(ProductController.class).findAll()).withRel("all products"));
    resource.add(linkTo(methodOn(WarehouseController.class).findAll()).withRel("all warehouses"));
    resource.add(linkTo(methodOn(WarehouseSectorController.class).findAll())
        .withRel("all warehouse sectors"));
    return resource;
  }
}
