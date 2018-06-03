package com.erpsystem.entity.car;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.erpsystem.global.RestAssistant.ResourceBuilder;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkBuilder;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CarController {

  private static final String REL = "all cars";

  @Autowired
  private CarService service;
  private Link parentLink;
  private LinkBuilder parentLinkBuilder;

  @PostConstruct
  private void init() {
    parentLinkBuilder = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).findAll());
    parentLink = parentLinkBuilder.withRel(REL);
  }

  @PostMapping(path = "/cars")
  public ResponseEntity<Object> create(@RequestBody Car car) {

    Car savedCar = service.save(car);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedCar.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(path = "/cars")
  public Resources<Resource<Car>> findAll() {
    List<Car> cars = service.findAll();
    List<Resource<Car>> resourceList = new LinkedList<>();
    for (Car car : cars) {
      Resource<Car> resource = new Resource<>(car);
      resource.add(parentLinkBuilder.slash(car.getId()).withSelfRel());
      resourceList.add(resource);
    }
    return new Resources<>(resourceList);
  }

  @GetMapping(path = "/cars/{id}")
  public Resource<Car> findById(@PathVariable String id) {
    return new ResourceBuilder<>(service.findById(id), id).link(parentLink).build();
  }

  @PutMapping(path = "/cars/{id}")
  public Resource<Car> updateById(@PathVariable String id, @RequestBody Car car) {
    car.setId(id);
    return new ResourceBuilder<>(service.overwrite(car), id).link(parentLink).build();
  }

  @DeleteMapping(path = "/cars/{id}")
  public Resource<Car> deleteById(@PathVariable String id) {
    return new ResourceBuilder<>(service.deleteById(id), id).link(parentLink).build();
  }
}