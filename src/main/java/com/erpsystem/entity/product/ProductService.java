package com.erpsystem.entity.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product save(Product product){
        repository.save(product);
        return product;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(String id){
        return repository.findById(id).orElse(null);
    }

    public Product overwrite(Product product){
        Product overwrittenProduct = repository.findById(product.getId()).orElse(null);
        if(overwrittenProduct != null){
            repository.save(product);
        }
        return overwrittenProduct;
    }
    public void deleteAll(){
        repository.deleteAll();
    }

    public Product deleteById(String id) {
        Product product = repository.findById(id).orElse(null);
        repository.deleteById(id);
        return product;
    }
}
