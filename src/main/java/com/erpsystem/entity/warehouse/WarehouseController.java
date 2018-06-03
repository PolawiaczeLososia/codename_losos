package com.erpsystem.entity.warehouse;

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
public class WarehouseController {

  private static final String REL = "all warehouses";
  private LinkBuilder parentLinkBuilder;
  private Link parentLink;

  @Autowired
  private WarehouseService service;
  
  @PostConstruct
  private void init() {
    parentLinkBuilder = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).findAll());
    parentLink = parentLinkBuilder.withRel(REL);
  }

  @PostMapping(path = "/warehouses")
  public ResponseEntity<Object> create(@RequestBody Warehouse warehouse) {

    Warehouse savedWarehouse = service.save(warehouse);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedWarehouse.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(path = "/warehouses")
  public Resources<Resource<Warehouse>> findAll() {
    List<Warehouse> warehouses = service.findAll();
    List<Resource<Warehouse>> resourceList = new LinkedList<>();
    for (Warehouse warehouse : warehouses) {
      Resource<Warehouse> resource = new Resource<>(warehouse);
      resource.add(parentLinkBuilder.slash(warehouse.getId()).withSelfRel());
      resourceList.add(resource);
    }
    return new Resources<>(resourceList);
  }

  @GetMapping(path = "/warehouses/{id}")
  public Resource<Warehouse> findById(@PathVariable String id) {
    return new ResourceBuilder<>(service.findById(id), id).link(parentLink).build();
  }

  @PutMapping(path = "/warehouses/{id}")
  public Resource<Warehouse> updateById(@PathVariable String id, @RequestBody Warehouse warehouse) {
    warehouse.setId(id);
    return new ResourceBuilder<>(service.overwrite(warehouse), id).link(parentLink).build();
  }

  @DeleteMapping(path = "/warehouses/{id}")
  public Resource<Warehouse> deleteById(@PathVariable String id) {
    return new ResourceBuilder<>(service.deleteById(id), id).link(parentLink).build();
  }
}
