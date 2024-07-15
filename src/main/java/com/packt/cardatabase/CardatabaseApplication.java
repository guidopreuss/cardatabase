package com.packt.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;

import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;

import java.util.Arrays;


@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {
    private static final Logger logger =
            LoggerFactory.getLogger(CardatabaseApplication.class);

    private final CarRepository repository;
    private final OwnerRepository orepository;
    public CardatabaseApplication(CarRepository repository,
                                  OwnerRepository orepository)
    {
        this.repository = repository;
        this.orepository = orepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(CardatabaseApplication.class, args);
        logger.info("GP: Application started...");
    }
    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner("John" , "Johnson");
        Owner owner2 = new Owner("Mary" , "Robinson");
        orepository.saveAll(Arrays.asList(owner1, owner2));
        repository.save(new Car("Ford", "Mustang", "Red",
                "ADF-1121", 2023, 59000, owner1));
        repository.save(new Car("Nissan", "Leaf", "White",
                "SSJ-3002", 2020, 29000, owner1));
        repository.save(new Car("Toyota", "Prius",
                "Silver", "KKO-0212", 2022, 39000, owner2));
        // Fetch all cars and log to console
        for (Car car : repository.findAll()) {
            logger.info("GP brand: {}, model: {}",
                    car.getBrand(), car.getModel());
            logger.info(owner1.getLastname());
            Owner myowner = car.getOwner();
            logger.info(myowner != null ? "not null " : "null");
            Long ownerId = myowner.getOwnerid();
            logger.info("mylong: " + ownerId);
            Owner owner = orepository.findById(ownerId).get();
            logger.info("owner: " + owner.getFirstname());

        }
    }

}
