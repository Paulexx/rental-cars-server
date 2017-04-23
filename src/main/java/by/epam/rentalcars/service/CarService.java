package by.epam.rentalcars.service;

import by.epam.rentalcars.entity.Car;

import java.util.List;

public interface CarService {

    Car findById(int id);

    List<Car> findAll();
}
