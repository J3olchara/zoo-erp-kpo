Описание проекта

Это консольное приложение для учета животных и вещей в зоопарке. Оно позволяет:

Добавлять новых животных и вещи.
Проверять здоровье животных перед добавлением.
Подсчитывать общее количество еды, необходимое для всех животных.
Формировать список животных, которые могут быть помещены в контактный зоопарк.
Выводить инвентаризационные данные.
Принципы SOLID, примененные в проекте

Принцип единственной ответственности (Single Responsibility Principle, SRP):
Каждый класс отвечает за одну задачу. Например:
Класс Zoo отвечает за управление животными и вещами.
Класс VetClinic отвечает за проверку здоровья животных.
Классы Animal, Herbo, Predator и другие отвечают за хранение данных о животных.
Принцип открытости/закрытости (Open/Closed Principle, OCP):
Классы открыты для расширения, но закрыты для модификации. Например:
Класс Animal можно расширять, создавая новые типы животных (например, Monkey, Tiger), не изменяя существующий код.
Класс Zoo работает с любыми типами животных, реализующими интерфейс IAlive.
Принцип подстановки Барбары Лисков (Liskov Substitution Principle, LSP):
Классы-наследники (Herbo, Predator, Monkey, Tiger и другие) могут использоваться вместо базового класса Animal без изменения поведения программы.
Принцип разделения интерфейса (Interface Segregation Principle, ISP):
Интерфейсы IAlive и IInventory разделены, чтобы классы не зависели от методов, которые они не используют. Например:
Животные реализуют IAlive, а вещи — IInventory.
Принцип инверсии зависимостей (Dependency Inversion Principle, DIP):
Классы зависят от абстракций, а не от конкретных реализаций. Например:
Класс Zoo зависит от интерфейса VetClinic, а не от конкретной реализации ветеринарной клиники.
Внедрение зависимостей (DI) осуществляется через Spring.
Инструкция по запуску

Требования:
Установите Java 11 или выше.
Установите Maven 3.6 или выше.
Сборка проекта:
Перейдите в корневую директорию проекта (где находится pom.xml).
Выполните команду:
bash
Copy
mvn clean install
Запуск приложения:
После успешной сборки выполните команду:
bash
Copy
mvn spring-boot:run
Запуск тестов:
Для запуска тестов выполните команду:
bash
Copy
mvn test
Пример работы приложения

После запуска приложения вы увидите вывод в консоли:

Copy
Total food: 15
Contact zoo animals:
Monkey
Inventory:
Animal: Monkey, Number: 1
Animal: Tiger, Number: 2
Thing: Table, Number: 101
Thing: Computer, Number: 102
Тестирование

Проект покрыт юнит-тестами с использованием JUnit 5 и Mockito. Тесты проверяют:

Добавление животных и вещей.
Проверку здоровья животных.
Подсчет общего количества еды.
Формирование списка животных для контактного зоопарка.
Вывод инвентаря.
Для запуска тестов выполните команду:

bash
Copy
mvn test