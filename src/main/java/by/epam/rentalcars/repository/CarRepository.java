package by.epam.rentalcars.repository;

import by.epam.rentalcars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {

}