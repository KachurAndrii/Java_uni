package lab_1;
import lab_1.Classes.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Створюємо інформацію про машину
        Car car = new Car.Builder()
                .setBrand("Toyota")
                .setVin("1HGCM82633A123456")
                .setPlateNumber("AA1234BB")
                .setReleaseDate(LocalDate.of(2020, 5, 20))
                .setMileage(45000)
                .setPricePerDay(50.0)
                .build();

        // Створюємо інформацію про орендаря
        Renter renter = Renter.builder()
                .firstName("Іван")
                .lastName("Петров")
                .documentId("AB1234567")
                .drivingLicense("ID123456")
                .build();

        // Створюємо оренду
        CarRental rental = CarRental.builder()
                .car(car)
                .renter(renter)
                .pickupLocation("Київ")
                .dropoffLocation("Львів")
                .startDate(LocalDate.of(2023, 10, 1))
                .endDate(LocalDate.of(2023, 10, 5))
                .build();

        // Обчислюємо загальну ціну оренди
        rental.calculateTotalPrice();

        // Виводимо інформацію про оренду
        System.out.println(rental.toString());
    }
}