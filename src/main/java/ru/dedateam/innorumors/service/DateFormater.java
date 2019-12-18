package ru.dedateam.innorumors.service;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class DateFormater {

    public static String format(LocalDateTime date) {
        return date.getDayOfMonth()
                + "." + date.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH)
                + "." + date.getYear()
                + " - " + date.getHour()
                + ":" + date.getMinute();
    }

}
