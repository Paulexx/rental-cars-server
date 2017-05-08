package by.epam.rentalcars.service;

import by.epam.rentalcars.entity.Car;

import java.util.Date;
import java.util.List;

public interface CarService {

    Car add(Car car);

    Car edit(Car car);

    void delete(Car car);

    Car findById(int id);

    List<Car> filterCars(Car car);

//    Car findByMark(String mark);
//
//    Car findByModel(String model);
//
//    Car findByYear(int year);
//
//    Car findByLastTI(Date lastTI);
//
//    Car findByGearboxType(String gearboxType);
//
//    Car findByEngineVolume(float engineVolume);
//
//    Car findByBodyType(String bodyType);
//
//    Car findByCapacity(int capacity);
//
//    Car findByCarryingCapacity(int carryingCapacity);
//
//    Car findByPricePerHour(float pricePerHour);
//
//    Car findByPricePerDay(float pricePerDay);
//
//    Car findByType(String type);

    List<Car> findAll();
}
