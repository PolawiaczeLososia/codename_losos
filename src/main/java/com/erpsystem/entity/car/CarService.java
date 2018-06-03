package com.erpsystem.entity.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CarService {

  @Autowired
  private CarRepository carRepository;

  public Car save(Car car) {
    carRepository.save(car);
    return car;
  }

  public List<Car> findAll() {
    return carRepository.findAll();
  }

  public Car findById(String id) {
    return carRepository.findById(id).orElse(null);
  }

  public Car findByName(String name) {
    return carRepository.findByName(name);
  }

  public Car overwrite(Car car) {
    Car overwrittenCar = carRepository.findById(car.getId()).orElse(null);
    if (overwrittenCar != null) {
      carRepository.save(car);
    }
    return overwrittenCar;
  }

  public void deleteAll() {
    carRepository.deleteAll();
  }

  public void delete(Car car) {
    carRepository.delete(car);
  }

  public Car deleteById(String id) {
    Car car = carRepository.findById(id).orElse(null);
    carRepository.deleteById(id);
    return car;
  }
}
