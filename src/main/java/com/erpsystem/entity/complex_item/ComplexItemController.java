package com.erpsystem.entity.complex_item;

import com.erpsystem.entity.complex_item.complex_item.ComplexItem;
import com.erpsystem.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ComplexItemController {

    @Autowired
    ComplexItemService service;

    @GetMapping(path="/complex-items")
    public List<ComplexItem> findAll(){
        return service.findAll();
    }

    @GetMapping(path="/complex-items/{id}")
    public Resource<ComplexItem> findById(@PathVariable Integer id){
        var item = service.findById(id);
        if(item==null)
            throw new ItemNotFoundException(String.format("Item with id: %d not found in the database", id));
        else{
            var resource = new Resource<>(item);
            var link = linkTo(methodOn(this.getClass()).findAll());
            resource.add(link.withRel("all-complex-items"));
            return resource;
        }
    }

    @PostMapping(path="/complex-items")
    public ResponseEntity<Object> create(@RequestBody ComplexItem item){
        ComplexItem savedItem = service.save(item);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/complex-items/{id}")
    public void deleteById(@PathVariable Integer id) throws ItemNotFoundException{
        ComplexItem item = service.deleteById(id);
        if(item==null)
            throw new ItemNotFoundException(String.format("Item with id: %d not found in the database", id));
    }

    @PutMapping(path="/complex-items/{id}")
    public void modifyById(@PathVariable Integer id, @RequestBody ComplexItem item){
        item.setId(id);
        ComplexItem modifiedItem = service.overwrite(item);
        if(modifiedItem==null)
            throw new ItemNotFoundException(String.format("Item with id: %d not found in the database", id));

    }
}
