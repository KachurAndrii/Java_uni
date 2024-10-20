package lab_3.Service;

import lab_2.Classes.Car;
import lab_2.Classes.CarRental;
import lab_2.Comparator.CarRentalComparator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for operations with CarRental collections.
 */
public class CarRentalService {

    /**
     * Filter rentals by a given renter's last name.
     *
     * @param rentals the list of rentals
     * @param lastName the last name of the renter to filter by
     * @return a list of rentals where the renter's last name matches
     */
    public List<CarRental> filterRentalsByRenterLastName(List<CarRental> rentals, String lastName) {
        return rentals.stream()
                .filter(rental -> rental.getRenter().getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
    }

    /**
     * Sort rentals by car brand in ascending order using the comparator.
     *
     * @param rentals the list of rentals
     * @return a sorted list of rentals by car brand
     */
    public List<CarRental> sortRentalsByCarBrand(List<CarRental> rentals) {
        return rentals.stream()
                .sorted(CarRentalComparator.byCarBrand())
                .collect(Collectors.toList());
    }

    /**
     * Sort rentals by total price in ascending order using the comparator.
     *
     * @param rentals the list of rentals
     * @return a sorted list of rentals by total price
     */
    public List<CarRental> sortRentalsByTotalPrice(List<CarRental> rentals) {
        return rentals.stream()
                .sorted(CarRentalComparator.byTotalPrice())
                .collect(Collectors.toList());
    }

    /**
     * Sort rentals by start date of rental in ascending order using the comparator.
     *
     * @param rentals the list of rentals
     * @return a sorted list of rentals by start date
     */
    public List<CarRental> sortRentalsByStartDate(List<CarRental> rentals) {
        return rentals.stream()
                .sorted(CarRentalComparator.byStartDate())
                .collect(Collectors.toList());
    }

    /**
     * Get a list of cars that were rented in a specific location.
     *
     * @param rentals  the list of rentals
     * @param location the pickup location
     * @return a list of cars rented from the specified location
     */
    public List<Car> getCarsRentedFromLocation(List<CarRental> rentals, String location) {
        return rentals.stream()
                .filter(rental -> rental.getPickupLocation().equalsIgnoreCase(location))
                .map(CarRental::getCar)
                .collect(Collectors.toList());
    }

    /**
     * Sort rentals by car mileage in ascending order using the comparator.
     *
     * @param rentals the list of rentals
     * @return a sorted list of rentals by car mileage
     */
    public List<CarRental> sortRentalsByCarMileage(List<CarRental> rentals) {
        return rentals.stream()
                .sorted(CarRentalComparator.byCarMileage())
                .collect(Collectors.toList());
    }
}
