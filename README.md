# Employee Project Overlap
This project is designed to analyze employee work periods on various projects by reading employee data from a CSV file. It identifies the pairs of employees who have worked together the longest on common projects and calculates the total number of days they worked together.

## 1. CSVReader
The `CSVReader` class is responsible for reading employee data from a specified CSV file. It parses each line of the file and converts it into `Employee` objects.

**Key Features:**
- Reads the CSV file line by line.
- Splits each line into fields corresponding to employee ID, project ID, start date, and end date.
- Supports multiple date formats (`yyyy-MM-dd`, `MM/dd/yyyy`).
- Handles file not found and unexpected errors with appropriate messages.

**Method:**
```java
public List<Employee> read(String filePath);
```
## 2. EmployeeService

The `EmployeeService` class is responsible for analyzing employee work data and finding the longest working pairs of employees on shared projects. 

**Key Features:**
- Groups employees by project ID.
- Calculates the overlap in work periods between employee pairs.
- Identifies the pair of employees who worked together for the longest duration on common projects.

**Method:**
```java
public findLongestWorkingPair(List<Employee> employees);
```

This method takes a list of `Employee` objects and returns a formatted string indicating the IDs of the two employees who worked together the longest, along with the number of overlapping days.

**Parameters:**
- `employees`: A list of `Employee` objects.

**Returns:**
- A string formatted as `"employeeId1, employeeId2, daysWorked"`.

**Example Usage:**
```java
EmployeeService service = new EmployeeService();
List<Employee> employees = // ... fetch or create employee list
String result = service.findLongestWorkingPair(employees);
System.out.println(result); // Outputs: "1, 2, 30"
