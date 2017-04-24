package by.epam.rentalcars.service.impl;

import by.epam.rentalcars.entity.Order;
import by.epam.rentalcars.repository.OrderRepository;
import by.epam.rentalcars.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order add(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order edit(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public void delete(Order order) {
        orderRepository.delete(order);
    }

    @Override
    public Order findById(int id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
