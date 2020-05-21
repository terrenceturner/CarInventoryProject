package com.cosc625.carinventoryservice.dao;

import com.cosc625.carinventoryservice.dto.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarInventoryRepositoryTest {

    @Autowired
    CarInventoryRepository carRepo;

    @BeforeEach
    void setUp() {
        carRepo.deleteAll();
    }

    @Test
    void saveCarTest(){

        Car car = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        carRepo.save(car);

        List<Car> cars = carRepo.findAll();
        assertEquals(1, cars.size());
    }

    @Test
    void getCarByIdTest(){

        Car car = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        Car car1 = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");

        carRepo.save(car);
        carRepo.save(car1);

        Car getCar = carRepo.getOne(car.getId());
        assertEquals(car.getId(), getCar.getId());
    }

    @Test
    void getCarByVinTest(){

        //Arrange
        List<Car> expectedCars = new ArrayList<>();

        Car car = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");
        expectedCars.add(car);
        Car car1 = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        expectedCars.add(car1);
        Car car2 = buildCar("33333", "Ford", "Mustang", "Silver", "2016", "30000");
        expectedCars.add(car2);

        carRepo.save(car);
        carRepo.save(car1);
        carRepo.save(car2);


        //Act
        List<Car> actualCars = carRepo.findCarByVin("55555");

        //Assert
        assertEquals(1, actualCars.size());
        assertFalse(actualCars.contains(car1));
        //assertTrue(actualCars.containsAll(expectedCars));
        assertTrue(expectedCars.containsAll(actualCars));
    }


    @Test
    void getAllCarTest(){

        Car car = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        Car car1 = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");

        carRepo.save(car);
        carRepo.save(car1);

        List<Car> cars = carRepo.findAll();
        assertEquals(2, cars.size());

    }

    @Test
    void deleteCarTest(){

        Car car = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        Car car1 = buildCar("55555", "Honda", "Accord", "Blue", "2019","15000");

        carRepo.save(car);
        carRepo.save(car1);

        carRepo.deleteById(car.getId());
        List<Car> cars = carRepo.findAll();
        assertEquals(1, cars.size());
    }

    @Test
    void updateCarTest(){

        Car car = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        car = carRepo.save(car);

        car.setModel("F250");
        carRepo.save(car);

        Car getCar = carRepo.getOne(car.getId());
        assertEquals("F250", getCar.getModel());

    }

    @Test
    void findCarByMakeTest() {

        //Arrange
        List<Car> expectedCars = new ArrayList<>();

        Car car = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");
        expectedCars.add(car);
        Car car1 = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        expectedCars.add(car1);
        Car car2 = buildCar("33333", "Ford", "Mustang", "Silver", "2016", "30000");
        expectedCars.add(car2);

        carRepo.save(car);
        carRepo.save(car1);
        carRepo.save(car2);


        //Act
        List<Car> actualCars = carRepo.findCarByMake("Ford");

        //Assert
        assertEquals(2, actualCars.size());
        assertFalse(actualCars.contains(car));
        assertTrue(expectedCars.containsAll(actualCars));
        //assertTrue(actualCars.containsAll(expectedCars));

    }

    @Test
    void findCarByColorTest() {
        //Arrange
        List<Car> expectedCars = new ArrayList<>();

        Car car = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");
        expectedCars.add(car);
        Car car1 = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        expectedCars.add(car1);
        Car car2 = buildCar("33333", "Ford", "Mustang", "Silver", "2016", "30000");
        expectedCars.add(car2);

        carRepo.save(car);
        carRepo.save(car1);
        carRepo.save(car2);


        //Act
        List<Car> actualCars = carRepo.findCarByColor("Blue");

        //Assert
        assertEquals(1, actualCars.size());
        assertFalse(actualCars.contains(car1));
        assertFalse(actualCars.contains(car2));
        assertTrue(expectedCars.containsAll(actualCars));
        //assertTrue(actualCars.containsAll(expectedCars));

    }

    @Test
    void findCarByModelTest(){
        //Arrange
        List<Car> expectedCars = new ArrayList<>();

        Car car = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");
        expectedCars.add(car);
        Car car1 = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        expectedCars.add(car1);
        Car car2 = buildCar("33333", "Ford", "Mustang", "Silver", "2016", "30000");
        expectedCars.add(car2);

        carRepo.save(car);
        carRepo.save(car1);
        carRepo.save(car2);

        //Act
        List<Car> actualCars = carRepo.findCarByModel("Accord");

        //Assert
        assertEquals(1, actualCars.size());
        assertFalse(actualCars.contains(car1));
        assertFalse(actualCars.contains(car2));
        assertTrue(expectedCars.containsAll(actualCars));
        //assertTrue(actualCars.containsAll(expectedCars));



    }

    @Test
    void findCarByOdometerTest(){
        //Arrange
        List<Car> expectedCars = new ArrayList<>();

        Car car = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");
        expectedCars.add(car);
        Car car1 = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        expectedCars.add(car1);
        Car car2 = buildCar("33333", "Ford", "Mustang", "Silver", "2016", "30000");
        expectedCars.add(car2);

        carRepo.save(car);
        carRepo.save(car1);
        carRepo.save(car2);

        //Act
        List<Car> actualCars = carRepo.findCarByOdometer("30000");

        //Assert
        assertEquals(1, actualCars.size());
        assertFalse(actualCars.contains(car1));
        assertFalse(actualCars.contains(car));
        assertTrue(expectedCars.containsAll(actualCars));
        //assertTrue(actualCars.containsAll(expectedCars));

    }

    @Test
    void findCarByYearTest(){
        //Arrange
        List<Car> expectedCars = new ArrayList<>();

        Car car = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");
        expectedCars.add(car);
        Car car1 = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        expectedCars.add(car1);
        Car car2 = buildCar("33333", "Ford", "Mustang", "Silver", "2016", "30000");
        expectedCars.add(car2);

        carRepo.save(car);
        carRepo.save(car1);
        carRepo.save(car2);

        //Act
        List<Car> actualCars = carRepo.findCarByYear("2019");

        //Assert
        assertEquals(1, actualCars.size());
        assertFalse(actualCars.contains(car1));
        assertFalse(actualCars.contains(car2));
        assertTrue(expectedCars.containsAll(actualCars));
        //assertTrue(actualCars.containsAll(expectedCars));

    }

    @Test
    void findCarByMakeAndColorTest() {

        //Arrange
        List<Car> expectedCars = new ArrayList<>();

        Car car = buildCar("55555", "Honda", "Accord", "Blue", "2019", "15000");
        expectedCars.add(car);
        Car car1 = buildCar("22222", "Ford", "F150", "Red", "2006", "100000");
        expectedCars.add(car1);
        Car car2 = buildCar("33333", "Ford", "Mustang", "Silver", "2016", "30000");
        expectedCars.add(car2);

        carRepo.save(car);
        carRepo.save(car1);
        carRepo.save(car2);


        //Act
        List<Car> actualCars = carRepo.findCarByMakeAndColor("Ford", "Silver");

        //Assert
        assertEquals(1, actualCars.size());
        assertFalse(actualCars.contains(car));
        assertFalse(actualCars.contains(car1));
        assertTrue(expectedCars.containsAll(actualCars));
        //assertTrue(actualCars.containsAll(expectedCars));


    }

    private Car buildCar(String vin, String make, String model, String color, String year, String odometer){
        Car newCar = new Car();
        newCar.setVin(vin);
        newCar.setMake(make);
        newCar.setModel(model);
        newCar.setYear(year);
        newCar.setColor(color);
        newCar.setOdometer(odometer);
        return newCar;
    }
}