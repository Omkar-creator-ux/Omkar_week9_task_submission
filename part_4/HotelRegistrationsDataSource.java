package part_4;

import java.util.ArrayList;
import java.util.List;

public class HotelRegistrationsDataSource extends TouristDataSource {

    public HotelRegistrationsDataSource() {
        super("Kathmandu Hotels Registrations");
    }

    @Override
    public List<String> fetchData() throws DataSourceAccessException {
        if (getSourceName().contains("Hotels") && Math.random() < 0.2) {
            throw new AuthenticationFailedException("Hotel API authentication failed! Did someone forget the password again?");
        }

        List<String> data = new ArrayList<>();
        data.add("Hotel: Yak & Yeti, Guest: Ram Thapa, NP");
        data.add("Hotel: Annapurna, Guest: Alice Smith, AU");
        return data;
    }

    public static class AuthenticationFailedException extends DataSourceAccessException {
        public AuthenticationFailedException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        TouristDataSource source = new HotelRegistrationsDataSource();
        try {
            List<String> data = source.fetchData();
            System.out.println("Fetched data: " + data);
        } catch (DataSourceAccessException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
