package com.erpsystem.entity.company;

import com.erpsystem.entity.company.company.Company;
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

public class CompanyController {

    @Autowired
    CompanyService service;

    @GetMapping(path="/companies")
    public List<Company> findAll(){
        return service.findAll();
    }

    @GetMapping(path="/companies/{id}")
    public Resource<Company> findById(@PathVariable Integer id){
        var company = service.findById(id);
        if(company==null)
            throw new ItemNotFoundException(String.format("Company with id: %d not found in the database", id));
        else{
            var resource = new Resource<>(company);
            var link = linkTo(methodOn(this.getClass()).findAll());
            resource.add(link.withRel("all-companies"));
            return resource;
        }
    }

    @PostMapping(path="/companies")
    public ResponseEntity<Object> create(@RequestBody Company company){
        Company savedItem = service.save(company);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedItem.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/companies/{id}")
    public void deleteById(@PathVariable Integer id) throws ItemNotFoundException{
        Company company = service.deleteById(id);
        if(company==null)
            throw new ItemNotFoundException(String.format("Company with id: %d not found in the database", id));
    }

    @PutMapping(path="/companies/{id}")
    public void modifyById(@PathVariable Integer id, @RequestBody Company company){
        company.setId(id);
        Company modifiedItem = service.overwrite(company);
        if(modifiedItem==null)
            throw new ItemNotFoundException(String.format("Company with id: %d not found in the database", id));

    }
}
