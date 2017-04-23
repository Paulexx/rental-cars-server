package by.epam.rentalcars.service;

import by.epam.rentalcars.entity.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    List<User> findAll();
}
