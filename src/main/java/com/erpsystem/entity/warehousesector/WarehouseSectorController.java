package com.erpsystem.entity.warehousesector;

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
public class WarehouseSectorController {

  private static final String REL = "all warehouse sectors";
  private LinkBuilder parentLinkBuilder;
  private Link parentLink;

  @Autowired
  private WarehouseSectorService service;
  
  @PostConstruct
  private void init() {
    parentLinkBuilder = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).findAll());
    parentLink = parentLinkBuilder.withRel(REL);
  }

  @PostMapping(path = "/warehouseSectors")
  public ResponseEntity<Object> create(@RequestBody WarehouseSector warehouseSector) {

    WarehouseSector savedWarehouseSector = service.save(warehouseSector);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedWarehouseSector.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(path = "/warehouseSectors")
  public Resources<Resource<WarehouseSector>> findAll() {
    List<WarehouseSector> warehouseSectors = service.findAll();
    List<Resource<WarehouseSector>> resourceList = new LinkedList<>();
    for (WarehouseSector warehouseSector : warehouseSectors) {
      Resource<WarehouseSector> resource = new Resource<>(warehouseSector);
      resource.add(parentLinkBuilder.slash(warehouseSector.getId()).withSelfRel());
      resourceList.add(resource);
    }
    return new Resources<>(resourceList);
  }

  @GetMapping(path = "/warehouseSectors/{id}")
  public Resource<WarehouseSector> findById(@PathVariable String id) {
    return new ResourceBuilder<>(service.findById(id), id).link(parentLink).build();
  }

  @PutMapping(path = "/warehouseSectors/{id}")
  public Resource<WarehouseSector> updateById(@PathVariable String id, @RequestBody WarehouseSector warehouseSector) {
    warehouseSector.setId(id);
    return new ResourceBuilder<>(service.overwrite(warehouseSector), id).link(parentLink).build();
  }

  @DeleteMapping(path = "/warehouseSectors/{id}")
  public Resource<WarehouseSector> deleteById(@PathVariable String id) {
    return new ResourceBuilder<>(service.deleteById(id), id).link(parentLink).build();
  }
}
