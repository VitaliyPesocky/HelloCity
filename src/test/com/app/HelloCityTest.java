package com.app;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.*;


public class HelloCityTest {

    private static final Locale DEFAULT_LOCALE = Locale.getDefault();
    private static final ResourceBundle MESSAGES = ResourceBundle.getBundle("messages", DEFAULT_LOCALE);
    private HelloCity helloCity = new HelloCity(new DayTimeImpl());
    @Test
    public void testMorning(){
        String message = helloCity.showMessage("07:00");
        assertEquals(MESSAGES.getString("morning") + null + "!", message);
    }
    @Test
    public void testDay(){
        String message = helloCity.showMessage("09:30");
        assertEquals(MESSAGES.getString("day") + null + "!", message);
    }
    @Test
    public void testEvening(){
        String message = helloCity.showMessage("20:30");
        assertEquals(MESSAGES.getString("evening") + null + "!", message);
    }
    @Test
    public void testNight(){
        String message = helloCity.showMessage("00:30");
        assertEquals(MESSAGES.getString("night") + null + "!", message);
    }

    @Test
    public void testTimeZone(){
        boolean isTimeZone = true;
        assertEquals(helloCity.isTimeZone("Europe/Kiev"), isTimeZone);
    }

    @Test
    public void testNameTimeZone(){
        boolean isNameTimeZone = true;
        assertEquals(helloCity.isNameTimeZone("Kiev"), isNameTimeZone);
    }
}