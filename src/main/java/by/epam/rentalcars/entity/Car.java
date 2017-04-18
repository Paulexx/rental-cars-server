package by.epam.rentalcars.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int carid;

    @Column
    public String mark;

    @Column
    public String model;

    @Column
    public int year;

    @Column
    public Date lastti;

    @Column
    public String gearboxtype;

    @Column
    public int enginevolume;

    @Column
    public String bodytype;

    @Column
    public int capacity;

    @Column
    public int carryingcapacity;

    @Column
    public float priceperhour;

    @Column
    public float priceperday;

    @Column
    public String type;

    @Column
    public String status;
}
