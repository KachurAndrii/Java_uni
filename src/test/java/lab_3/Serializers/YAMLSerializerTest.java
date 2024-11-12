package lab_3.Serializers;

import lab_3.Classes.Car;
import lab_3.Service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class YAMLSerializerTest {
    private YAMLSerializer<Car> yamlSerializer;
    private Car car;
    private CarService carService;
    @BeforeEach
    void setUp() {
        yamlSerializer = new YAMLSerializer<>(Car.class);
        car = new Car("Toyota", "JTHBE96S280012345", "AB1234CD", LocalDate.parse("2018-06-15"), 50000, 49.9 );

    }
    @Test
    void serialize() throws IOException {
        String yamlString = yamlSerializer.serialize(car);

        assertAll(() -> {
            assertNotNull(yamlString);
            assertTrue(yamlString.contains("""
                    brand: "Toyota\""""), "Toyota expected");
            assertTrue(yamlString.contains("""
                    vin: "JTHBE96S280012345\""""), "JTHBE96S280012345 expected");
            assertTrue(yamlString.contains("""
                    plateNumber: "AB1234CD\""""), "AB1234CD expected");
            assertTrue(yamlString.contains("""
                    releaseDate: "2018-06-15\""""), "2018-06-15 expected");
            assertTrue(yamlString.contains("""
                    mileage: 50000"""), "50000 expected");
            assertTrue(yamlString.contains("""
                    pricePerDay: 49.9"""), "49.9 expected");

        });
    }
}