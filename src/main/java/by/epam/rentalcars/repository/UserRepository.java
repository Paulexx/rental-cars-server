package by.epam.rentalcars.repository;

import by.epam.rentalcars.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(@Param("email") String email);

//    User findByFirstName(@Param("firstName") String firstName);
//
//    User findByFirstName(@Param("firstName") String firstName);

}