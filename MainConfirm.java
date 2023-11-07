import java.util.Scanner;
import java.util.ArrayList;

public class SkywardMain {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        // Variables to keep track of students, classes, and user input
        int studenttotal = 0;
        int classtotal = 0;
        int studentID;
        int classID;
        int error = 0; // Variable to handle errors
        ArrayList<String> Students = new ArrayList<>(); // ArrayList to store student names
        ArrayList<String> Classes = new ArrayList<>(); // ArrayList to store class names
        int choice;
        SkywardStudent school = new SkywardStudent(); // Instance of SkywardStudent class for student management

        do {
            // Display the main menu
            System.out.println("====================Skyward=====================");
            System.out.println("");
            // Iterate over students and display their classes
            for (int i = 0; i < studenttotal; i++) {
                System.out.println("\t" + (i + 1) + ". " + Students.get(i));
                int numClasses = school.getClasstotal(i);
                // Iterate over classes for each student and display class details
                for (int k = 0; k < numClasses; k++) {
                    classID = school.getClassID(i, k);
                    int grade = school.getGrade(i, classID);
                    int attendance = school.getAttendance(i, classID);
                    System.out.println("\t\t" + Classes.get(classID) + " (Grade: " + grade + ", Attendance: " + attendance + ")");
                }
            }

            // Display existing classes
            System.out.println("\n------------------------------------------------");
            for (int i = 0; i < classtotal; i++) {
                System.out.println("\t" + (i + 1) + ". " + Classes.get(i));
            }

            System.out.println("================================================");

            // Display error messages if applicable
            switch (error) {
                case 1:
                    System.out.println("No student exists.");
                    error = 0;
                    break;
                case 2:
                    System.out.println("No class exists.");
                    error = 0;
                    break;
            }

            // Display user options
            System.out.println("");
            System.out.println("1. Add a student");
            System.out.println("2. Enroll a student in a class");
            System.out.println("3. Assign grade to a student's class");
            System.out.println("4. Edit a student's Attendance");
            System.out.println("5. Create a Class");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            // Read user choice
            choice = reader.nextInt();

            // Handle user choices
            switch (choice) {
                case 1:
                    // Add a new student
                    System.out.print("Enter the student's name: ");
                    String name = reader.next();
                    Students.add(name);
                    studenttotal++;
                    school.giveBirth();
                    break;
                case 2:
                    // Enroll a student in a class
                    if (studenttotal == 0) {
                        error = 1;
                    } else if (classtotal == 0) {
                        error = 2;
                    } else {
                        System.out.print("Enter the student's ID: ");
                        studentID = reader.nextInt() - 1;
                        System.out.print("Enter the class's ID: ");
                        classID = reader.nextInt() - 1;
                        school.enrollStudent(studentID, classID);
                    }
                    break;
                case 3:
                    // Assign grade to a student's class
                    if (studenttotal == 0) {
                        error = 1;
                    } else if (classtotal == 0) {
                        error = 2;
                    } else {
                        System.out.print("Enter the student's ID: ");
                        studentID = reader.nextInt() - 1;
                        System.out.print("Enter the class's ID: ");
                        classID = reader.nextInt() - 1;
                        System.out.print("Enter the percent grade you would like to change to: ");
                        int grade = reader.nextInt();
                        school.changeGrade(studentID, grade, classID);
                    }
                    break;
                case 4:
                    // Edit a student's Attendance
                    if (studenttotal == 0) {
                        error = 1;
                    } else if (classtotal == 0) {
                        error = 2;
                    } else {
                        System.out.print("Enter the student's ID: ");
                        studentID = reader.nextInt() - 1;
                        System.out.print("Enter the class's ID: ");
                        classID = reader.nextInt() - 1;
                        System.out.print("Enter the number of tardies the student has in this class:");
                        int attendance = reader.nextInt();
                        school.changeAttendance(studentID, attendance, classID);
                    }
                    break;
                case 5:
                    // Create a new class
                    System.out.print("Enter the class's name: ");
                    String classname = reader.next();
                    Classes.add(classname);
                    classtotal++;
                    break;
                case 6:
                    // Exit the program
                    System.exit(0);
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }

            // Clear the console for a cleaner interface
            clearConsole();
        } while (choice != 6); // Continue until the user chooses to exit
    }

    // Method to clear the console
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
