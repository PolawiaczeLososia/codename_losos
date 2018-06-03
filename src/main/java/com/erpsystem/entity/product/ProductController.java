package com.erpsystem.entity.product;

import static com.erpsystem.global.RestAssistant.checkIfResourceExists;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.erpsystem.entity.address.Address;
import com.erpsystem.entity.carmodel.CarModel;
import com.erpsystem.entity.price.Price;
import com.erpsystem.entity.producent.Producent;
import com.erpsystem.entity.product.Product;
import com.erpsystem.entity.product.ProductService;
import com.erpsystem.entity.contact.Contact;
import com.erpsystem.entity.warehouse.Warehouse;
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
public class ProductController {

  private final String REL = "all products";
  private LinkBuilder parentLinkBuilder;
  private Link parentLink;

  @PostConstruct
  private void init() {
    parentLinkBuilder = ControllerLinkBuilder.linkTo(methodOn(this.getClass()).findAll());
    parentLink = parentLinkBuilder.withRel(REL);
  }

  @Autowired
  private ProductService service;

  @PostMapping(path = "/products")
  public ResponseEntity<Object> create(@RequestBody Product product) {

    Product savedProduct = service.save(product);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(savedProduct.getId()).toUri();

    return ResponseEntity.created(location).build();
  }

  @GetMapping(path = "/products")
  public Resources<Resource<Product>> findAll() {
    List<Product> products = service.findAll();
    List<Resource<Product>> resourceList = new LinkedList<>();
    for (Product product : products) {
      Resource<Product> resource = new Resource<>(product);
      resource.add(parentLinkBuilder.slash(product.getId()).withSelfRel());
      resourceList.add(resource);
    }
    return new Resources<>(resourceList);
  }

  @GetMapping(path = "/products/{id}")
  public Resource<Product> findById(@PathVariable String id) {
    return new ResourceBuilder<>(service.findById(id), id).link(parentLink).build();
  }

  @GetMapping(path = "/products/{id}/warehouse")
  public Resource<Warehouse> getWarehouse(@PathVariable String id) {
    var product = checkIfResourceExists(service.findById(id), id);
    Resource<Warehouse> resource = new Resource<>(product.getWarehouse());
    resource.add(parentLinkBuilder.slash(product.getId()).withRel(Product.class.getSimpleName()));
    return resource;
  }

  @GetMapping(path = "/products/{id}/producent")
  public Resource<Producent> getProducent(@PathVariable String id) {
    var product = checkIfResourceExists(service.findById(id), id);
    Resource<Producent> resource = new Resource<>(product.getProducent());
    resource.add(parentLinkBuilder.slash(product.getId()).withRel(Product.class.getSimpleName()));
    return resource;
  }

  @GetMapping(path = "/products/{id}/carModels")
  public Resources<CarModel> getCarModels(@PathVariable String id) {
    var product = checkIfResourceExists(service.findById(id), id);
    Resources<CarModel> resources = new Resources<>(product.getCarModels());
    resources.add(parentLinkBuilder.slash(product.getId()).withRel(Product.class.getSimpleName()));
    return resources;
  }

  @GetMapping(path = "/products/{id}/prices")
  public Resources<Price> getPrices(@PathVariable String id) {
    var product = checkIfResourceExists(service.findById(id), id);
    Resources<Price> resources = new Resources<>(product.getPrices());
    resources.add(parentLinkBuilder.slash(product.getId()).withRel(Product.class.getSimpleName()));
    return resources;
  }

  @PutMapping(path = "/products/{id}")
  public Resource<Product> updateById(@PathVariable String id, @RequestBody Product product) {
    product.setId(id);
    return new ResourceBuilder<>(service.overwrite(product), id).link(parentLink).build();
  }

  @DeleteMapping(path = "/products/{id}")
  public Resource<Product> deleteById(@PathVariable String id) {
    return new ResourceBuilder<>(service.deleteById(id), id).link(parentLink).build();
  }
}
