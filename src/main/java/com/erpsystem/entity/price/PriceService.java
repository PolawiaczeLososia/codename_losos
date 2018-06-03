package com.erpsystem.entity.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceService {

    @Autowired
    private PriceRepository repository;

    public Price save(Price price){
        repository.save(price);
        return price;
    }

    public List<Price> findAll() {
        return repository.findAll();
    }

    public Price findById(String id){
        return repository.findById(id).orElse(null);
    }

    public Price overwrite(Price price){
        Price overwrittenPrice = repository.findById(price.getId()).orElse(null);
        if(overwrittenPrice != null){
            repository.save(price);
        }
        return overwrittenPrice;
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public Price deleteById(String id) {
        Price price = repository.findById(id).orElse(null);
        repository.deleteById(id);
        return price;
    }
}
