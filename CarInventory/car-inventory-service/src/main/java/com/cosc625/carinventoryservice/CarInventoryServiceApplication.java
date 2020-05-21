package com.cosc625.carinventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CarInventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarInventoryServiceApplication.class, args);
		System.out.println("working...");
	}

}
