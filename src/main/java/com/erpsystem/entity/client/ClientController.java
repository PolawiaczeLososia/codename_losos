package com.erpsystem.entity.client;

import static com.erpsystem.global.RestAssistant.checkIfResourceExists;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.erpsystem.entity.address.Address;
import com.erpsystem.entity.contact.Contact;
import com.erpsystem.exception.ObjectNotFoundException;
import com.erpsystem.global.RestAssistant.ResourceBuilder;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Add;
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
public class ClientController {

  private final String REL = "all clients";
  private LinkBuilder parentLinkBuilder;
  private Link parentLink;

  @PostConstruct
  private void init() {
    parentLinkBuilder = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).findAll());
    parentLink = parentLinkBuilder.withRel(REL);
  }

  @Autowired
  private ClientService service;

  @PostMapping(path = "/clients")
  public ResponseEntity<Object> create(@RequestBody Client client) {

    Client savedClient = service.save(client);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedClient.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(path = "/clients")
  public Resources<Resource<Client>> findAll() {
    List<Client> clients = service.findAll();
    List<Resource<Client>> resourceList = new LinkedList<>();
    for (Client client : clients) {
      Resource<Client> resource = new Resource<>(client);
      resource.add(parentLinkBuilder.slash(client.getId()).withSelfRel());
      resourceList.add(resource);
    }
    return new Resources<>(resourceList);
  }

  @GetMapping(path = "/clients/{id}")
  public Resource<Client> findById(@PathVariable String id) {
    return new ResourceBuilder<>(service.findById(id), id).link(parentLink).build();
  }

  @GetMapping(path = "/clients/{id}/address")
  public Resource<Address> getAddress(@PathVariable String id) {
    var client = checkIfResourceExists(service.findById(id), id);
    Resource<Address> resource = new Resource<>(client.getAddress());
    resource.add(parentLinkBuilder.slash(client.getId()).withRel(Client.class.getSimpleName()));
    return resource;
  }

  @GetMapping(path = "/clients/{id}/contacts")
  public Resources<Contact> getContacts(@PathVariable String id) {
    var client = checkIfResourceExists(service.findById(id), id);
    Resources<Contact> resources = new Resources<>(client.getContacts());
    resources.add(parentLinkBuilder.slash(client.getId()).withRel(Client.class.getSimpleName()));
    return resources;
  }

  @PutMapping(path = "/clients/{id}")
  public Resource<Client> updateById(@PathVariable String id, @RequestBody Client client) {
    client.setId(id);
    return new ResourceBuilder<>(service.overwrite(client), id).link(parentLink).build();
  }

  @DeleteMapping(path = "/clients/{id}")
  public Resource<Client> deleteById(@PathVariable String id) {
    return new ResourceBuilder<>(service.deleteById(id), id).link(parentLink).build();
  }
}
