package lab_2.Service;
import lab_2.Classes.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for operations with Renter collections.
 */
public class RenterService {

    /**
     * Filter renters by driving license.
     *
     * @param renters the list of renters
     * @param drivingLicense the driving license to filter by
     * @return a list of renters with the specified driving license
     */
    public List<Renter> filterRentersByDrivingLicense(List<Renter> renters, String drivingLicense) {
        return renters.stream()
                .filter(renter -> renter.getDrivingLicense().equals(drivingLicense))
                .collect(Collectors.toList());
    }

    /**
     * Get renters whose last names start with a specific letter.
     *
     * @param renters the list of renters
     * @param letter the starting letter
     * @return a list of renters whose last names start with the specified letter
     */
    public List<Renter> getRentersByLastNameStartingWith(List<Renter> renters, String letter) {
        return renters.stream()
                .filter(renter -> renter.getLastName().startsWith(letter))
                .collect(Collectors.toList());
    }

    /**
     * Sort renters by last name in alphabetical order.
     *
     * @param renters the list of renters
     * @return a sorted list of renters by last name
     */
    public List<Renter> sortRentersByLastName(List<Renter> renters) {
        return renters.stream()
                .sorted((renter1, renter2) -> renter1.getLastName().compareTo(renter2.getLastName()))
                .collect(Collectors.toList());
    }
}
