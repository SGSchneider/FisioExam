package br.ufsm.fisioexam.database.converter;


import androidx.room.TypeConverter;

import java.util.Calendar;
public class ConversorCalendar {
    @TypeConverter
    public Long paraLong(Calendar calendar){
        if(calendar != null){
            return calendar.getTimeInMillis();
        }
        return null;
    }

    @TypeConverter
    public Calendar paraCalendar(Long inMilis){
        Calendar calendar = Calendar.getInstance();
        if(inMilis != null){
            calendar.setTimeInMillis(inMilis);
        }
        return calendar;
    }
}
