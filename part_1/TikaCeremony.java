// TikaCeremony.java

 class InvalidGuestCountException extends FestivalPlanningException {
    public InvalidGuestCountException(String message) {
        super(message);
    }
}

 class BudgetExceededException extends FestivalPlanningException {
    public BudgetExceededException(String message) {
        super(message);
    }
}

public class TikaCeremony extends FestivalActivity {
    int expectedGuests;
    String mainFamilyElder;

    public TikaCeremony(double estimatedCost, int expectedGuests, String mainFamilyElder) {
        super("Tika Ceremony", estimatedCost);
        this.expectedGuests = expectedGuests;
        this.mainFamilyElder = mainFamilyElder;
    }

    @Override
public void planActivity() throws FestivalPlanningException {
        if (expectedGuests < 5) {
            throw new InvalidGuestCountException("Not enough guests for a lively Tika! Is everyone on vacation?");
        }
        if (estimatedCost > 50000) {
            throw new BudgetExceededException("Tika budget too high! Is this for the whole village?");
        }
        System.out.println("Tika ceremony with " + mainFamilyElder + " planned successfully for " + expectedGuests + " guests!");
    }

    public static void main(String[] args) {
        TikaCeremony[] ceremonies = {
            new TikaCeremony(40000, 10, "Grandfather"),
            new TikaCeremony(60000, 15, "Uncle"),
            new TikaCeremony(30000, 3, "Aunt")
        };

        for (TikaCeremony tc : ceremonies) {
            try {
                tc.displayOverview();
                tc.planActivity();
            } catch (InvalidGuestCountException e) {
                System.out.println("Guest Error: " + e.getMessage());
            } catch (BudgetExceededException e) {
                System.out.println("Budget Error: " + e.getMessage());
            } catch (FestivalPlanningException e) {
                System.out.println("Festival Error: " + e.getMessage());
            }
            System.out.println();
        }
    }
}

