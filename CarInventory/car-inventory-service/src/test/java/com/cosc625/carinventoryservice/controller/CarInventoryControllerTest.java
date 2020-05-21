package com.cosc625.carinventoryservice.controller;

import com.cosc625.carinventoryservice.dao.CarInventoryRepository;
import com.cosc625.carinventoryservice.dto.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(CarInventoryController.class)
class CarInventoryControllerTest {

    //Automatically inject beans into the class
    @Autowired
    private MockMvc mockMvc;

    //@Mock on mockito instance - unit test
    //@MockBean create mock of it but loads it into the application context
    @MockBean
    private CarInventoryRepository repo;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

    }


    @Test
    void createCarShouldReturnCreatedCar() throws Exception {

        Car inputCar = buildCar("11111", "Chevrolet", "Silverado", "Black", "2020");

        //Object to JSON in String
        String inputJson = mapper.writeValueAsString(inputCar);

        Car outputCar = buildCar("11111", "Chevrolet", "Silverado", "Black", "2020");
        outputCar.setId(2);

        //Object to JSON in String
        String outputJson = mapper.writeValueAsString(outputCar);


        when(repo.save(inputCar)).thenReturn(outputCar);

        this.mockMvc.perform(post("/cars")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));


    }

    @Test
    void getCarByIdShouldReturnCarWithIdJson() throws Exception {

        Car car = buildCar("11111", "Chevrolet", "Silverado", "Black", "2020");
        car.setId(2);

        // Since findById returns an Optional, we create one with our car object as the value
        Optional<Car> returnVal = Optional.of(car);

        //Object to JSON in String
        String outputJson = mapper.writeValueAsString(car);

        // Mocking DAO response
        // This is another way to mock using mockito
        // same as doReturn(returnVal).when(repo).findById(8);
        // We could also set up our mocks in a separate method, if we so chose
        when(repo.findById(2)).thenReturn(returnVal);


        this.mockMvc.perform(get("/cars/2"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                //use the objectmapper output with the json method
                .andExpect(content().json(outputJson));



    }

    /*@Test
    void getCarThatDoesNotExistReturns404() throws Exception {

        // Since findById returns an Optional, we create one. But this time without a value
        // as that would be the expected behavior if we searched for a non-existant id
        Optional<Car> returnVal = Optional.empty();

        int idForCarThatDoesNotExist = 100;

        when(repo.findById(idForCarThatDoesNotExist)).thenReturn(returnVal);

        this.mockMvc.perform(get("/cars/" + idForCarThatDoesNotExist))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound());
    }*/


    @Test
    void deleteCarIsOkNoContentReturned() throws Exception {
        //can't mock the call to delete. it returns void
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/cars/8"))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
                .andExpect(content().string(""));

    }

    @Test
    void updateCarsShouldReturnAnUpdatedCars() throws Exception {

        Car car = buildCar("11111", "Chevrolet", "Silverado", "Black", "2020");
        car.setId(2);

        //these will be the same
        String inputJson = mapper.writeValueAsString(car);
        String outputJson = mapper.writeValueAsString(car);

        this.mockMvc.perform(put("/cars/" + car.getId())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    void getAllCarsShouldReturnAListOfCars() throws Exception {

        Car car = buildCar("11111", "Chevrolet", "Silverado", "Black", "2020");
        car.setId(2);

        Car car1 = buildCar("99999", "Tesla", "Model 3", "Red", "2018");
        car1.setId(8);

        List<Car> carList = new ArrayList<>();
        carList.add(car);
        carList.add(car1);

        //Object to JSON in String
        when(repo.findAll()).thenReturn(carList);

        List<Car> listChecker = new ArrayList<>();
        listChecker.addAll(carList);
        //To confirm the test is testing what we think it is... add another Car.
        // Uncommenting the below line causes the test to fail. As expected!
        // listChecker.add(new Car(10, "Donald Duck", 2));
        String outputJson = mapper.writeValueAsString(listChecker);

        this.mockMvc.perform(get("/cars"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));


    }

    private Car buildCar(String vin, String make, String model, String color, String year){

        Car car = new Car();
        car.setVin(vin);
        car.setMake(make);
        car.setModel(model);
        car.setYear(year);
        car.setColor(color);

        return car;
    }
}