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
    public Car add(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car edit(Car car) {
        return carRepository.saveAndFlush(car);
    }

    @Override
    public void delete(Car car) {
        carRepository.delete(car);
    }

    @Override
    public Car findById(int id) {
        return carRepository.findOne(id);
    }

    @Override
    public List<Car> filterCars(Car car) {
        return carRepository.filterCars(car.mark,
                car.model, car.year, car.lastTI,
                car.gearboxType, car.engineVolume,
                car.bodyType, car.capacity,
                car.carryingCapacity, car.pricePerHour,
                car.pricePerDay, car.type);
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
