package com.devdojo.guto.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DateUtil {
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final String LOCALDATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public String formatLocalDateTimeToDatabaseStyle(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern(LOCALDATETIME_FORMAT).format(localDateTime);
    }

    public Date convertStringToDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.parse(dateString);
    }
}
