package GPACalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class gpacalculator {
    public static void runGPAProgram() {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = new ArrayList<>();

        System.out.println("Welcome to the GPA Calculator!");

        System.out.print("How many courses did you take? ");
        int numberCourses = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        for(int i = 0; i < numberCourses; i++) {
            Course course = new Course();

            // Get user input for course details
            System.out.print("Enter course code: ");
            String code = scanner.nextLine();
            course.setCode(code);

            System.out.print("Enter course units: ");
            int units = scanner.nextInt();
            course.setUnits(units);

            System.out.print("Enter course score: ");
            double courseScore = scanner.nextDouble();
            course.setGrade(determineGrade(courseScore));

            // Add the course to the list
            courses.add(course);
            scanner.nextLine(); // Consume the newline character
        }
            // Create an instance of GPACalculator.GPACalculator
            gpacalculator gpaCalculator = new gpacalculator();

            // Display recorded course information in a well-divided table
            gpaCalculator.displayCourseTable(courses);

            // Calculate GPA and display the result
            double gpa = gpaCalculator.calculateGPA(courses);
            gpaCalculator.displayGPAInfo(courses, gpa);

            // Close the scanner
            scanner.close();
        }



    // Function to calculate GPA based on the list of courses
    private double calculateGPA(List<Course> courses) {
        int totalUnits = calculateTotalUnits(courses);
        double totalWeightedScore = 0.0;

        for (Course course : courses) {
            totalWeightedScore += (course.getScore() * course.getUnits());
        }

        if (totalUnits == 0) {
            return 0.0; // Avoid division by zero
        }
        return totalWeightedScore / totalUnits;
    }

    // Function to calculate total units based on the list of courses
    private int calculateTotalUnits(List<Course> courses) {
        int totalUnits = 0;
        for (Course course : courses) {
            totalUnits += course.getUnits();
        }
        return totalUnits;
    }

    // Function to determine grade based on score
    private static String determineGrade(double score) {
        if (score >= 70 && score <= 100) {
            return "A";
        } else if (score >= 60 && score < 70) {
            return "B";
        } else if (score >= 50 && score < 60) {
            return "C";
        } else if (score >= 45 && score < 50) {
            return "D";
        } else if (score >= 40 && score < 45) {
            return "E";
        } else if (score >= 0 && score < 40) {
            return "Fail";
        } else {
            return "Invalid Score";
        }
    }

    // Display recorded course information in a well-divided table
    private void displayCourseTable(List<Course> courses) {
        System.out.println("\nRecorded Courses:");
        System.out.println("|----------------------|---------------|---------|---------------|");
        System.out.println("| COURSE CODE          | COURSE UNIT   | GRADE   | GRADE UNIT    |");
        System.out.println("|----------------------|---------------|---------|---------------|");

        for (Course course : courses) {
            System.out.printf("|  %-20s | %-13d|  %-7s| %-13d|  \n",
                    course.getCode(),
                    course.getUnits(),
                    course.getGrade(),
                    course.calculateGradeUnits());
        }

        System.out.println("|----------------------|---------------|---------|---------------|");
    }

    // Display GPA information
    private void displayGPAInfo(List<Course> courses, double gpa) {
        System.out.println("\nGPA Information:");
        System.out.printf("Total Units: %d\n", calculateTotalUnits(courses));
        System.out.printf("GPA: %.2f\n", gpa);
    }
}