package com.example.sistemanf.utils;

import java.util.Calendar;

public class TimeUtil {

    private TimeUtil () {}

    public static String getNowFormatado() {
        String nowFormatado = "";

        Calendar calendar = Calendar.getInstance();
        nowFormatado = nowFormatado + calendar.get(Calendar.DAY_OF_MONTH) + "_";
        nowFormatado = nowFormatado + calendar.get(Calendar.MONTH) + "_";
        nowFormatado = nowFormatado + calendar.get(Calendar.YEAR) + "_";
        nowFormatado = nowFormatado + calendar.get(Calendar.HOUR_OF_DAY) + "_";
        nowFormatado = nowFormatado + calendar.get(Calendar.MINUTE) + "_";
        nowFormatado = nowFormatado + calendar.get(Calendar.SECOND) + "_";
        nowFormatado = nowFormatado + calendar.get(Calendar.MILLISECOND);

        return nowFormatado;
    }
}
