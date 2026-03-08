/**
 * Student Name: Ryley Carlson
 * CSC320 Module 4 Critical Thinking Assignment 3 - Option #2
 * Program: GradeStatistic calculator
 * Description: This program prompts the user to enter ten grades (0-100) and calculates the average, maximum, and minimum grades.
 * The program ensures that all inputs are valid and provides formatted output for the statistics.
 */

import java.text.DecimalFormat;
import java.util.Scanner;

public class GradeStatistics {
    public static void main(String[] args) {
        int numGrades = 10;
        double sum = 0.0;
        // Initialize max and min to extreme values for comparison
        double max = -1.0;
        double min = 101.0;
        
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("--- Ryley Carlson's Grade Statistics Calculator ---");
        System.out.println("Please enter ten grades (0-100):");

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < numGrades; i++) {
                boolean validInput = false;
                while (!validInput) {
                    System.out.print("Enter grade #" + (i + 1) + ": ");
                    if (scanner.hasNextDouble()) {
                        double input = scanner.nextDouble();
                        if (input >= 0 && input <= 100) {
                            // Valid input, update statistics
                            sum += input;
                            if (input > max) max = input;
                            if (input < min) min = input;
                            validInput = true;
                        } else {
                            System.out.println("! Out of range. Enter 0-100.");
                        }
                    } else {
                        System.out.println("! Invalid input. Please enter a number.");
                        scanner.next(); // Clear the invalid input
                    }
                }
            }

            double average = sum / numGrades;

            System.out.println("\n--- Final Class Grade Statistics ---");
            System.out.println("Average: " + df.format(average) + "%");
            System.out.println("Maximum: " + df.format(max) + "%");
            System.out.println("Minimum: " + df.format(min) + "%");
        }
    }
}