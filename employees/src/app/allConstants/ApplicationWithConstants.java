package app.allConstants;

public class ApplicationWithConstants {

        private ApplicationWithConstants() {
        }

        public static final String FILE_PATH = ".\\src\\resources\\employees.csv";
        public static final String PATTERN_FOR_EMPLOYEES = "The pair of employees who have worked together on common projects for the longest period of time are:%n" +
                "EmployeeID: %d and EmployeeID: %d%nwith total overlap duration %d days";
        public static final String NO_MESSAGE = "Doesn't exist pair of employees which are worked together on joint projects.";

        public static final int EMPTY_COLLECTION_SIZE = 0;
        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int OVERLAP_ZERO_DAYS = 0;
    }
