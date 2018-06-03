package com.erpsystem.entity.producent;

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
public class ProducentController {

  private static final String REL = "all producents";
  private LinkBuilder parentLinkBuilder;
  private Link parentLink;

  @Autowired
  private ProducentService service;
  
  @PostConstruct
  private void init() {
    parentLinkBuilder = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).findAll());
    parentLink = parentLinkBuilder.withRel(REL);
  }

  @PostMapping(path = "/producents")
  public ResponseEntity<Object> create(@RequestBody Producent producent) {

    Producent savedProducent = service.save(producent);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedProducent.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(path = "/producents")
  public Resources<Resource<Producent>> findAll() {
    List<Producent> producents = service.findAll();
    List<Resource<Producent>> resourceList = new LinkedList<>();
    for (Producent producent : producents) {
      Resource<Producent> resource = new Resource<>(producent);
      resource.add(parentLinkBuilder.slash(producent.getId()).withSelfRel());
      resourceList.add(resource);
    }
    return new Resources<>(resourceList);
  }

  @GetMapping(path = "/producents/{id}")
  public Resource<Producent> findById(@PathVariable String id) {
    return new ResourceBuilder<>(service.findById(id), id).link(parentLink).build();
  }

  @PutMapping(path = "/producents/{id}")
  public Resource<Producent> updateById(@PathVariable String id, @RequestBody Producent producent) {
    producent.setId(id);
    return new ResourceBuilder<>(service.overwrite(producent), id).link(parentLink).build();
  }

  @DeleteMapping(path = "/producents/{id}")
  public Resource<Producent> deleteById(@PathVariable String id) {
    return new ResourceBuilder<>(service.deleteById(id), id).link(parentLink).build();
  }
}
