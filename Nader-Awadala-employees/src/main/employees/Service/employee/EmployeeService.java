package main.employees.Service.employee;

import main.employees.Model.Employee;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    public String findLongestWorkingPair(List<Employee> employees) {
        Map<Integer, List<Employee>> projects = employees.stream()
                .collect(Collectors.groupingBy(Employee::projectId));

        Map<String, Long> pairWorkDurations = new HashMap<>();

        projects.values().forEach(projectEmployees -> {
            int size = projectEmployees.size();
            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    Employee e1 = projectEmployees.get(i);
                    Employee e2 = projectEmployees.get(j);

                    long overlapDays = calculateOverlap(e1.dateFrom(), e1.dateTo(), e2.dateFrom(), e2.dateTo());
                    if (overlapDays > 0) {
                        String pairKey = createEmployeePairKey(e1.id(), e2.id());
                        pairWorkDurations.merge(pairKey, overlapDays, Long::sum);
                    }
                }
            }
        });

        Optional<Map.Entry<String, Long>> longestPairEntry = pairWorkDurations.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        return longestPairEntry.map(entry -> {
            String[] employeeIds = entry.getKey().split(",");
            int empId1 = Integer.parseInt(employeeIds[0]);
            int empId2 = Integer.parseInt(employeeIds[1]);
            long daysWorked = entry.getValue();

            return String.format("%d, %d, %d", empId1, empId2, daysWorked);
        }).orElse("No pairs found from input");
    }

    private long calculateOverlap(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        LocalDate actualEnd1 = (end1 == null || end1.isAfter(LocalDate.now())) ? LocalDate.now() : end1;
        LocalDate actualEnd2 = (end2 == null || end2.isAfter(LocalDate.now())) ? LocalDate.now() : end2;

        LocalDate overlapStart = start1.isAfter(start2) ? start1 : start2;
        LocalDate overlapEnd = actualEnd1.isBefore(actualEnd2) ? actualEnd1 : actualEnd2;

        return (overlapStart.isBefore(overlapEnd) || overlapStart.equals(overlapEnd))
                ? ChronoUnit.DAYS.between(overlapStart, overlapEnd)
                : 0;
    }

    private String createEmployeePairKey(int empId1, int empId2) {
        return empId1 < empId2 ? empId1 + "," + empId2 : empId2 + "," + empId1;
    }
}
