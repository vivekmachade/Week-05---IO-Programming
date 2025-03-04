package com.bridgelabz.basicproblems;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CSVRowCounter {

    public static int countRecords(String filePath) {
        int count = 0;
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            if (!records.isEmpty()) {
                count = records.size() - 1;
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public static void main(String[] args) {
        String filePath = "E:/Capg/Week5_IO_Programming/Day1/src/main/resources/employees.csv"; // Change if needed
        int recordCount = countRecords(filePath);
        System.out.println("Number of records (excluding header): " + recordCount);
    }
}
