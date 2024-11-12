package lab_3.Serializers;

import lab_3.Classes.Car;
import lab_3.Service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class XMLSerializerTest {
    private XMLSerializer<Car> xmlSerializer;
    private Car car;
    private CarService carService;
    @BeforeEach
    void setUp() {
        xmlSerializer = new XMLSerializer<Car>(Car.class);
        car = new Car("Toyota", "JTHBE96S280012345", "AB1234CD", LocalDate.parse("2018-06-15"), 50000, 49.9);

    }
    @Test
    void serialize() throws IOException {
        String xmlString = xmlSerializer.serialize(car);

        assertAll(() -> {
            assertNotNull(xmlString);
            assertTrue(xmlString.contains("<brand>Toyota</brand>"), "Toyota expected");
            assertTrue(xmlString.contains("<vin>JTHBE96S280012345</vin>"), "JTHBE96S280012345 expected");
            assertTrue(xmlString.contains("<plateNumber>AB1234CD</plateNumber>"), "AB1234CD expected");
            assertTrue(xmlString.contains("<releaseDate>2018-06-15</releaseDate>"), "2018-06-15 expected");
            assertTrue(xmlString.contains("<mileage>50000</mileage>"), "50000 expected");
            assertTrue(xmlString.contains("<pricePerDay>49.9</pricePerDay>"), "49.9 expected");

        });
    }
}