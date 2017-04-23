package by.epam.rentalcars.controller;

import by.epam.rentalcars.entity.User;
import by.epam.rentalcars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @RequestMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

}