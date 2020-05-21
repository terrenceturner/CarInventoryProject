package com.cosc625.carinventoryservice.dao;

import com.cosc625.carinventoryservice.dto.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarInventoryRepository extends JpaRepository<Car, Integer> {

        List<Car> findCarByMake(String make);

        List<Car> findCarByColor(String color);

        List<Car> findCarByVin(String vin);

        List<Car> findCarByModel(String model);

        List<Car> findCarByOdometer(String odometer);

        List<Car> findCarByYear(String year);

        List<Car> findCarByMakeAndColor(String make, String color);
}
