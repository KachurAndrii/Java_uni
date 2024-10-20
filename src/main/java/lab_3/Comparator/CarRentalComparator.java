package lab_3.Comparator;

import lab_2.Classes.CarRental;

import java.util.Comparator;

/**
 * Comparator for sorting CarRental objects by various fields.
 */
public class CarRentalComparator {

    // Comparator for sorting by car brand (марка машини)
    public static Comparator<CarRental> byCarBrand() {
        return Comparator.comparing(carRental -> carRental.getCar().getBrand());
    }

    // Comparator for sorting by total price (загальна ціна)
    public static Comparator<CarRental> byTotalPrice() {
        return Comparator.comparing(CarRental::getTotalPrice);
    }

    // Comparator for sorting by start date of rental (дата початку оренди)
    public static Comparator<CarRental> byStartDate() {
        return Comparator.comparing(CarRental::getStartDate);
    }

    // Comparator for sorting by mileage of car (пробіг машини)
    public static Comparator<CarRental> byCarMileage() {
        return Comparator.comparing(carRental -> carRental.getCar().getMileage());
    }
}