// DeusiBhailo.java

import java.util.*;

 class NoRouteException extends FestivalPlanningException {
    public NoRouteException(String message) {
        super(message);
    }
}

public class DeusiBhailo extends FestivalActivity {
    List<String> plannedRoutes;
    int numberOfPerformers;

    public DeusiBhailo(double estimatedCost, List<String> plannedRoutes, int numberOfPerformers) {
        super("Deusi Bhailo Program", estimatedCost);
        this.plannedRoutes = plannedRoutes;
        this.numberOfPerformers = numberOfPerformers;
    }

    @Override
    public void planActivity() throws FestivalPlanningException {
        if (plannedRoutes.isEmpty()) {
            throw new NoRouteException("No routes planned for Deusi Bhailo! Are we just singing in the living room?");
        }
        if (numberOfPerformers < 3) {
            throw new FestivalPlanningException("Need at least 3 performers for a proper Deusi Bhailo!");
        }
        System.out.println("Deusi Bhailo program with " + numberOfPerformers + " performers planned for " + plannedRoutes.size() + " routes!");
    }

    public static void main(String[] args) {
        List<String> routes1 = Arrays.asList("Street A", "Street B");
        List<String> routes2 = new ArrayList<>();
        List<String> routes3 = Arrays.asList("Main Road");

        DeusiBhailo[] programs = {
            new DeusiBhailo(20000, routes1, 5),
            new DeusiBhailo(15000, routes2, 4),
            new DeusiBhailo(10000, routes3, 2)
        };

        for (DeusiBhailo db : programs) {
            try {
                db.displayOverview();
                db.planActivity();
            } catch (NoRouteException e) {
                System.out.println("Route Error: " + e.getMessage());
            } catch (FestivalPlanningException e) {
                System.out.println("Festival Error: " + e.getMessage());
            }
            System.out.println();
        }
    }
}