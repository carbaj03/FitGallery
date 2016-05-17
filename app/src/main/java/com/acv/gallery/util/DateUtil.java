package com.acv.gallery.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

    public static final String TAG = DateUtil.class.getSimpleName();

    private static DateUtil instance = null;

    public final String[] vArrDayWeek = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sábado"};

    /**dd/MM/yyyy hh:mm*/
    public final String FECHA_LARGA = "dd/MM/yyyy hh:mm";

    /**dd/MM/yyyy*/
    public final String FECHA_NORMAL = "dd/MM/yyyy";

    /**dd/MM/yy*/
    public final String FECHA_CORTA = "dd/MM/yy";

    /**HH:mm:ss*/
    public final String FECHA_HORA = "HH:mm:ss";

    /**yyyy-MM-dd*/
    public final String FECHA_FORMATEADA = "yyyy-MM-dd";

    /**dd_MM_yy*/
    public final String FECHA_FICHERO = "dd_MM_yy";

    public static DateUtil getInstance() {
        if(instance == null) {
            instance = new DateUtil();
        }
        return instance;
    }

    public String getFechaDia(long pDate){
        return getFechaDia(new Date(pDate));
    }

    public String getFechaDia(Date pDate){
        return pDate != null ?
                String.format("%s (%s)",
                        new SimpleDateFormat(FECHA_NORMAL, Locale.getDefault())
                                .format(pDate.getTime()), getDay(pDate)) : "";
    }

    public String getDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        return vArrDayWeek[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public int getDayOfTheWeek(Date date){
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public boolean isSomeDay(Calendar cal, Calendar cal2){
        return cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) == cal2.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    public boolean isSomeMonth(){
        return true;
    }

    public boolean isSomeYear(){
        return true;
    }
}
