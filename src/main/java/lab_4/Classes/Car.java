package lab_4.Classes;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import jakarta.validation.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;
import java.util.Set;
import static jakarta.validation.Validation.buildDefaultValidatorFactory;
/**
 * Клас що представляє автомобілі
 */
public class Car {
    private String brand;       // Марка машини
    private String vin;         // VIN код
    private String plateNumber; // Номер машини
    private LocalDate releaseDate; // Дата випуску
    private int mileage;        // Пробіг
    protected double pricePerDay;    // Ціна за день

    // @Constructor
    private Car(Builder builder) {
        this.brand = builder.brand;
        this.vin = builder.vin;
        this.plateNumber = builder.plateNumber;
        this.releaseDate = builder.releaseDate;
        this.mileage = builder.mileage;
        this.pricePerDay = builder.pricePerDay;
    }

    public Car(String brand, String vin, String plateNumber, LocalDate releaseDate, int mileage, double pricePerDay) {
        this.brand = brand;
        this.vin = vin;
        this.plateNumber = plateNumber;
        this.releaseDate = releaseDate;
        this.pricePerDay = pricePerDay;
        this.mileage = mileage;
    }

    // @Getter для кожного поля
    public String getBrand() {
        return brand;
    }

    public String getVin() {
        return vin;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", vin='" + vin + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", releaseDate=" + releaseDate +
                ", mileage=" + mileage +
                ", pricePerDay=" + pricePerDay +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return mileage == car.mileage &&
                Objects.equals(brand, car.brand) &&
                Objects.equals(vin, car.vin) &&
                Objects.equals(plateNumber, car.plateNumber) &&
                Objects.equals(releaseDate, car.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, vin, plateNumber, releaseDate, mileage);
    }

    /**
     * @Builder class for Car.
     */
    public static class Builder {
        private String brand;
        private String vin;
        private String plateNumber;
        private LocalDate releaseDate;
        private int mileage;

        protected double pricePerDay;

        public class CarBuilder {
            @NotNull(message = "Make cannot be null")
            @Length(min = 3, max = 30, message = "Make must be between 3 and 30 characters")
            private String brand;

            @Length(min = 17, message = "Vin is too short, minimum 17 characters is required")
            @Length(max = 17, message = "Vin is too long, maximum 17 characters is required")
            @NotNull(message = "vin cannot be null")
            @NotEmpty(message = "vin cannot be empty string")
            private String vin;

            @NotNull(message = "plateNumber cannot be null")
            @NotEmpty(message = "plateNumber cannot be empty string")
            @Length(min = 6, message = "plateNumber is too short, minimum 6 characters is required")
            @Length(max = 8, message = "plateNumber is too long, maximum 8 characters is required")
            private String plateNumber;

            @NotNull(message = "releaseDate cannot be null")
            // перевірка дати (завтра не може бути)
            private LocalDate releaseDate;

            @NotNull(message = "pricePerDay cannot be null")
            @Min(value = 0, message = "pricePerDay cannot be less than 0")
            private double pricePerDay;

            @NotNull(message = "mileage cannot be null")
            @Min(value = 0, message = "mileage cannot be less than 0")
            @Max(value = 100_000, message = "mileage cannot be greater than 100_000")
            private int mileage;


            public CarBuilder setMake(String brand) {
                this.brand = brand;
                return this;
            }

            public CarBuilder setVin(String vin) {
                this.vin = vin;
                return this;
            }
            public CarBuilder setPlateNumber(String plateNumber) {
                this.plateNumber = plateNumber;
                return this;
            }

            public CarBuilder setReleaseDate(LocalDate releaseDate) {
                this.releaseDate = releaseDate;
                return this;
            }

            public CarBuilder setPricePerDay(double pricePerDay) {
                this.pricePerDay = pricePerDay;
                return this;
            }

            public CarBuilder setMileage(int mileage) {
                this.mileage = mileage;
                return this;
            }


            public Car build() {
                ValidatorFactory factory = buildDefaultValidatorFactory();
                Validator validator = factory.getValidator();
                Car car = new Car(this.brand, this.vin, this.plateNumber, this.releaseDate, this.mileage, this.pricePerDay);

                Set<ConstraintViolation<CarBuilder>> violations = validator.validate(this);
                if (!violations.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (ConstraintViolation<CarBuilder> violation : violations) {
                        sb
                                .append("\nField: ")
                                .append(violation.getPropertyPath())
                                .append("\nInvalid value: ")
                                .append(violation.getInvalidValue())
                                .append("\nProblem: ")
                                .append(violation.getMessage())
                                .append("\n");
                    }
                    throw new IllegalArgumentException(sb.toString());
                }
                return car;
            }
        }

    }
}
