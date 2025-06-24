package part_4;

import java.util.List;

public interface DataProcessor {
    List<String> process(List<String> rawData) throws DataProcessingException;
}
