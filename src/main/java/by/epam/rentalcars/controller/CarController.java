package by.epam.rentalcars.controller;

import by.epam.rentalcars.entity.Car;
import by.epam.rentalcars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("/cars/{id}")
    public Car getCarById(@PathVariable("id") int id) {
        return carService.findById(id);
    }

    @RequestMapping("/cars")
    public List<Car> getAllCars() {
        return carService.findAll();
    }

}