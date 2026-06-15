package de.hitec.nhplus.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    /**
     * A method to convert a String to a LocalDateTime in the format of <code>DATETIME_FORMAT</code>.
     * @param datetime is the String that will be converted to a LocalDateTime.
     * @return <code>LocalDateTime</code>
     */
    public static LocalDateTime convertStringToLocalDateTime(String datetime) {
        if (datetime == null){
            return null;
        }
        return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    // TODO dateOfBirth spinnt
    public static String convertLocalDateToString(LocalDate date) {

        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public static String convertLocalTimeToString(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern(TIME_FORMAT));
    }

    /**
     * A method to convert a LocalDateTime to a String in the format of <code>DATETIME_FORMAT</code>
     * @param datetime is the LocalDateTime that will be converted to a String.
     * @return <code>String</code>
     */
    public static String convertLocalDateTimeToString(LocalDateTime datetime){
        if (datetime == null){
            return null;
        }
        return datetime.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT));
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

}
