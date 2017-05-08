package by.epam.rentalcars.controller;

import by.epam.rentalcars.entity.Car;
import by.epam.rentalcars.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @RequestMapping("/cars/{id}")
    public Car getCarById(@PathVariable("id") int id) {
        LOGGER.info("Load car with id = " + id);
        return carService.findById(id);
    }

    @RequestMapping("/filter")
    public List<Car> filterCars(@RequestBody Car car) {
        LOGGER.info("Filter cars");
        return carService.filterCars(car);
    }

//    @RequestMapping("/cars/{mark}")
//    public Car getCarByMark(@PathVariable("mark") String mark) {
//        //LOGGER.info("Load car with mark = " + mark);
//        return carService.findByMark(mark);
//    }
//
//    @RequestMapping("/cars/{model}")
//    public Car getCarByModel(@PathVariable("model") String model) {
//        //LOGGER.info("Load car with mark = " + mark);
//        return carService.findByModel(model);
//    }
//
//    @RequestMapping("/cars/{year}")
//    public Car getCarByYear(@PathVariable("year") int year) {
//        //LOGGER.info("Load car with mark = " + mark);
//        return carService.findByYear(year);
//    }

    @RequestMapping("/cars")
    public List<Car> getAllCars() {
        LOGGER.info("Load all cars");
        return carService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Car> add(@RequestBody Car car) {
        LOGGER.info("Adding car");
        if (carService.findById(car.id) == null) {
            Car addedCar = carService.add(car);
            if (addedCar != null) {
                LOGGER.info("Adding car successful");
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        LOGGER.error("Adding car failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<Car> edit(@RequestBody Car car) {
        LOGGER.info("Editing car with id = " + car.id);
        if (carService.findById(car.id) != null) {
            Car editedCar = carService.edit(car);
            if (editedCar != null) {
                LOGGER.info("Editing car with id = " + car.id + " successful");
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        LOGGER.error("Editing car with id = " + car.id + " failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Car> delete(@RequestBody Car car) {
        LOGGER.info("Deleting car with id = " + car.id);
        if (carService.findById(car.id) != null) {
            carService.delete(car);
            LOGGER.info("Deleting car with id = " + car.id + " successful");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        LOGGER.error("Deleting car with id = " + car.id + " failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}