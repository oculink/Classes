import java.util.Scanner;

public class StudentGradingSystem {
    
    public static char getGrade(double mark) {
        if (mark >= 80) return 'A';
        else if (mark >= 70) return 'B';
        else if (mark >= 60) return 'C';
        else if (mark >= 50) return 'D';
        else return 'F';
    }
    
    public static boolean isPassed(char grade) {
        return grade != 'F';
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice = "yes";
        
        do {
            System.out.println("\n========================================");
            System.out.println("   STUDENT MARKS & GRADING SYSTEM");
            System.out.println("========================================\n");
            
            System.out.print("Enter the number of students: ");
            int numStudents = sc.nextInt();
            
            if (numStudents <= 0) {
                System.out.println("Invalid number of students! Please enter a positive number.");
                continue;
            }

            double[] marks = new double[numStudents];
            char[] grades = new char[numStudents];
            
            int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;
            int passed = 0, failed = 0;
            
            System.out.println("\n--- Enter Student Marks ---");
            for (int i = 0; i < numStudents; i++) {
                System.out.print("Enter mark for Student " + (i + 1) + " (0-100): ");
                marks[i] = sc.nextDouble();
                
                while (marks[i] < 0 || marks[i] > 100) {
                    System.out.print("Invalid mark! Enter mark (0-100): ");
                    marks[i] = sc.nextDouble();
                }
                
                grades[i] = getGrade(marks[i]);
                
                switch (grades[i]) {
                    case 'A': countA++; break;
                    case 'B': countB++; break;
                    case 'C': countC++; break;
                    case 'D': countD++; break;
                    case 'F': countF++; break;
                }

                if (isPassed(grades[i])) {
                    passed++;
                } else {
                    failed++;
                }
                
                System.out.println("   Grade: " + grades[i]);
            }

            // Psstt... why are you reading this.... hmmmm...
            // Well if you were trying to find an easter egg in this code, well you found one! Congrats
            // Open Source i guess -oculink
            
            System.out.println("\n========================================");
            System.out.println("           GRADING SUMMARY");
            System.out.println("========================================");
            
            System.out.println("\n--- Grade Distribution ---");
            System.out.println("Grade A: " + countA + " student(s)");
            System.out.println("Grade B: " + countB + " student(s)");
            System.out.println("Grade C: " + countC + " student(s)");
            System.out.println("Grade D: " + countD + " student(s)");
            System.out.println("Grade F: " + countF + " student(s)");
            
            System.out.println("\n--- Pass/Fail Statistics ---");
            System.out.println("Students Passed: " + passed);
            System.out.println("Students Failed: " + failed);
            
            System.out.println("\n--- Result ---");
            if (passed > failed) {
                System.out.println("BONUS TO INSTRUCTOR");
                System.out.println("  (More students passed than failed!)");
            } else if (failed > passed) {
                System.out.println("NO BONUS TO INSTRUCTOR");
                System.out.println("  (More students failed than passed!)");
            } else {
                System.out.println("⚖ EQUAL NUMBER OF PASS AND FAIL");
                System.out.println("  (Same number of students passed and failed!)");
            }
            
            System.out.println("\n========================================");
            
            System.out.print("\nDo you want to continue? (yes/no): ");
            choice = sc.next().toLowerCase();
            
        } while (choice.equals("yes") || choice.equals("y"));
        
        System.out.println("\n========================================");
        System.out.println("Thank you for using the Grading System!");
        System.out.println("========================================\n");
        
        sc.close();
    }
}