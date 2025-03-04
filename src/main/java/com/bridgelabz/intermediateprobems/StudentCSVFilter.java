package com.bridgelabz.intermediateprobems;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class StudentCSVFilter {

    public static void filterStudents(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            if (records.isEmpty()) {
                System.out.println("CSV file is empty!");
                return;
            }

            System.out.println("Students with marks > 80:");
            System.out.println("--------------------------------");

            // Skip the header row
            for (int i = 1; i < records.size(); i++) {
                String[] record = records.get(i);
                if (record.length < 4) continue; // Skip malformed rows

                try {
                    int marks = Integer.parseInt(record[3]); // Marks column
                    if (marks > 80) {
                        System.out.println("ID: " + record[0] + ", Name: " + record[1] + ", Marks: " + marks);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Invalid marks data: " + record[3]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String filePath = "E:/Capg/Week5_IO_Programming/Day1/src/main/resources/Book1.csv";
        filterStudents(filePath);
    }
}

