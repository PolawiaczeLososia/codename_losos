package com.erpsystem.entity.car;

import com.erpsystem.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CarController {

    @Autowired
    private CarService service;

    private Resource<Car> makeHateoasResource(Car car, String rel){
        var resource = new Resource<>(car);
        var link = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).findAll());
        resource.add(link.withRel(rel));
        return resource;
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
    public List<Car> findAll() {
        return service.findAll();
    }

    @GetMapping(path = "/cars/{id}")
    public Resource<Car> findById(@PathVariable String id) {
        var car = service.findById(id);
        if (car == null) {
            throw new ObjectNotFoundException(String.format("Car with name: %s does not exist", id));
        } else {
            return makeHateoasResource(car, "all-cars");
        }
    }

    @PutMapping(path="/cars/{id}")
    public Resource<Car> updateById(@PathVariable String id, @RequestBody Car car){
        car.setId(id);
        var overwrittenCar = service.overwrite(car);
        if (overwrittenCar == null) {
            throw new ObjectNotFoundException(String.format("Car with name: %s does not exist", id));
        } else {
            return makeHateoasResource(overwrittenCar, "all-cars");
        }
    }

    @DeleteMapping(path = "/cars/{id}")
    public Resource<Car> deleteById(@PathVariable String id) {
        var car = service.deleteById(id);
        if (car == null) {
            throw new ObjectNotFoundException(String.format("Car with name: %s does not exist", id));
        } else {
            return makeHateoasResource(car, "all-cars");
        }
    }
}
