package by.epam.rentalcars.service;

import by.epam.rentalcars.entity.Order;

import java.util.List;

public interface OrderService {

    Order add(Order order);

    Order edit(Order order);

    void delete(Order order);

    Order findById(int id);

    List<Order> findAll();
}
