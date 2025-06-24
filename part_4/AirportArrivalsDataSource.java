package part_4;

import java.util.ArrayList;
import java.util.List;

public class AirportArrivalsDataSource extends TouristDataSource {

    public AirportArrivalsDataSource() {
        super("Tribhuvan Airport Arrivals");
    }

    @Override
    public List<String> fetchData() throws DataSourceAccessException {
        if (getSourceName().contains("Tribhuvan") && Math.random() < 0.3) {
            throw new ConnectionLostException("Airport data connection lost! Maybe a pigeon sat on the antenna?");
        }

        List<String> arrivals = new ArrayList<>();
        arrivals.add("Visitor: John Doe, USA");
        arrivals.add("Visitor: Emily White, UK");
        return arrivals;
    }

    public static class ConnectionLostException extends DataSourceAccessException {
        public ConnectionLostException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        TouristDataSource source = new AirportArrivalsDataSource();
        try {
            List<String> data = source.fetchData();
            System.out.println("Fetched data: " + data);
        } catch (DataSourceAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
