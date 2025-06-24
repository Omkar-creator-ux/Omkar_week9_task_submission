package part_4;

import java.util.*;

public class UniqueVisitorCounter implements DataProcessor {

    public static class EmptyDataException extends DataProcessingException {
        public EmptyDataException(String message) {
            super(message);
        }
    }

    @Override
    public List<String> process(List<String> rawData) throws DataProcessingException {
        if (rawData.isEmpty()) {
            throw new EmptyDataException("No raw data to process! Did all tourists go missing?");
        }

        Set<String> uniqueNames = new HashSet<>();
        for (String entry : rawData) {
            if (entry.contains("Visitor: ")) {
                String[] parts = entry.split("Visitor: ")[1].split(", ");
                uniqueNames.add(parts[0]);
            } else if (entry.contains("Guest: ")) {
                String[] parts = entry.split("Guest: ")[1].split(", ");
                uniqueNames.add(parts[0]);
            }
        }

        return List.of("Unique Visitors: " + uniqueNames.size());
    }

    public static void main(String[] args) {
        UniqueVisitorCounter counter = new UniqueVisitorCounter();

        try {
            List<String> output = counter.process(List.of(
                "Visitor: John Doe, USA",
                "Visitor: Emily White, UK",
                "Guest: Ram Thapa, NP",
                "Guest: John Doe, NP"
            ));
            System.out.println(output);
        } catch (DataProcessingException e) {
            System.out.println("Processing error: " + e.getMessage());
        }

        try {
            counter.process(new ArrayList<>());
        } catch (DataProcessingException e) {
            System.out.println("Processing error: " + e.getMessage());
        }
    }
}
