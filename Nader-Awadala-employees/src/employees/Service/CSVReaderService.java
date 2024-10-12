package employees.Service;

import employees.Model.Employee;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderService implements ReaderService{
    @Override
    public List<Employee> read(String filePath) {
        List<Employee> employees = new ArrayList<>();
        BufferedReader br = null;
        String line;
        try {
            br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",\\s*");

                int id = Integer.parseInt(values[0]);
                int projectId = Integer.parseInt(values[1]);
                LocalDate dateFrom = LocalDate.parse(values[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate dateTo = values[3].equals("NULL") ? LocalDate.now() : LocalDate.parse(values[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                employees.add(new Employee(id, projectId, dateFrom, dateTo));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employees;
    }
}
