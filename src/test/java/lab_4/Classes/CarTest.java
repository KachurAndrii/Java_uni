package lab_4.Classes;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class CarTest {
    @Test
    void build() {

        Car car = new Car.CarBuilder()
                .SetBrand("Toyota")
                .setVin("JTHBE96S280012345")
                .setPlateNumber("AB1234CD")
                .setReleaseDate(LocalDate.parse("2018-06-15"))
                .setPricePerDay(49.9)
                .setMileage(50000)
                .build();
        assertAll(() -> {
            assertEquals("Toyota", car.getBrand());
            assertEquals("JTHBE96S280012345", car.getVin());
            assertEquals("AB1234CD", car.getPlateNumber());
            assertEquals(LocalDate.parse("2018-06-15"), car.getReleaseDate());
            assertEquals(49.9, car.getPricePerDay());
            assertEquals(50000, car.getMileage());

        });
    }
}