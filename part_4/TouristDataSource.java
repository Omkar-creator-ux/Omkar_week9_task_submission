package part_4;

import java.util.List;

public abstract class TouristDataSource {
    private final String sourceName;

    public TouristDataSource(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceName() {
        return sourceName;
    }

    public abstract List<String> fetchData() throws DataSourceAccessException;
}
