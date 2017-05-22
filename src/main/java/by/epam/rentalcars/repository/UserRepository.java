package by.epam.rentalcars.repository;

import by.epam.rentalcars.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Integer> {

<<<<<<< HEAD
=======
    User findByEmail(@Param("email") String email);

//    User findByFirstName(@Param("firstName") String firstName);
//
//    User findByFirstName(@Param("firstName") String firstName);
>>>>>>> a95b14db304f35b959ab9f177e1b674a74b620c3

}