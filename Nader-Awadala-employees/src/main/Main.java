package main;

import main.employees.Model.Employee;
import main.employees.Service.employee.EmployeeService;
import main.employees.Service.reader.CSVReader;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the absolute path (including filename) to the CSV file: ");
        String filePath = scanner.nextLine();
        scanner.close();

        CSVReader readerService = new CSVReader();
        List<Employee> employees = readerService.read(filePath);

        EmployeeService employeeService = new EmployeeService();
        String results = employeeService.findLongestWorkingPair(employees);
        System.out.println("Employee ID 1, Employee ID 2, Days Worked Together");
        System.out.println(results);
    }
}