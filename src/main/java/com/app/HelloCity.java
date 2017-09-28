package com.app;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ResourceBundle;

public class HelloCity {

    private static final Logger LOGGER = Logger.getLogger(HelloCity.class);
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages", DEFAULT_LOCALE);

    private String cityName;
    private String correctCityName;
    private String identity;
    private DayTime dayTime;

    public HelloCity(DayTime dayTime){
        this.dayTime = dayTime;
    }

    public static void main(String[] args) {
        HelloCity helloCity = new HelloCity(new DayTimeImpl());
        if(args.length > 1){
            helloCity.cityName = args[0];
            helloCity.identity = args[1];
        }else if(args.length == 1){
            helloCity.cityName = args[0];
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String currentTime = helloCity.getCurrentTime(dateFormat);
        System.out.println(helloCity.showMessage(currentTime));
    }

    private String getCurrentTime(SimpleDateFormat dateFormat) {
        String currentTime;
        correctCityName = cityName.replace(" ", "_");
        if (identity.isEmpty()) {
            if (isTimeZone(correctCityName)) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(correctCityName));
                currentTime = getTime(dateFormat);
            } else {
                dateFormat.setTimeZone(TimeZone.getDefault());
                currentTime = getTime(dateFormat);
            }
        } else {
            dateFormat.setTimeZone(TimeZone.getTimeZone(identity));
            currentTime = getTime(dateFormat);
        }
        return currentTime;
    }

    public String showMessage(String currentTime) {
        LOGGER.info("inside method showMessage");
        String message;
        if(dayTime.isMorning(currentTime)){
            LOGGER.info("Print good morning");
            message = MESSAGES.getString("morning")+ cityName + "!";
        }else if(dayTime.isDay(currentTime)){
            LOGGER.info("Print good day");
            message = MESSAGES.getString("day")+ cityName + "!";
        }else if(dayTime.isEvening(currentTime)){
            LOGGER.info("Print good evening");
            message = MESSAGES.getString("evening")+ cityName + "!";
        }else if(dayTime.isNight(currentTime)){
            LOGGER.info("Print good night");
            message = MESSAGES.getString("night")+ cityName + "!";
        }else{
            message = "Error";
            LOGGER.warn("Wrong time");
        }
        return message;
    }

    private String getTime(SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date());
    }

    private boolean isTimeZone(String testString) {
        String[] timeZones = TimeZone.getAvailableIDs();
        String pattern = ".+" + testString;
        for (String s1 : timeZones) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(s1);
            if (m.matches()) {
                correctCityName = s1;
                return true;
            }
        }
        return false;
    }
}
