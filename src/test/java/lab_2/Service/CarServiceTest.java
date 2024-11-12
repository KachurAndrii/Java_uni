package lab_2.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import lab_2.Classes.*;

import static org.junit.jupiter.api.Assertions.*;

class CarServiceTest {

    private Car car1;
    private Car car2;
    private Car car3;

    private CarService carService;


    @BeforeEach
    void setUp() {
        this.car1 = new Car("Toyota",
                "JTHBE96S280012345",
                "AB1234CD",
                LocalDate.parse("2018-06-15"),
                50000,
                49.9);

        this.car2 = new Car("Nissan",
                "ABCDF26S281112225",
                "AB1224CD",
                LocalDate.parse("2018-07-11"),
                390000,
                39);

        this.car3 = new Car("Toyota",
                "GGHHE111280022222",
                "AA11111AA",
                LocalDate.parse("2020-07-20"),
                69,
                20000);

        List<Car> cars = List.of(car1, car2, car3);

        this.carService = new CarService(cars);
    }

    @Test
    void filterCarsByBrand() {
        List<Car> res = carService.filterCarsByBrand("Toyota");
        assertEquals(List.of(car1, car3), res, "first and third car");
    }


    @Test
    void sortCarsByMileage() {
        List<Car> res = carService.sortCarsByMileage();
        assertEquals(List.of(car3, car1, car2), res, "sorted by mileage");
    }


    @Test
    void getCarsWithMileageGreaterThan() {
        List<Car> res = carService.getCarsWithMileageGreaterThan(40000);
        assertEquals(List.of(car1, car2), res, "first and second");
    }
}