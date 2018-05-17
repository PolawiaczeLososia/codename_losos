package com.erpsystem.entity.car;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String> {
    Car findByName(String name);
}
