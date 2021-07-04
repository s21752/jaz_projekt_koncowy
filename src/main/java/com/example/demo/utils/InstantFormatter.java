package com.example.demo.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;

public class InstantFormatter {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    public static String getDataString(Instant instant) {
        return InstantFormatter.formatter.format(instant).split(" ")[0];
    }

    public static Instant getInstant(String date) {
        var temporalAccessor = formatter.parse(date + " 12:00");

        LocalDateTime localDateTime = LocalDateTime.from(temporalAccessor);
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());

        return Instant.from(zonedDateTime);
    }

    public static String getNbpApiDateQueryStringFormat(Instant instant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(instant));
        var month = cal.get(Calendar.MONTH);
        var day = cal.get(Calendar.DAY_OF_MONTH);
        return cal.get(Calendar.YEAR) + "-" + ((month < 10) ? "0" : "") + cal.get(Calendar.MONTH) + "-" + ((day < 10) ? "0" : "") + cal.get(Calendar.DAY_OF_MONTH);
    }
}
