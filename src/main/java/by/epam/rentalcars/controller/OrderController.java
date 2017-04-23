package by.epam.rentalcars.controller;

import by.epam.rentalcars.entity.Order;
import by.epam.rentalcars.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/orders/{id}")
    public Order getOrderById(@PathVariable("id") int id) {
        return orderService.findById(id);
    }

    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

}