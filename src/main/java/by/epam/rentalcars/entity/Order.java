package by.epam.rentalcars.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "carOrder")
public class Order implements Comparable {

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

    public int compareTo(Object obj) {
        Order tmp = (Order) obj;
        if (this.returnDateTime.before(tmp.returnDateTime)) {
            return -1;
        } else if (this.returnDateTime.after(tmp.returnDateTime)) {
            return 1;
        }
        return 0;
    }
}
