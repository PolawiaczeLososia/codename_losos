package com.erpsystem.entity.carmodel;

import com.erpsystem.entity.car.Car;
import com.erpsystem.entity.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarModelService {

    @Autowired
    private CarModelRepository repository;

    public CarModel save(CarModel carModel){
        repository.save(carModel);
        return carModel;
    }

    public List<CarModel> findAll() {
        return repository.findAll();
    }

    public List<CarModel> findByProductionYear(String productionYear){
        return repository.findByProductionYear(productionYear);
    }

    public CarModel findById(String id){
        return repository.findById(id).orElse(null);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
