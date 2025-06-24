package part_3;

 class EnrollmentDeniedException extends Exception {
    public EnrollmentDeniedException(String message) {
        super(message);
    }
}

public class CourseEnrollmentManager {

    public static void enrollStudent(String studentId, String courseId, EligibilityRule rule) throws EnrollmentDeniedException {
        System.out.println("Attempting to enroll " + studentId + " in " + courseId + "...");
        try {
            if (rule.isEligible(studentId, courseId)) {
                System.out.println("Enrollment successful for " + studentId + " in " + courseId + "! Happy learning!");
            }
        } catch (EligibilityRule.EnrollmentDeniedException e) {
            throw new EnrollmentDeniedException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        EligibilityRule rule = (studentId, courseId) -> {
            try {
                if (studentId.equals("SKILL999")) {
                    throw new EnrollmentDeniedException("Student account suspended due to outstanding fees, Roshan!");
                } else if (courseId.equals("JAVA101") && !studentId.startsWith("SKILL")) {
                    throw new EnrollmentDeniedException("Invalid student ID format. Please use 'SKILL' prefix, Anisha!");
                }
                return studentId.startsWith("SKILL") && courseId.equals("JAVA101");
            } catch (EnrollmentDeniedException e) {
                System.out.println("Enrollment failed for " + studentId + ": " + e.getMessage());
                return false;
            }
        };

        try {
            enrollStudent("SKILL123", "JAVA101", rule);   // Valid
        } catch (EnrollmentDeniedException e) {
            System.out.println("Enrollment failed for SKILL123: " + e.getMessage());
        }
        try {
            enrollStudent("SKILL999", "PYTHON202", rule);  // Suspended
        } catch (EnrollmentDeniedException e) {
            System.out.println("Enrollment failed for SKILL999: " + e.getMessage());
        }
        try {
            enrollStudent("STUDENT001", "JAVA101", rule);  // Invalid ID format
        } catch (EnrollmentDeniedException e) {
            System.out.println("Enrollment failed for STUDENT001: " + e.getMessage());
        }
        try {
            enrollStudent("SKILL456", "PYTHON202", rule);  // Not eligible
        } catch (EnrollmentDeniedException e) {
            System.out.println("Enrollment failed for SKILL456: " + e.getMessage());
        }
    }
}