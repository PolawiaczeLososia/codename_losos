package com.erpsystem.entity.simple_item;

import com.erpsystem.entity.simple_item.simple_item.SimpleItem;
import com.erpsystem.exception.ItemNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class SimpleItemController {

    @Autowired
    SimpleItemService service;

    @GetMapping(path="/simple-items")
    public List<SimpleItem> findAll(){
        return service.findAll();
    }

    @GetMapping(path="/simple-items/{id}")
    public Resource<SimpleItem> findById(@PathVariable Integer id){
        var item = service.findById(id);
        if(item==null)
            throw new ItemNotFoundException(String.format("Item with id: %d not found in the database", id));
        else{
            var resource = new Resource<>(item);
            var link = linkTo(methodOn(this.getClass()).findAll());
            resource.add(link.withRel("all-simple-items"));
            return resource;
        }
    }

    @PostMapping(path="/simple-items")
    public ResponseEntity<Object> create(@RequestBody SimpleItem item){
        SimpleItem savedItem = service.save(item);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/simple-items/{id}")
    public void deleteById(@PathVariable Integer id) throws ItemNotFoundException{
        SimpleItem item = service.deleteById(id);
        if(item==null)
            throw new ItemNotFoundException(String.format("Item with id: %d not found in the database", id));
    }

    @PutMapping(path="/simple-items/{id}")
    public void modifyById(@PathVariable Integer id, @RequestBody SimpleItem item){
        item.setId(id);
        SimpleItem modifiedItem = service.overwrite(item);
        if(modifiedItem==null)
            throw new ItemNotFoundException(String.format("Item with id: %d not found in the database", id));

    }
}
