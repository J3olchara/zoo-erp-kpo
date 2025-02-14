package com.application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.application.application.domain.animals.*;
import com.application.application.domain.service.Zoo;
import com.application.application.domain.things.*;


@SpringBootApplication
public class ZooApplication implements CommandLineRunner {

    @Autowired
    private Zoo zoo;

    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        zoo.addAnimal(new Monkey(1, 5, 7));
        zoo.addAnimal(new Tiger(2, 10));
        zoo.addThing(new Table(101));
        zoo.addThing(new Computer(102));

        System.out.println("Total food: " + zoo.getTotalFood());
        System.out.println("Contact zoo animals:");
        zoo.getContactZooAnimals().forEach(animal -> System.out.println(animal.getClass().getSimpleName()));
        System.out.println("Inventory:");
        zoo.printInventory();
    }
}