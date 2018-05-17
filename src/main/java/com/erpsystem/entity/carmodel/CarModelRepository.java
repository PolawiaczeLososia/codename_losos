package com.erpsystem.entity.carmodel;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarModelRepository extends MongoRepository<CarModel, String> {

    List<CarModel> findByProductionYear(String productionYear);
    // CarModel findByCarId(String carId);
}
