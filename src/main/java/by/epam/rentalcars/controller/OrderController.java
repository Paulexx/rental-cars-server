package by.epam.rentalcars.controller;

import by.epam.rentalcars.entity.Order;
import by.epam.rentalcars.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping("/orders/{id}")
    public Order getOrderById(@PathVariable("id") int id) {
        return orderService.findById(id);
    }

    @RequestMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Order> add(@RequestBody Order order) {
        LOGGER.info("Adding order");
        if (orderService.findById(order.id) == null) {
            Order addedOrder = orderService.add(order);
            if (addedOrder != null) {
                LOGGER.info("Adding order successful");
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        LOGGER.error("Adding order failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<Order> edit(@RequestBody Order order) {
        LOGGER.info("Editing order with id = " + order.id);
        if (orderService.findById(order.id) != null) {
            Order editedOrder = orderService.edit(order);
            if (editedOrder != null) {
                LOGGER.info("Editing order with id = " + order.id + " successful");
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        LOGGER.error("Editing order with id = " + order.id + " failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Order> delete(@RequestBody Order order) {
        LOGGER.info("Deleting order with id = " + order.id);
        if (orderService.findById(order.id) != null) {
            orderService.delete(order);
            LOGGER.info("Deleting order with id = " + order.id + " successful");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        LOGGER.error("Deleting order with id = " + order.id + " failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/history/{id}")
    public List<Order> getAllOrders(@PathVariable("id") int id) {
        return orderService.findOrdersByUserId(id);
    }

}