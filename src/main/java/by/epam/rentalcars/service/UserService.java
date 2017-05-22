package by.epam.rentalcars.service;

import by.epam.rentalcars.entity.User;

import java.util.List;

public interface UserService {

    User add(User user);

    User edit(User user);

    void delete(User user);

    User findById(int id);

    User findByEmail(String email);

    List<User> findAll();
}
