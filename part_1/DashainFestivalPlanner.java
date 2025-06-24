// DashainFestivalPlanner.java

import java.util.*;

public class DashainFestivalPlanner {

    public static void executeFestivalPlan(List<FestivalActivity> activities) {
        for (FestivalActivity activity : activities) {
            activity.displayOverview();
            try {
                activity.planActivity();
            } catch (InvalidGuestCountException e) {
                System.out.println("Planning Warning (Guests): " + e.getMessage());
            } catch (BudgetExceededException e) {
                System.out.println("Planning Warning (Budget): " + e.getMessage());
            } catch (NoRouteException e) {
                System.out.println("Planning Warning (Routes): " + e.getMessage());
            } catch (FestivalPlanningException e) {
                System.out.println("General Planning Error: " + e.getMessage());
            } finally {
                System.out.println("Activity planning attempt for " + activity.activityName + " completed.");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        List<FestivalActivity> activities = new ArrayList<>();

        activities.add(new TikaCeremony(40000, 10, "Grandfather"));
        activities.add(new TikaCeremony(60000, 12, "Father"));
        activities.add(new TikaCeremony(30000, 2, "Uncle"));

        List<String> routes1 = Arrays.asList("Street A", "Street B");
        List<String> routes2 = new ArrayList<>();
        List<String> routes3 = Arrays.asList("Street X");

        activities.add(new DeusiBhailo(25000, routes1, 5));
        activities.add(new DeusiBhailo(18000, routes2, 4));
        activities.add(new DeusiBhailo(12000, routes3, 2));

        executeFestivalPlan(activities);
    }
}