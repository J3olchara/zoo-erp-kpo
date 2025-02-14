package com.application.domain;

import com.application.domain.service.Zoo;
import com.application.domain.animals.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = {"com.application.domain.service", "com.application.application"})
public class ZooApplication implements CommandLineRunner {

    @Autowired
    private Zoo zoo;

    public static void main(String[] args) {
        SpringApplication.run(ZooApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить животное");
            System.out.println("2. Удалить животное");
            System.out.println("3. Показать всех животных");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    addAnimal(scanner);
                    break;
                case 2:
                    removeAnimal(scanner);
                    break;
                case 3:
                    showAnimals();
                    break;
                case 4:
                    running = false;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }

        scanner.close();
    }

    private void addAnimal(Scanner scanner) {
        System.out.println("\nДобавление животного:");
        System.out.print("Введите тип животного (Monkey, Rabbit, Tiger, Wolf): ");
        String type = scanner.nextLine();

        System.out.print("Введите инвентаризационный номер: ");
        int number = scanner.nextInt();

        System.out.print("Введите количество еды (кг/день): ");
        int food = scanner.nextInt();

        Animal animal;
        switch (type.toLowerCase()) {
            case "monkey":
                System.out.print("Введите уровень доброты (1-10): ");
                int kindnessLevel = scanner.nextInt();
                animal = new Monkey(number, food, kindnessLevel);
                break;
            case "rabbit":
                System.out.print("Введите уровень доброты (1-10): ");
                kindnessLevel = scanner.nextInt();
                animal = new Rabbit(number, food, kindnessLevel);
                break;
            case "tiger":
                animal = new Tiger(number, food);
                break;
            case "wolf":
                animal = new Wolf(number, food);
                break;
            default:
                System.out.println("Неверный тип животного.");
                return;
        }

        zoo.addAnimal(animal);
        System.out.println("Животное добавлено.");
    }

    private void removeAnimal(Scanner scanner) {
        System.out.println("\nУдаление животного:");
        System.out.print("Введите инвентаризационный номер: ");
        int number = scanner.nextInt();

        boolean removed = zoo.removeAnimal(number);
        if (removed) {
            System.out.println("Животное удалено.");
        } else {
            System.out.println("Животное с таким номером не найдено.");
        }
    }

    private void showAnimals() {
        
        System.out.println("\nСписок животных:");
        zoo.getAnimals().forEach(animal -> System.out.println(
                "Тип: " + animal.getClass().getSimpleName() +
                ", Номер: " + animal.getNumber() +
                ", Еда: " + animal.getFood() + " кг/день" +
                (animal instanceof Herbo ? ", Доброта: " + ((Herbo) animal).getKindnessLevel() : "")
        ));
        System.out.println("Общее количество еды для всех животных: " + zoo.getTotalFood() + " кг/день");
    }
}