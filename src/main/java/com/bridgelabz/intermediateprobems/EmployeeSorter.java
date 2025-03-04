package com.bridgelabz.intermediateprobems;



import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class EmployeeSorter {

    public static void sortAndPrintTopSalaries(String filePath, int topN) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            List<String[]> records = reader.readAll();
            if (records.size() <= 1) {
                System.out.println("CSV file is empty or has only a header!");
                return;
            }

            String[] header = records.remove(0);
            records.sort((a, b) -> Double.compare(Double.parseDouble(b[3]), Double.parseDouble(a[3])));

            // Print the top N highest-paid employees
            System.out.println("Top " + topN + " Highest Paid Employees:");
            System.out.println(String.join(", ", header));
            for (int i = 0; i < Math.min(topN, records.size()); i++) {
                System.out.println(String.join(", ", records.get(i)));
            }

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error processing CSV file: " + e.getMessage());
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String filePath = "E:/Capg/Week5_IO_Programming/Day1/src/main/resources/employees.csv";
        int topN = 5;
        sortAndPrintTopSalaries(filePath, topN);
    }
}
