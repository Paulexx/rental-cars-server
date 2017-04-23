package by.epam.rentalcars.service;

import by.epam.rentalcars.entity.Order;

import java.util.List;

public interface OrderService {

    Order findById(int id);

    List<Order> findAll();
}
