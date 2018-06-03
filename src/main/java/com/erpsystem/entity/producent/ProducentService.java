package com.erpsystem.entity.producent;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProducentService {

    @Autowired
    private ProducentRepository repository;

    public Producent save(Producent producent){
        repository.save(producent);
        return producent;
    }

    public List<Producent> findAll() {
        return repository.findAll();
    }

    public Producent findById(String id){
        return repository.findById(id).orElse(null);
    }

    public Producent overwrite(Producent producent){
        Producent overwrittenProducent = repository.findById(producent.getId()).orElse(null);
        if(overwrittenProducent != null){
            repository.save(producent);
        }
        return overwrittenProducent;
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public Producent deleteById(String id) {
        Producent producent = repository.findById(id).orElse(null);
        repository.deleteById(id);
        return producent;
    }
}
