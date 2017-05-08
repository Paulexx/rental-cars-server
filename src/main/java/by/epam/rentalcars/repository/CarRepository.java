package by.epam.rentalcars.repository;

import by.epam.rentalcars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {

    @Query("SELECT c FROM Car c WHERE c.mark = :mark " +
            "and c.model = :model " +
            "and c.year = :year " +
            "and c.lastTI = :lastTI " +
            "and c.gearboxType = :gearboxType " +
            "and c.engineVolume = :engineVolume " +
            "and c.bodyType = :bodyType " +
            "and c.capacity = :capacity " +
            "and c.carryingCapacity = :carryingCapacity " +
            "and c.pricePerHour = :pricePerHour " +
            "and c.pricePerDay = :pricePerDay " +
            "and c.type = :type")
    List<Car> filterCars(@Param("mark") String mark, 
                         @Param("model") String model, 
                         @Param("year") int year, 
                         @Param("lastTI") Date lastTI,
                         @Param("gearboxType") String gearboxType,
                         @Param("engineVolume") float engineVolume,
                         @Param("bodyType") String bodyType,
                         @Param("capacity") int capacity,
                         @Param("carryingCapacity") int carryingCapacity,
                         @Param("pricePerHour") float pricePerHour,
                         @Param("pricePerDay") float pricePerDay,
                         @Param("type") String type);

//    Car findByMark(@Param("mark") String mark, @Param("model") String model);
//
//    Car findByModel(@Param("model") String model);
//
//    Car findByYear(@Param("year") int year);
//
//    Car findByLastTI(@Param("lastTI") Date lastTI);
//
//    Car findByGearboxType(@Param("gearboxType") String gearboxType);
//
//    Car findByEngineVolume(@Param("engineVolume") float engineVolume);
//
//    Car findByBodyType(@Param("bodyType") String bodyType);
//
//    Car findByCapacity(@Param("capacity") int capacity);
//
//    Car findByCarryingCapacity(@Param("carryingCapacity") int carryingCapacity);
//
//    Car findByPricePerHour(@Param("pricePerHour") float pricePerHour);
//
//    Car findByPricePerDay(@Param("pricePerDay") float pricePerDay);
//
//    Car findByType(@Param("type") String type);

}