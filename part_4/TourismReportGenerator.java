package part_4;

import java.util.List;

public class TourismReportGenerator {

    public static void generateOverallReport(List<TouristDataSource> dataSources, DataProcessor processor) {
        System.out.println("Generating overall tourism report...");

        for (TouristDataSource dataSource : dataSources) {
            String sourceName = dataSource.getSourceName();
            try {
                List<String> rawData = dataSource.fetchData();
                try {
                    List<String> processed = processor.process(rawData);
                    System.out.println("Processed data from " + sourceName + ": " + processed);
                } catch (DataProcessingException e) {
                    System.out.println("Error processing data from " + sourceName + ": " + e.getMessage() + ". Skipping this data.");
                }
            } catch (DataSourceAccessException e) {
                System.out.println("Could not fetch data from " + sourceName + ": " + e.getMessage() + ". Skipping this source.");
                if (e.getCause() != null) {
                    System.out.println("Reason: " + e.getCause().getMessage());
                }
            } finally {
                System.out.println("Data handling from " + sourceName + " completed.\n");
            }
        }
    }

    public static void main(String[] args) {
        List<TouristDataSource> dataSources = List.of(
            new AirportArrivalsDataSource(),
            new HotelRegistrationsDataSource()
        );

        DataProcessor processor = new UniqueVisitorCounter();

        generateOverallReport(dataSources, processor);
    }
}
