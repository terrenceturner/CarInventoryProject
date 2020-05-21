package com.cosc625.carinventoryservice.controller;

import com.cosc625.carinventoryservice.dao.CarInventoryRepository;
import com.cosc625.carinventoryservice.dto.Car;
import com.cosc625.carinventoryservice.exceptions.CarNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/cars")
public class CarInventoryController {

    @Autowired
    private CarInventoryRepository repo;

    public CarInventoryController(CarInventoryRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody @Valid Car car) {

        System.out.println("Saving Car...");
        return repo.save(car);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car getCar(@PathVariable("id")int id) {
        System.out.println("GETTING Car ID = " + id);

        Optional<Car> returnVal = repo.findById(id);
        if ( !returnVal.isPresent() ) {
            throw new CarNotFoundException("There is no Car with id " + id);
        }

        return returnVal.get();

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCar(@PathVariable("id") int id) {

        System.out.println("DELETING RSVP ID = " + id);
        repo.deleteById(id);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car updateCar(@PathVariable int id, @RequestBody @Valid Car car) {
        System.out.println("UPDATING RSVP ID = " + car.getId());


        // make sure the id on the path matches the id of the car object
        if (id != car.getId()) {
            throw new IllegalArgumentException("Car ID on path must match the ID in the Car object.");
        }

        repo.save(car);
        return car;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllCars() {
        System.out.println("Getting All Cars");
        return repo.findAll();
    }
}
