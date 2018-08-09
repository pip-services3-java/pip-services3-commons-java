package org.pipservices.commons.random;

import java.time.*;

public class RandomDateTime {
    public static ZonedDateTime nextDate() {
    	return nextDate(0, 0);
    }

    public static ZonedDateTime nextDate(int year) {
    	return nextDate(year, year);
    }
    
    public static ZonedDateTime nextDate(int minYear, int maxYear) {
        int currentYear = ZonedDateTime.now().getYear();
        minYear = minYear == 0 ? currentYear - RandomInteger.nextInteger(10) : minYear;
        maxYear = maxYear == 0 ? currentYear : maxYear;

        int year = RandomInteger.nextInteger(minYear, maxYear);
        int month = RandomInteger.nextInteger(1, 13);
        int day = RandomInteger.nextInteger(1, 32);
       
        if (month == 2)
            day = Math.min(28, day);
        else if (month == 4 || month == 6 || month == 9 || month == 11)
            day = Math.min(30, day);
        return ZonedDateTime.of(year, month, day, 0, 0, 0, 0, ZoneId.of("UTC"));
    }

//    public static TimeSpan nextTime() {
//        int hour = RandomInteger.nextInteger(0, 24);
//        int min = RandomInteger.nextInteger(0, 60);
//        int sec = RandomInteger.nextInteger(0, 60);
//        int millis = RandomInteger.nextInteger(0, 1000);
//
//        return new TimeSpan(hour, min, sec, millis);
//    }

    public static ZonedDateTime nextDateTime() {
    	return nextDateTime(0, 0);
    }

    public static ZonedDateTime nextDateTime(int year) {
    	return nextDateTime(year, year);
    }
    
	public static ZonedDateTime nextDateTime(int minYear, int maxYear) {
        return nextDate(minYear, maxYear)
    		.plusSeconds(RandomInteger.nextInteger(3600 * 24 * 365));
    }

//    public static T nextEnum<T>() {
//        var enumType = typeof(T);
//        var values = enumType.GetEnumValues();
//        var index = Integer(values.Length);
//        return (T)values.GetValue(index);
//    }

    public static ZonedDateTime updateDateTime(ZonedDateTime value) {
    	return updateDateTime(value, 0);
    }

    public static ZonedDateTime updateDateTime(ZonedDateTime value, float range) {
        range = range != 0 ? range : 10;
        if (range < 0)
        	return value;
        
        float days = RandomFloat.nextFloat(-range, range);
        return value.plusDays((int)days);
    }

}
