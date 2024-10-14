package main.employees.Service.reader;

import java.util.List;

public interface Reader<T> {
    List<T> read(String filePath);
}
