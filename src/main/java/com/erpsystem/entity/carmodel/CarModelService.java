package com.erpsystem.entity.carmodel;

import com.erpsystem.entity.car.Car;
import com.erpsystem.entity.car.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarModelService {

  @Autowired
  private CarModelRepository carModelRepository;

  @Autowired
  private CarRepository carRepository;

  public CarModel save(CarModel carModel) {
    carModel.setCar(saveCar(carModel.getCar()));
    carModelRepository.save(carModel);
    return carModel;
  }

  public List<CarModel> findAll() {
    return carModelRepository.findAll();
  }

  public List<CarModel> findByProductionYear(String productionYear) {
    return carModelRepository.findByProductionYear(productionYear);
  }

  public CarModel findById(String id) {
    return carModelRepository.findById(id).orElse(null);
  }

  public CarModel overwrite(CarModel carModel) {
    CarModel overwrittenCarModel = carModelRepository.findById(carModel.getId()).orElse(null);
    if (overwrittenCarModel != null) {
      carModelRepository.save(carModel);
    }
    return overwrittenCarModel;
  }

  public void deleteAll() {
    carModelRepository.deleteAll();
  }

  public CarModel deleteById(String id) {
    CarModel carModel = carModelRepository.findById(id).orElse(null);
    carModelRepository.deleteById(id);
    return carModel;
  }

  private Car saveCar(Car car){
    Car presentCar = carRepository.findByName(car.getName());
    if (presentCar == null) {
      return carRepository.save(car);
    } else if(presentCar.equals(car)){
      return presentCar;
    }else{
      return carRepository.save(car);
    }
  }
}
