package employees.Service;

import java.util.List;

public interface ReaderService<T> {
    public List<T> read(String filePath);
}
