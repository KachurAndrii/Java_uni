package lab_3;

import lab_2.Classes.Car;
import lab_2.Classes.CarRental;
import lab_2.Classes.Renter;
import lab_3.Service.CarRentalService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створюємо прикладні дані
        Car car1 = new Car.Builder()
                .setBrand("Toyota")
                .setVin("1HGCM82633A123456")
                .setPlateNumber("AA1234BB")
                .setReleaseDate(LocalDate.of(2020, 5, 20))
                .setMileage(45000)
                .setPricePerDay(50.0)
                .build();

        Car car2 = new Car.Builder()
                .setBrand("Honda")
                .setVin("1HGCM82633A654321")
                .setPlateNumber("BB1234AA")
                .setReleaseDate(LocalDate.of(2019, 7, 10))
                .setMileage(30000)
                .setPricePerDay(40.0)
                .build();

        Renter renter1 = Renter.builder()
                .firstName("Іван")
                .lastName("Петров")
                .documentId("AB1234567")
                .drivingLicense("ID123456")
                .build();

        Renter renter2 = Renter.builder()
                .firstName("Олексій")
                .lastName("Коваленко")
                .documentId("CD9876543")
                .drivingLicense("ID987654")
                .build();

        CarRental rental1 = CarRental.builder()
                .car(car1)
                .renter(renter1)
                .pickupLocation("Київ")
                .dropoffLocation("Львів")
                .startDate(LocalDate.of(2023, 10, 1))
                .endDate(LocalDate.of(2023, 10, 5))
                .build();

        CarRental rental2 = CarRental.builder()
                .car(car2)
                .renter(renter2)
                .pickupLocation("Одеса")
                .dropoffLocation("Київ")
                .startDate(LocalDate.of(2023, 10, 5))
                .endDate(LocalDate.of(2023, 10, 10))
                .build();

        rental1.calculateTotalPrice();
        rental2.calculateTotalPrice();

        List<CarRental> rentals = new ArrayList<>();
        rentals.add(rental1);
        rentals.add(rental2);

        CarRentalService rentalService = new CarRentalService();

        // Приклад: сортування оренд за маркою машини
        List<CarRental> sortedByBrand = rentalService.sortRentalsByCarBrand(rentals);
        sortedByBrand.forEach(System.out::println);

        // Приклад: сортування оренд за загальною ціною
        List<CarRental> sortedByPrice = rentalService.sortRentalsByTotalPrice(rentals);
        sortedByPrice.forEach(System.out::println);

        // Приклад: сортування оренд за датою початку оренди
        List<CarRental> sortedByStartDate = rentalService.sortRentalsByStartDate(rentals);
        sortedByStartDate.forEach(System.out::println);
    }
}
