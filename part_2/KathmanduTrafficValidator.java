// KathmanduTrafficValidator.java

package part_2;

// Define custom exceptions if not already defined
class SameLocationException extends Exception {
    public SameLocationException(String message) {
        super(message);
    }
}

class InvalidRouteException extends Exception {
    public InvalidRouteException(String message) {
        super(message);
    }
}

// Define RouteValidator interface if not already defined
interface RouteValidator {
    boolean isValidCommuteRoute(String origin, String destination, double distanceKm)
            throws SameLocationException, InvalidRouteException;
}

public class KathmanduTrafficValidator implements RouteValidator {
    @Override
    public boolean isValidCommuteRoute(String origin, String destination, double distanceKm)
            throws SameLocationException, InvalidRouteException {
        if (origin.equalsIgnoreCase(destination)) {
            throw new SameLocationException("Origin and destination cannot be the same! Are you just spinning in circles, Damodar?");
        }
        if (distanceKm < 0.1 || distanceKm > 30) {
            throw new InvalidRouteException("Distance " + distanceKm + "km is unrealistic for Kathmandu commute!");
        }
        return true;
    }

    public static void main(String[] args) {
        KathmanduTrafficValidator validator = new KathmanduTrafficValidator();
        try {
            validator.isValidCommuteRoute("Baneshwor", "Baneshwor", 2);
        } catch (SameLocationException | InvalidRouteException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        try {
            validator.isValidCommuteRoute("Thamel", "Patan", 35);
        } catch (SameLocationException | InvalidRouteException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        try {
            validator.isValidCommuteRoute("Kalimati", "Putalisadak", 0.05);
        } catch (SameLocationException | InvalidRouteException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }

        try {
            if (validator.isValidCommuteRoute("Thamel", "Patan", 5)) {
                System.out.println("Route is valid!");
            }
        } catch (SameLocationException | InvalidRouteException e) {
            System.out.println("Validation failed: " + e.getMessage());
        }
    }
}