package ru.dedateam.innorumors.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormater {

    public static LocalDateTime parse(CharSequence date) throws ParseException {
        return LocalDateTime.parse(date);
    }
    public static String format(LocalDateTime date){
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    public static void main(String[] args) throws ParseException {
        CharSequence date = "2019-11-11";
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(format(localDateTime));
        System.out.println(parse(date));
    }
}
