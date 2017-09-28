package com.app;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DayTimeImpl implements DayTime {
    public boolean isMorning(String testString) {
        String pattern = "[0][6-8].+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public boolean isDay(String testString) {
        String pattern = "(09|1[0-8]).+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public boolean isEvening(String testString) {
        String pattern = "(19|2[0-2]).+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    public boolean isNight(String testString) {
        String pattern = "(23|0[0-5]).+";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(testString);
        return m.matches();
    }
}
