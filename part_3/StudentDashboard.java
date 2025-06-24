package part_3;

public class StudentDashboard {

    public void displayCourseStatus(String studentId, String courseId, EligibilityRule rule) {
        System.out.println("Checking enrollment status for " + studentId + " in " + courseId + "...");
        try {
            if (rule.isEligible(studentId, courseId)) {
                System.out.println("You are enrolled! Access course materials now.");
            }
        } catch (EligibilityRule.EnrollmentDeniedException e) {
            System.out.println("Enrollment denied: " + e.getMessage() + ". Please contact support.");
        } finally {
            System.out.println("Status check completed for " + studentId + ".");
        }
    }

    public static void main(String[] args) {
        StudentDashboard dashboard = new StudentDashboard();

        EligibilityRule rule = (studentId, courseId) -> {
            if (studentId.equals("SKILL999")) {
                throw new EligibilityRule.EnrollmentDeniedException("Student account suspended due to outstanding fees, Roshan!");
            } else if (courseId.equals("JAVA101") && !studentId.startsWith("SKILL")) {
                throw new EligibilityRule.EnrollmentDeniedException("Invalid student ID format. Please use 'SKILL' prefix, Anisha!");
            }
            return studentId.startsWith("SKILL") && courseId.equals("JAVA101");
        };

        dashboard.displayCourseStatus("SKILL123", "JAVA101", rule);   // Valid
        dashboard.displayCourseStatus("SKILL999", "PYTHON202", rule);  // Suspended
        dashboard.displayCourseStatus("STUDENT001", "JAVA101", rule);  // Invalid ID format
    }
}