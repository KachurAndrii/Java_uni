package lab_1.Classes;
import lombok.Builder;
import lombok.Data;

/**
 * Клас що представляє орендарів
 */
@Data
@Builder
public class Renter {
    private String lastName;      // Прізвище
    private String firstName;     // Ім'я
    private String documentId;    // Документ, що підтверджує особу
    private String drivingLicense; // Права водія
}
