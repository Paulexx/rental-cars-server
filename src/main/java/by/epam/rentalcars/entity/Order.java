package by.epam.rentalcars.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column(name = "orderDateTime")
    public Date orderDateTime;

    @Column(name = "returnDateTime")
    public Date returnDateTime;

    @Column(name = "orderStatus")
    public String orderStatus;

    @Column(name = "userId")
    public int userId;

    @Column(name = "carId")
    public int carId;
}
