package com.garyfimo.retofinancieraoh.ui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private static final String BASE_DATE = "dd/MM/yyyy";
    private SimpleDateFormat sdf;

    public DateUtils() {
        sdf = new SimpleDateFormat(BASE_DATE, Locale.US);
    }

    public Date getDateFromString(String stringDate) throws ParseException {
        return sdf.parse(stringDate);
    }

    public String getStringFromDate(Date date) {
        return sdf.format(date);
    }
}
