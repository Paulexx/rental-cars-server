package by.epam.rentalcars.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String helloWorld() {
        return "Hello world with Spring Boot!";
    }

}