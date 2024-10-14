package main.employees.Model;

import java.time.LocalDate;

public record Employee(int id, int projectId, LocalDate dateFrom, LocalDate dateTo) {
}
