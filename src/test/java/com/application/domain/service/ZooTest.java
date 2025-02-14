package com.application.domain.service;

import com.application.application.domain.animals.*;
import com.application.application.domain.things.*;
import com.application.application.domain.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ZooTest {

    @Mock
    private VetClinic vetClinic;

    @InjectMocks
    private Zoo zoo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация моков
    }

    @Test
    void testAddAnimal() {
        Animal monkey = new Monkey(1, 5, 7);
        when(vetClinic.checkHealth(monkey)).thenReturn(true);

        zoo.addAnimal(monkey);

        assertEquals(1, zoo.getSize());
    }

    @Test
    void testAddAnimal_NotHealthy() {
        Animal tiger = new Tiger(2, 10);
        when(vetClinic.checkHealth(tiger)).thenReturn(false);

        zoo.addAnimal(tiger);

        assertEquals(0, zoo.getSize());
    }

    @Test
    void testAddThing() {
        Thing table = new Table(101);
        zoo.addThing(table);

        assertEquals(1, zoo.getInventorySize());
    }

    @Test
    void testGetTotalFood() {
        Animal monkey = new Monkey(1, 5, 7);
        Animal tiger = new Tiger(2, 10);
        when(vetClinic.checkHealth(monkey)).thenReturn(true);
        when(vetClinic.checkHealth(tiger)).thenReturn(true);

        zoo.addAnimal(monkey);
        zoo.addAnimal(tiger);

        assertEquals(15, zoo.getTotalFood());
    }

    @Test
    void testGetContactZooAnimals() {
        Animal monkey = new Monkey(1, 5, 7); // Доброта > 5
        Animal rabbit = new Rabbit(2, 3, 4); // Доброта <= 5
        Animal tiger = new Tiger(3, 10); // Не травоядное
        when(vetClinic.checkHealth(monkey)).thenReturn(true);
        when(vetClinic.checkHealth(rabbit)).thenReturn(true);
        when(vetClinic.checkHealth(tiger)).thenReturn(true);

        zoo.addAnimal(monkey);
        zoo.addAnimal(rabbit);
        zoo.addAnimal(tiger);

        List<Animal> contactZooAnimals = zoo.getContactZooAnimals();

        assertEquals(1, contactZooAnimals.size());
        assertEquals(Monkey.class, contactZooAnimals.get(0).getClass());
    }

    @Test
    void testPrintInventory() {
        Animal monkey = new Monkey(1, 5, 7);
        Thing table = new Table(101);
        when(vetClinic.checkHealth(monkey)).thenReturn(true);

        zoo.addAnimal(monkey);
        zoo.addThing(table);

        assertDoesNotThrow(() -> zoo.printInventory());
    }

    @Test
    void testAddMultipleAnimalsAndThings() {
        Animal monkey = new Monkey(1, 5, 7);
        Animal tiger = new Tiger(2, 10);
        Thing table = new Table(101);
        Thing computer = new Computer(102);
        when(vetClinic.checkHealth(monkey)).thenReturn(true);
        when(vetClinic.checkHealth(tiger)).thenReturn(true);

        zoo.addAnimal(monkey);
        zoo.addAnimal(tiger);
        zoo.addThing(table);
        zoo.addThing(computer);

        assertEquals(15, zoo.getTotalFood());
        assertEquals(2, zoo.getInventorySize());
    }

    @Test
    void testEmptyZoo() {
        assertEquals(0, zoo.getTotalFood());
        assertEquals(0, zoo.getInventorySize());
        assertTrue(zoo.getContactZooAnimals().isEmpty());
    }

    @Test
    void testKindnessLevelBoundary() {
        Animal rabbitLowKindness = new Rabbit(1, 3, 5); // Доброта = 5
        Animal rabbitHighKindness = new Rabbit(2, 3, 6); // Доброта > 5
        when(vetClinic.checkHealth(rabbitLowKindness)).thenReturn(true);
        when(vetClinic.checkHealth(rabbitHighKindness)).thenReturn(true);

        zoo.addAnimal(rabbitLowKindness);
        zoo.addAnimal(rabbitHighKindness);

        List<Animal> contactZooAnimals = zoo.getContactZooAnimals();

        assertEquals(1, contactZooAnimals.size());
        assertEquals(Rabbit.class, contactZooAnimals.get(0).getClass());
    }

    @Test
    void testPrintInventoryWithEmptyLists() {
        assertDoesNotThrow(() -> zoo.printInventory());
    }

    @Test
    void testGetInventorySize() {
        Thing table = new Table(101);
        Thing computer = new Computer(102);
        zoo.addThing(table);
        zoo.addThing(computer);

        assertEquals(2, zoo.getInventorySize());
    }
}