/**
 * Student Name: Ryley Carlson
 * CSC320 Module 4 Critical Thinking Assignment 3 - Option #2
 * Program: GradeStatistic calculator
 * Description: This program prompts the user to enter ten floating-point grades (0-100) for students
 * calculates and displays various statistics such as average, maximum, minimum, and standard deviation.
 *  It also generates a grade distribution report with a bar chart representation.
 * The program includes input validation to ensure that only valid grades are accepted.
 */

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeStatistics {
    public static void main(String[] args) {
        int numGrades = 10;
        double[] grades = new double[numGrades];
        double sum = 0.0;
        DecimalFormat df = new DecimalFormat("0.00");
        Map<String, Integer> distributionMap = new LinkedHashMap<>();
        
        // Initialize the map to ensure order and default counts
        distributionMap.put("A", 0);
        distributionMap.put("A-", 0);
        distributionMap.put("B+", 0);
        distributionMap.put("B", 0);
        distributionMap.put("B-", 0);
        distributionMap.put("C+", 0);
        distributionMap.put("C", 0);
        distributionMap.put("D", 0);
        distributionMap.put("F", 0);

        System.out.println("--- Welcome to Ryley's Grade Statistics Calculator ---");
        System.out.println("Please enter ten floating-point grades ranging from (0-100) for each student:");

        try (Scanner scanner = new Scanner(System.in)) {
            for (int i = 0; i < numGrades; i++) {
                while (true) {
                    try {
                        System.out.print("Enter grade #" + (i + 1) + ": ");
                        double input = scanner.nextDouble();
                        if (input < 0 || input > 100) {
                            System.out.println("! The grade is out of range. Please enter 0-100.");
                            continue;
                        }
                        grades[i] = input;
                        sum += grades[i];
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("! This is an invalid input. Please enter a number ranging from 0 to 100.");
                        scanner.next();
                    }
                }
            }
        }

        // Calculations for Average and Standard Deviation
        double average = sum / numGrades;
        double varianceSum = 0.0;
        for (double grade : grades) varianceSum += Math.pow(grade - average, 2);
        double stdDev = Math.sqrt(varianceSum / numGrades);

        // Sorting (Ascending then reversing for Descending) Using Arrays.sort() and manual reversal for better readability
        Arrays.sort(grades);
        for (int i = 0; i < grades.length / 2; i++) {
            double temp = grades[i];
            grades[i] = grades[grades.length - 1 - i];
            grades[grades.length - 1 - i] = temp;
        }

        // Individual Report & Count Distribution
        System.out.println("\n--- Individual Grade Report (Sorted) ---");
        for (double grade : grades) {
            String letter = getLetterGrade(grade);
            System.out.println(df.format(grade) + "% [" + letter + "]");
            distributionMap.put(letter, distributionMap.get(letter) + 1);
        }

        // Final Statistics (Including Maximum and Minimum with their Letter Grades)
        System.out.println("\n--- Final Class Grade Statistics ---");
        System.out.println("The Average grade for the class is: " + df.format(average) + "% (" + getLetterGrade(average) + ")");
        System.out.println("The Maximum grade for the class is: " + df.format(grades[0]) + "% (" + getLetterGrade(grades[0]) + ")");
        System.out.println("The Minimum grade for the class is: " + df.format(grades[numGrades - 1]) + "% (" + getLetterGrade(grades[numGrades - 1]) + ")");
        System.out.println("The Standard deviation for the class is: " + df.format(stdDev));
        
        // Distribution & Bar Chart Output (Using LinkedHashMap to maintain order)
        System.out.println("\n--- Grade Distribution (Bar Chart) ---");
        printBarChart(distributionMap);
        System.out.println("----------------------------");
    }

    public static String getLetterGrade(double score) {
        if (score >= 95.0) return "A";
        else if (score >= 90.0) return "A-";
        else if (score >= 86.7) return "B+";
        else if (score >= 83.3) return "B";
        else if (score >= 80.0) return "B-";
        else if (score >= 75.0) return "C+";
        else if (score >= 70.0) return "C";
        else if (score >= 60.0) return "D";
        else return "F";
    }

    public static void printBarChart(Map<String, Integer> distribution) {
        for (Map.Entry<String, Integer> entry : distribution.entrySet()) {
            String label = entry.getKey();
            int count = entry.getValue();
            System.out.printf("%-3s [%d]: ", label, count);
            for (int i = 0; i < count; i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}

