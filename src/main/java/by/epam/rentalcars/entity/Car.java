package by.epam.rentalcars.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    @Column
    public String mark;

    @Column
    public String model;

    @Column
    public int year;

    @Column(name = "lastTI")
    public Date lastTI;

    @Column(name = "gearboxType")
    public String gearboxType;

    @Column(name = "engineVolume")
    public int engineVolume;

    @Column(name = "bodyType")
    public String bodyType;

    @Column
    public int capacity;

    @Column(name = "carryingCapacity")
    public int carryingCapacity;

    @Column(name = "pricePerHour")
    public float pricePerHour;

    @Column(name = "pricePerDay")
    public float pricePerDay;

    @Column
    public String type;

    @Column
    public String status;

    @Column
    public String image;
}
