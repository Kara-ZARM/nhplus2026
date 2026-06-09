package de.hitec.nhplus.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static LocalDate convertStringToLocalDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static LocalTime convertStringToLocalTime(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public static LocalDateTime convertStringToLocalDateTime(String datetime) {
        if (datetime == null){
            return null;
        }
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    public static String convertLocalDateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String convertLocalTimeToString(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    public static String convertLocalDateTimeToString(LocalDateTime datetime){
        if (datetime == null){
            return null;
        }
        return datetime.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }
}
