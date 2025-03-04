package com.bridgelabz.advancedproblems.encryptanddecryptdata;


import org.apache.commons.csv.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CsvProcessor {
    private static final String FILE_NAME = "employees.csv";

    public static void writeEncryptedCsv() {
        List<String[]> employees = Arrays.asList(
                new String[]{"101", "Anand Soni", "IT", "john.doe@example.com", "60000"},
                new String[]{"102", "Ankit Patel", "HR", "jane.smith@example.com", "55000"},
                new String[]{"103", "Ankit Kumar", "Finance", "alice.brown@example.com", "70000"},
                new String[]{"104", "Aakrati Barsaiyan", "IT", "bob.white@example.com", "65000"},
                new String[]{"105", "Anshika Gupta", "Marketing", "charlie.black@example.com", "50000"}
        );

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
             CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Department", "Email", "Salary"))) {

            for (String[] emp : employees) {
                String encryptedEmail = CsvEncryptor.encrypt(emp[3]);
                String encryptedSalary = CsvEncryptor.encrypt(emp[4]);
                csvPrinter.printRecord(emp[0], emp[1], emp[2], encryptedEmail, encryptedSalary);
            }

            System.out.println("Encrypted CSV written successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readDecryptedCsv() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            System.out.println("Decrypted Employee Data:");
            for (CSVRecord record : csvParser) {
                String id = record.get("ID");
                String name = record.get("Name");
                String department = record.get("Department");
                String decryptedEmail = CsvEncryptor.decrypt(record.get("Email"));
                String decryptedSalary = CsvEncryptor.decrypt(record.get("Salary"));

                System.out.printf("ID: %s, Name: %s, Dept: %s, Email: %s, Salary: %s%n",
                        id, name, department, decryptedEmail, decryptedSalary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        writeEncryptedCsv();
        readDecryptedCsv();
    }
}
