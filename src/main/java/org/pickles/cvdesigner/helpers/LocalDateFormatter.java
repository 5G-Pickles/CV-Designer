package org.pickles.cvdesigner.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class LocalDateFormatter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String localDateToString(LocalDate date) {
        if (date == null)
            return "";
        else
            return date.format(LocalDateFormatter.formatter);
    }

    public static LocalDate stringToLocalDate(String date) {
        if (date.isEmpty())
            return null;
        else
            return LocalDate.parse(date, formatter);
    }
}
