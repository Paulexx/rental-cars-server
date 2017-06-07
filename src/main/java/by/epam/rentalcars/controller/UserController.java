package by.epam.rentalcars.controller;

import by.epam.rentalcars.entity.User;
import by.epam.rentalcars.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int id) {
        return userService.findById(id);
    }

    @RequestMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> add(@RequestBody User user) {
        LOGGER.info("Registering user");
        if (userService.findById(user.id) == null && userService.findByEmail(user.email) == null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword((passwordEncoder.encode(user.getPassword())));
            User addedUser = userService.add(user);
            if (addedUser != null) {
                LOGGER.info("Registering user successful");
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        LOGGER.error("Registering user failed");
        Map<String, String> errorMap = new HashMap<String, String>();
        errorMap.put("errors", "Registration failed: user with email " + user.email + " already exists");
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<User> edit(@RequestBody User user) {
        LOGGER.info("Editing user with id = " + user.id);
        if (userService.findById(user.id) != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword((passwordEncoder.encode(user.getPassword())));
            User editedUser = userService.edit(user);
            if (editedUser != null) {
                LOGGER.info("Editing user with id = " + user.id + " successful");
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        LOGGER.error("Editing user with id = " + user.id + " failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<User> delete(@RequestBody User user) {
        LOGGER.info("Deleting user with id = " + user.id);
        if (userService.findById(user.id) != null) {
            userService.delete(user);
            LOGGER.info("Deleting user with id = " + user.id + " successful");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        LOGGER.error("Deleting user with id = " + user.id + " failed");
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}