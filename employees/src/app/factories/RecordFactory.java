package app.factories;

import app.model.Record;
import java.time.LocalDate;

public final class RecordFactory  {

    private static final String DEFAULT_REGEX_PATTERN = ", ";
    private static final String STR_NULL = "NULL";
    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;
    private static final int INDEX_TWO = 2;
    private static final int INDEX_THREE = 3;

    public RecordFactory() {
    }

    public static Record execute(String line) {
        String[] record = line.split(DEFAULT_REGEX_PATTERN);

        long emplID = Long.parseLong(record[INDEX_ZERO].trim());
        long projectID = Long.parseLong(record[INDEX_ONE].trim());
        LocalDate dateFrom = LocalDate.parse(record[INDEX_TWO]);
        LocalDate dateTo;
        if (record[INDEX_THREE] == null || STR_NULL.equals(record[INDEX_THREE])) {

            dateTo = LocalDate.now();
        } else {
            dateTo = LocalDate.parse(record[INDEX_THREE]);
        }

        return new Record(
                emplID,
                projectID,
                dateFrom,
                dateTo
        );
    }
}