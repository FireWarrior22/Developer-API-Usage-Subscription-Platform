package ApiPlatform.util;
import java.time.LocalDate;
import java.time.YearMonth;

public final class DateUtils {

    private DateUtils() {
        // utility class
    }

    public static YearMonth currentMonth() {
        return YearMonth.now();
    }

    public static YearMonth previousMonth() {
        return YearMonth.now().minusMonths(1);
    }

    public static LocalDate today() {
        return LocalDate.now();
    }

    public static LocalDate oneMonthFromNow() {
        return LocalDate.now().plusMonths(1);
    }
}

