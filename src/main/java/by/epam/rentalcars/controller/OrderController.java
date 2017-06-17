package by.epam.rentalcars.controller;

import by.epam.rentalcars.entity.Car;
import by.epam.rentalcars.entity.Order;
import by.epam.rentalcars.service.CarService;
import by.epam.rentalcars.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CarService carService;

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
        if (orderService.findById(order.id) == null && !isOverlapped(order.orderDateTime, order.returnDateTime, order.carId)) {
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
            Car findCar = carService.findById(order.carId);
            findCar.status = "free";
            orderService.delete(order);
            LOGGER.info("Deleting order with id = " + order.id + " successful");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        LOGGER.error("Deleting order with id = " + order.id + " failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> deleteById(@PathVariable int id) {
        LOGGER.info("Deleting order with id = " + id);
        Order foundOrder = orderService.findById(id);
        if (foundOrder != null) {
            Car findCar = carService.findById(foundOrder.carId);
            findCar.status = "free";
            orderService.delete(foundOrder);
            LOGGER.info("Deleting order with id = " + id + " successful");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        LOGGER.error("Deleting order with id = " + id + " failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/history/{id}")
    public List<Order> getHistoryByUserId(@PathVariable("id") int id) {
        return orderService.findOrdersByUserId(id);
    }

    public boolean isOverlapped(Date startDate, Date endDate, int carId) {
        for (Order order : orderService.findOrdersByCarId(carId)) {
            if (startDate.before(order.returnDateTime) && order.orderDateTime.before(endDate) && order.orderStatus.equals("ordered")) {
                return true;
            }
        }
        return false;
    }

    @Scheduled(fixedRate = 5000)
    public void refreshOrdersStatus() {
        Date date = new Date();
        for (Order order : orderService.findAll()) {
            Car findCar = carService.findById(order.carId);
            if (date.after(order.orderDateTime) && date.before(order.returnDateTime) && !findCar.status.equals("ordered") && order.orderStatus.equals("ordered")) {
                findCar.status = "ordered";
                carService.edit(findCar);
                LOGGER.info("Automatic ordered order with id = " + order.id);
            }
            if (date.after(order.returnDateTime) && order.orderStatus.equals("ordered")) {
                order.orderStatus = "done";
                orderService.edit(order);
                findCar.status = "free";
                carService.edit(findCar);
                LOGGER.info("Automatic done order with id = " + order.id);
            }
            if (order.orderStatus.equals("canceled")) {
                findCar.status = "free";
                carService.edit(findCar);
                LOGGER.info("Automatic cancel order with id = " + order.id);
            }
        }
    }
}