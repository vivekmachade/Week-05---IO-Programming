package com.bridgelabz.basicproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class StudentCSVReader {

    public static void readCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            System.out.println("Student Details:");
            System.out.println("------------------------------");

            for (String[] record : records) {
                System.out.println("ID: " + record[0]);
                System.out.println("Name: " + record[1]);
                System.out.println("Age: " + record[2]);
                System.out.println("Marks: " + record[3]);
                System.out.println("------------------------------");
            }
        } catch (IOException | CsvException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String filePath = "E:/Capg/Week5_IO_Programming/Day1/src/main/resources/Book1.csv"; // Update with actual path
        readCSV(filePath);
    }
}
