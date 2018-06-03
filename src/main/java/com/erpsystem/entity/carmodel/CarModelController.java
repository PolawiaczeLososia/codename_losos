package com.erpsystem.entity.carmodel;

import com.erpsystem.entity.car.Car;
import com.erpsystem.entity.car.CarController;
import com.erpsystem.entity.car.CarService;
import com.erpsystem.exception.ObjectNotFoundException;
import com.erpsystem.global.RestAssistant.ResourceBuilder;
import java.util.LinkedList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CarModelController {

  private static final String REL = "all car models";

  @Autowired
  private CarModelService service;
  @Autowired
  private CarService carService;
  private Link parentLink;
  private LinkBuilder parentLinkBuilder;

  @PostConstruct
  private void init() {
    parentLinkBuilder = linkTo(methodOn(this.getClass()).findAll());
    parentLink = parentLinkBuilder.withRel(REL);
  }


  @PostMapping(path = "/carModels")
  public ResponseEntity<Object> create(@RequestBody CarModel carModel) {
    Car car = carService.findByName(carModel.getCar().getName());
    if (car != null) {
      carModel.setCar(car);
    }
    CarModel savedCarModel = service.save(carModel);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedCarModel.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(path = "/carModels")
  public Resources<Resource<CarModel>> findAll() {
    List<CarModel> carModels = service.findAll();
    List<Resource<CarModel>> resourceList = new LinkedList<>();
    for (CarModel carModel : carModels) {
      Resource<CarModel> resource = new Resource<>(carModel);
      resource.add(parentLinkBuilder.slash(carModel.getId()).withSelfRel());
      resourceList.add(resource);
    }
    return new Resources<>(resourceList);
  }

  @GetMapping(path = "/carModels/{id}")
  public Resource<CarModel> findById(@PathVariable String id) {
    return new ResourceBuilder<>(service.findById(id), id).link(parentLink).build();
  }

  @GetMapping(path = "/carModels/{id}/car")
  public Resource<Car> getCar(@PathVariable String id) {
    var carModel = service.findById(id);
    if (carModel == null) {
      throw new ObjectNotFoundException(String.format("CarModel with id: %s does not exist", id));
    } else {
      var result = new Resource<>(carModel.getCar());
      result.add(linkTo(methodOn(CarController.class).findAll()).slash(carModel.getCar().getId())
          .withSelfRel());
      return result;
    }
  }

  @PutMapping(path = "/carModels/{id}")
  public Resource<CarModel> updateById(@PathVariable String id, @RequestBody CarModel carModel) {
    carModel.setId(id);
    return new ResourceBuilder<>(service.overwrite(carModel), id).link(parentLink).build();
  }

  @DeleteMapping(path = "/carModels/{id}")
  public Resource<CarModel> deleteById(@PathVariable String id) {
    return new ResourceBuilder<>(service.deleteById(id), id).link(parentLink).build();
  }
}