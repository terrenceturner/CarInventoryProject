package com.cosc625.vinlookup.controller;

import com.cosc625.vinlookup.dto.Vehicle;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Random;

public class VinLookupController {

    private String[] types = {"Motorcycle", "Truck", "Automobile", "Other"};
    private String[] makes = {"Honda", "Suzuki", "Subaru", "Ford", "Cheverolet"};
    private String[] models = {"Africa Twin", "F-150", "Outback", "Malibu", "DL650", "CBR100RR"};
    private String[] colors = {"Red", "Blue", "White", "Black", "Grey", "Tan", "Silver", "Orange"};
    private String[] years = {"2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020"};

    Random rndGen = new Random();

    @RequestMapping(value = "/vehicle/{vin}", method = RequestMethod.GET)
    public Vehicle lookUpVehicle(@PathVariable String vin) {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(types[rndGen.nextInt(4)]);
        vehicle.setMake(makes[rndGen.nextInt(5)]);
        vehicle.setModel(models[rndGen.nextInt(6)]);
        vehicle.setColor(colors[rndGen.nextInt(8)]);
        vehicle.setYear(years[rndGen.nextInt(12)]);

        return vehicle;
    }
}
