package util;

import java.time.LocalDate;

public class DateUtils {
    public static LocalDate nextMonth(LocalDate from,int x) {
        return from.plusMonths(x);
    }
}