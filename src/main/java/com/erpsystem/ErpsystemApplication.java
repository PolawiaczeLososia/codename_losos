package com.erpsystem;

import com.erpsystem.entity.car.Car;
import com.erpsystem.entity.car.CarRepository;
import com.erpsystem.entity.car.CarService;
import com.erpsystem.entity.carmodel.CarModel;
import com.erpsystem.entity.carmodel.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpsystemApplication implements CommandLineRunner {

    @Autowired
    private CarService carService;
    @Autowired
    private CarModelService carModelService;

    public static void main(String[] args) {
        SpringApplication.run(ErpsystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        carService.deleteAll();
        //carModelService.deleteAll();

        var car1 = carService.save(new Car("Mercedes"));
        var car2 = carService.save(new Car("BMW"));
        var car3 = carService.save(new Car("Ford"));
        var car4 = carService.save(new Car("Opel"));

        var carModel1 = carModelService.save(new CarModel("Benz", "1995", car1));
        var carModel2 = carModelService.save(new CarModel("Z3", "1981", car2));
        var carModel3 = carModelService.save(new CarModel("Shelby", "1992", car3));
        var carModel4 = carModelService.save(new CarModel("Astra","1982", car4));

    }
}
