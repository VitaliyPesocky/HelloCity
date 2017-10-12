package com.app;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

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
        helloCity.initFields(args);
        String currentTime = helloCity.getCurrentTime(new SimpleDateFormat("HH:mm"));
        System.out.println(helloCity.showMessage(currentTime));
    }

    private String getCurrentTime(SimpleDateFormat dateFormat) {
        LOGGER.info("inside method getCurrentTime");
        correctCityName = cityName.replace(" ", "_");
        if (identity == null) {
            if (isNameTimeZone(correctCityName)) {
                dateFormat.setTimeZone(TimeZone.getTimeZone(correctCityName));
            } else {
                dateFormat.setTimeZone(TimeZone.getDefault());
            }
        } else {
            dateFormat.setTimeZone(TimeZone.getTimeZone(identity));
        }
        return getTime(dateFormat);
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
        LOGGER.info("inside method getTime");
        return dateFormat.format(new Date());
    }

    private void initFields(String[] args){
        LOGGER.info("inside method initFields");
        cityName = args[0];
        if(args.length > 1){
            if(isTimeZone(args[1])){
                identity = args[1];
            }else{
                cityName += " " + args[1];
                if(args.length > 2){
                    identity = args[2];
                }
            }
        }
    }

    public boolean isNameTimeZone(String testString) {
        LOGGER.info("inside method isNameTimeZone");
        String[] timeZones = TimeZone.getAvailableIDs();
        for (String timeZone : timeZones) {
            if(timeZone.contains(testString)){
                correctCityName = timeZone;
                return true;
            }
        }
        return false;
    }

    public boolean isTimeZone(String text){
        LOGGER.info("inside method isTimeZone");
        String[] timeZones = TimeZone.getAvailableIDs();
        for (String timeZone : timeZones) {
            if(text.equals(timeZone)){
                return true;
            }
        }
        return false;
    }
}
