package by.epam.rentalcars.repository;

import by.epam.rentalcars.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}