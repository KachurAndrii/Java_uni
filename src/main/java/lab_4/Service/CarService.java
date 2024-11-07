package lab_4.Service;

import lab_2.Classes.Car;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for operations with Car collections.
 */
public class CarService {

    /**
     * Get a list of cars filtered by brand.
     *
     * @param cars the list of cars
     * @param brand the brand to filter by
     * @return a list of cars of the specified brand
     */
    public List<Car> filterCarsByBrand(List<Car> cars, String brand) {
        return cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
    }

    /**
     * Sort cars by mileage in ascending order.
     *
     * @param cars the list of cars
     * @return a sorted list of cars by mileage
     */
    public List<Car> sortCarsByMileage(List<Car> cars) {
        return cars.stream()
                .sorted((car1, car2) -> Integer.compare(car1.getMileage(), car2.getMileage()))
                .collect(Collectors.toList());
    }

    /**
     * Get cars with mileage greater than a specified value.
     *
     * @param cars the list of cars
     * @param mileageThreshold the minimum mileage
     * @return a list of cars with mileage greater than the specified value
     */
    public List<Car> getCarsWithMileageGreaterThan(List<Car> cars, int mileageThreshold) {
        return cars.stream()
                .filter(car -> car.getMileage() > mileageThreshold)
                .collect(Collectors.toList());
    }
}
