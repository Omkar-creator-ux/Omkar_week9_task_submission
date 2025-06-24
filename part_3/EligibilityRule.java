package part_3;

@FunctionalInterface
public interface EligibilityRule {
    boolean isEligible(String studentId, String courseId) throws EnrollmentDeniedException;

    // Custom checked exception for enrollment denial
    public static class EnrollmentDeniedException extends Exception {
        public EnrollmentDeniedException(String message) {
            super(message);
        }
    }

    // Example usage (inside a main method or external class)
    public static void main(String[] args) {
        EligibilityRule rule = (studentId, courseId) -> {
            if (studentId.equals("SKILL999")) {
                throw new EnrollmentDeniedException("Student account suspended due to outstanding fees.");
            } else if (courseId.equals("JAVA101") && !studentId.startsWith("SKILL")) {
                throw new EnrollmentDeniedException("Invalid student ID format. Please use 'SKILL' prefix.");
            }
            return studentId.startsWith("SKILL") && courseId.equals("JAVA101");
        };

        try {
            System.out.println(rule.isEligible("SKILL123", "JAVA101"));  // True
            System.out.println(rule.isEligible("STUDENT001", "JAVA101"));  // Exception: Invalid ID format
        } catch (EnrollmentDeniedException e) {
            System.out.println("Enrollment Denied: " + e.getMessage());
        }
    }
}