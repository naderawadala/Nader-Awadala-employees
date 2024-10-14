package main;

import main.employees.Model.Employee;
import main.employees.Service.employee.EmployeeService;
import main.employees.Service.reader.CSVReader;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        String filePath = "F:\\the batcave\\Nader-Awadala-employees\\Nader-Awadala-employees\\src\\main\\resources\\data.csv";
        CSVReader readerService = new CSVReader();
        List<Employee> employees = readerService.read(filePath);

        EmployeeService employeeService = new EmployeeService();
        String results = employeeService.findLongestWorkingPair(employees);
        System.out.println("Employee ID 1, Employee ID 2, Days Worked Together");
        System.out.println(results);
    }
}