package by.epam.rentalcars.repository;

import by.epam.rentalcars.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}