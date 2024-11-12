package lab_3.Serializers;

import lab_3.Classes.*;
import lab_3.Service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class JSONSerializerTest {
    private JSONSerializer<Car> jsonSerializer;
    private Car car;
    @BeforeEach
    void setUp() {
        jsonSerializer = new JSONSerializer<Car>(Car.class);
        car = new Car("Toyota", "JTHBE96S280012345", "AB1234CD", LocalDate.parse("2018-06-15"), 50000, 49.9);

    }
    @Test
    void serialize() throws IOException {
        String jsonString = jsonSerializer.serialize(car);

        assertAll(() -> {
            assertNotNull(jsonString);
            assertTrue(jsonString.contains("""
                    "brand":"Toyota\""""), "Toyota expected");
            assertTrue(jsonString.contains("""
                    "vin":"JTHBE96S280012345\""""), "JTHBE96S280012345 expected");
            assertTrue(jsonString.contains("""
                    "plateNumber":"AB1234CD\""""), "AB1234CD expected");
            assertTrue(jsonString.contains("""
                    "releaseDate":"2018-06-15\""""), "2018-06-15 expected");
            assertTrue(jsonString.contains("""
                    "mileage":50000"""), "50000 expected");
            assertTrue(jsonString.contains("""
                    "pricePerDay":49.9"""), "49.9 expected");
        });
    }
}