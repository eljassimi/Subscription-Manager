package util;

import java.time.LocalDate;

public class DateUtils {
    public static LocalDate nextMonth(LocalDate from) {
        return from.plusMonths(1);
    }
}
