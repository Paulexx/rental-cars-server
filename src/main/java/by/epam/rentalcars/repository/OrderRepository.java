package by.epam.rentalcars.repository;

import by.epam.rentalcars.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.userId = :id ")
    List<Order> findOrdersByUserId(@Param("id") int id);

    @Query("SELECT o FROM Order o WHERE o.carId = :id ")
    List<Order> findOrdersByCarId(@Param("id") int id);
}