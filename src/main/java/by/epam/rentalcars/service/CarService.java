package by.epam.rentalcars.service;

import by.epam.rentalcars.entity.Car;

import java.util.List;

public interface CarService {

    Car add(Car car);

    Car edit(Car car);

    void delete(Car car);

    Car findById(int id);

    List<Car> findAll();
}
