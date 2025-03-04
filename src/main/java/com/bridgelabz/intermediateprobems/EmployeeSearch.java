package com.bridgelabz.intermediateprobems;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class EmployeeSearch {

    public static void searchEmployee(String filePath, String searchName) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            if (records.isEmpty()) {
                System.out.println("CSV file is empty!");
                return;
            }

            boolean found = false;
            // Skip the header row and search for the employee
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                if (record.length < 4) continue; // Skip malformed rows

                String name = record[1];
                if (name.equalsIgnoreCase(searchName)) {
                    System.out.println("Employee Found:");
                    System.out.println("Name: " + name);
                    System.out.println("Department: " + record[2]);
                    System.out.println("Salary: " + record[3]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Employee not found!");
            }

        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String filePath = "E:/Capg/Week5_IO_Programming/Day1/src/main/resources/employees.csv";
        String searchName = "Anand";
        searchEmployee(filePath, searchName);
    }
}
