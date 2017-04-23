package by.epam.rentalcars.service.impl;

import by.epam.rentalcars.entity.Car;
import by.epam.rentalcars.repository.CarRepository;
import by.epam.rentalcars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car findById(int id) {
        return carRepository.findOne(id);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
