package lab_2.Classes;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

/**
 * Клас що представляє оренду
 */
@Data
@Builder
public class CarRental {
    private Car car;          // Інформація про машину
    private Renter renter;    // Інформація про орендаря
    private String pickupLocation; // Локація, де забирають авто
    private String dropoffLocation; // Локація, куди доставляють авто
    private LocalDate startDate;   // Дата початку оренди
    private LocalDate endDate;     // Дата закінчення оренди
    private double totalPrice;     // Загальна ціна

    /**
     * Рахує загальну вартість оренди
     */
    public void calculateTotalPrice() {
        long rentalDays = startDate.until(endDate).getDays();
        this.totalPrice = rentalDays * car.pricePerDay;
    }
}
