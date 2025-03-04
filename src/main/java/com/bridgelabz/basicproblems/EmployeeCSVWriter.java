package com.bridgelabz.basicproblems;


import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EmployeeCSVWriter {

    public static void writeCSV(String filePath) {
        String[] header = {"ID", "Name", "Department", "Salary"};
        String[][] employees = {
                {"101", "Anand", "HR", "50000"},
                {"102", "Avinash", "IT", "60000"},
                {"103", "Anshika", "Finance", "55000"},
                {"104", "Ankit", "Marketing", "58000"},
                {"105", "Aakrati", "Sales", "62000"}
        };

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeNext(header); // Write the header
            writer.writeAll(java.util.Arrays.asList(employees)); // Write all employee records
            System.out.println("CSV file written successfully!");
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "E:/Capg/Week5_IO_Programming/Day1/src/main/resources/employees.csv"; // Change if needed
        writeCSV(filePath);
    }
}
