package com.cosc625.carinventoryservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message = "You must supply a value for VIN.")
    @Size(min = 5, max = 5, message = "VIN must be 5 characters in length.")
    private String vin;
    @NotEmpty(message = "You must provide a value for make.")
    private String make;
    @NotEmpty(message = "You must provide a value for make.")
    private String model;
    @NotEmpty(message = "You must provide a value for year.")
    @Size(min = 4, max = 4, message = "Year must be 4 digits.")
    private String year;
    @NotEmpty(message = "You must provide a value for color.")
    private String color;
    @NotEmpty(message = "You must provide value for odometer")
    private String odometer;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                vin.equals(car.vin) &&
                make.equals(car.make) &&
                model.equals(car.model) &&
                year.equals(car.year) &&
                color.equals(car.color) &&
                odometer.equals(car.odometer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vin, make, model, year, color, odometer);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", vin='" + vin + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", color='" + color + '\'' +
                ", odometer='" + odometer + '\'' +
                '}';
    }
}
