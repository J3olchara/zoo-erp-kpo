package com.application.application.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.application.domain.animals.*;
import com.application.application.domain.things.Thing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Zoo {
    private List<Animal> animals = new ArrayList<>();
    private List<Thing> things = new ArrayList<>();

    @Autowired
    private VetClinic vetClinic;

    public void addAnimal(Animal animal) {
        if (vetClinic.checkHealth(animal)) {
            animals.add(animal);
        }
    }

    public int getInventorySize() {
        return things.size();
    }

    public void addThing(Thing thing) {
        things.add(thing);
    }

    public int getTotalFood() {
        return animals.stream().mapToInt(Animal::getFood).sum();
    }

    public List<Animal> getContactZooAnimals() {
        return animals.stream()
                .filter(animal -> animal instanceof Herbo && ((Herbo) animal).getKindnessLevel() > 5)
                .collect(Collectors.toList()); // Исправлено
    }

    public void printInventory() {
        animals.forEach(animal -> System.out.println("Animal: " + animal.getClass().getSimpleName() + ", Number: " + animal.getNumber()));
        things.forEach(thing -> System.out.println("Thing: " + thing.getClass().getSimpleName() + ", Number: " + thing.getNumber()));
    }

    public int getSize() {
        return animals.size();
    }
}