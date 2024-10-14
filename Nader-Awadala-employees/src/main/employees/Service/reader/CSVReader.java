package main.employees.Service.reader;

import main.employees.Model.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements Reader {
    @Override
    public List<Employee> read(String filePath) {
        List<Employee> employees = new ArrayList<>();
        BufferedReader br = null;
        String line;
        String datePattern = "yyyy-MM-dd";

        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",\\s*");

                int id = Integer.parseInt(values[0]);
                int projectId = Integer.parseInt(values[1]);
                LocalDate dateFrom = LocalDate.parse(values[2], DateTimeFormatter.ofPattern(datePattern));
                LocalDate dateTo = values[3].equals("NULL") ? LocalDate.now() : LocalDate.parse(values[3], DateTimeFormatter.ofPattern(datePattern));

                employees.add(new Employee(id, projectId, dateFrom, dateTo));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error: The file was not found at the specified path: " + filePath);
            return employees;
        }  catch (Exception e) {
            System.err.println("Error: An unexpected error occurred: " + e.getMessage());
            return employees;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.err.println("Error: Failed to close the BufferedReader.");
                }
            }
        }
        return employees;
    }
}
