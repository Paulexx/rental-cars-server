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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        if (orderService.findById(order.id) == null && !isOverlapped(order.orderDateTime, order.returnDateTime, order.carId)) {
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

    public static ArrayList<Date> getDaysBetweenDates(Date startDate, Date endDate) {
        ArrayList<Date> dates = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);
        while (calendar.getTime().before(endDate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

    @RequestMapping("/freedates/{id}")
    public List<Map.Entry<String,String>> getFreeDatesByCarId(@PathVariable("id") int id) {
        List<Order> orders = orderService.findOrdersByCarId(id);
        Collections.sort(orders);
        ArrayList<Date> freeDates = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calStart = Calendar.getInstance();
        calStart.set(Calendar.HOUR_OF_DAY, 0);
        calStart.set(Calendar.MINUTE, 0);
        calStart.set(Calendar.SECOND, 0);
        calStart.set(Calendar.MILLISECOND, 0);
        if (orders.size() != 0) {
            Calendar calEnd = Calendar.getInstance();
            calEnd.setTime(orders.get(orders.size() - 1).returnDateTime);
            ArrayList<String> stringDates = new ArrayList<>();
            freeDates.addAll(getDaysBetweenDates(calStart.getTime(), calEnd.getTime()));
            for (Order order : orders) {
                if (order.orderStatus.equals("ordered")) {
                    freeDates.removeAll(getDaysBetweenDates(order.orderDateTime, order.returnDateTime));
                }
            }
            Map<String, String> dateMap = new LinkedHashMap<String, String>();
            for (int i = 0; i < freeDates.size(); i++) {
                for (int j = i + 1; j < freeDates.size(); j++) {
                    int countOfDays = (int) ((freeDates.get(j).getTime() - freeDates.get(j - 1).getTime()) / (1000 * 60 * 60 * 24));
                    if (countOfDays != 1) {
                        dateMap.put(dateFormat.format(freeDates.get(i)), dateFormat.format(freeDates.get(j - 1)));
                        i = j - 1;
                        break;
                    }
                    if (j == freeDates.size() - 1) {
                        dateMap.put(dateFormat.format(freeDates.get(i)), dateFormat.format(freeDates.get(j)));
                        i = j;
                    }
                }
            }
            Collections.reverse(orders);
            for (Order order : orders) {
                if (order.orderStatus.equals("ordered")) {
                    dateMap.put(dateFormat.format(order.returnDateTime), "...");
                    break;
                }
            }
            for (Map.Entry<String, String> date : dateMap.entrySet()) {
                stringDates.add(date.getKey() + " - " + date.getValue());
            }
            List<Map.Entry<String, String>> dateJSON = new ArrayList<>();
            for (String date : stringDates) {
                Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<String, String>("date", date);
                dateJSON.add(entry);
            }
            return dateJSON;
        }
        else {
            List<Map.Entry<String, String>> dateJSON = new ArrayList<>();
            Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<String, String>("date", dateFormat.format(calStart.getTime()) + " - ...");
            dateJSON.add(entry);
            return dateJSON;
        }
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