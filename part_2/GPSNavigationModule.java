package part_2;

public class GPSNavigationModule implements NavigationService {
    @Override
    public void navigate(String startPoint, String endPoint, RouteValidator validator)
            throws NavigationFailedException {
        System.out.println("Attempting to navigate from " + startPoint + " to " + endPoint + "...");
        if (startPoint.equalsIgnoreCase("Kalanki")) {
            throw new NavigationFailedException("GPS signal lost near Kalanki! Welcome to Kathmandu traffic!");
        }

        try {
            double distance = simulatedDistance(startPoint, endPoint);
            if (validator.isValidCommuteRoute(startPoint, endPoint, distance)) {
                System.out.println("Navigation successful! Estimated time: 20 minutes (or 2 hours depending on traffic).");
            }
        } catch (SameLocationException | InvalidRouteException e) {
            throw new NavigationFailedException("Route validation failed!");
        }
    }

    private double simulatedDistance(String start, String end) {
        return 5.0;
    }

    public static void main(String[] args) {
        GPSNavigationModule gps = new GPSNavigationModule();
        KathmanduTrafficValidator validator = new KathmanduTrafficValidator();

        try {
            gps.navigate("Kalanki", "Balaju", validator);
        } catch (NavigationFailedException e) {
            System.out.println("Navigation failed: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Cause: " + e.getCause().getMessage());
            }
        }

        try {
            gps.navigate("Thamel", "Thamel", validator);
        } catch (NavigationFailedException e) {
            System.out.println("Navigation failed: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Cause: " + e.getCause().getMessage());
            }
        }

        try {
            gps.navigate("Baneshwor", "Patan", validator);
        } catch (NavigationFailedException e) {
            System.out.println("Navigation failed: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("Cause: " + e.getCause().getMessage());
            }
        }
    }
}