package com.bbt.simpleSchedular.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by anish on 24-10-2017.
 */

public class DateHelper {
    public static final String DATE_INT_FORMAT = "yyyyMMddHHmmss";

    public static String getDateInIntFormat(Date date) {
        SimpleDateFormat outputFormat = new SimpleDateFormat(DATE_INT_FORMAT, Locale.getDefault());
        return outputFormat.format(date);
    }
}
