package com.example.sistemanf.utils;

import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    private TimeUtil () {}

    public static String getNowFormatado() {
        Calendar calendar = Calendar.getInstance();

        String dia = calendar.get(Calendar.DAY_OF_MONTH) + "";
        dia = dia.length() == 1 ? "0" + dia : dia;

        String mes = (calendar.get(Calendar.MONTH) + 1) + "";
        mes = mes.length() == 1 ? "0" + mes : mes;

        String ano = calendar.get(Calendar.YEAR) + "";

        String hora = calendar.get(Calendar.HOUR_OF_DAY) + "";
        hora = hora.length() == 1 ? "0" + hora : hora;

        String minuto = calendar.get(Calendar.MINUTE) + "";
        minuto = minuto.length() == 1 ? "0" + minuto : minuto;

        String segundo = calendar.get(Calendar.SECOND) + "";
        segundo = segundo.length() == 1 ? "0" + segundo : segundo;

        String millisegundo = calendar.get(Calendar.MILLISECOND) + "";
        while (millisegundo.length() < 3) {
            millisegundo = "0" + millisegundo;
        }

        return dia + "_" + mes + "_" + ano + "_" + hora + "_" + minuto + "_" + segundo + "_" + millisegundo;
    }

    public static Date fromDataBrtoDate(String dataTexto) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dataTexto.substring(0, 2)) - 1);
        calendar.set(Calendar.MONTH, Integer.parseInt(dataTexto.substring(3, 5)));
        calendar.set(Calendar.YEAR, Integer.parseInt(dataTexto.substring(6)));
        return calendar.getTime();
    }
}
