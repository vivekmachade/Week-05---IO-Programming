package com.bridgelabz.advancedproblems.jsoncsvconverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class JsonCsvConverter {

    private static final String JSON_FILE = "students.json";
    private static final String CSV_FILE = "students.csv";


    public static void jsonToCsv() {
        try {
            // Read JSON File
            ObjectMapper objectMapper = new ObjectMapper();
            List<Student> students = objectMapper.readValue(new File(JSON_FILE), new TypeReference<List<Student>>() {});

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE));
                 CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Name", "Age", "Marks"))) {

                for (Student student : students) {
                    csvPrinter.printRecord(student.getId(), student.getName(), student.getAge(), student.getMarks());
                }
                csvPrinter.flush();
            }
            System.out.println("Converted JSON to CSV successfully: " + CSV_FILE);
        } catch (IOException e) {
            System.err.println("Error converting JSON to CSV: " + e.getMessage());
        }
    }

    // Convert CSV to JSON
    public static void csvToJson() {
        try {
            List<Student> students = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE));
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

                for (CSVRecord record : csvParser) {
                    Student student = new Student(
                            Integer.parseInt(record.get("ID")),
                            record.get("Name"),
                            Integer.parseInt(record.get("Age")),
                            Double.parseDouble(record.get("Marks"))
                    );
                    students.add(student);
                }
            }

            // Write to JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(JSON_FILE), students);
            System.out.println("Converted CSV to JSON successfully: " + JSON_FILE);
        } catch (IOException e) {
            System.err.println("Error converting CSV to JSON: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        jsonToCsv();
        csvToJson();
    }
}
