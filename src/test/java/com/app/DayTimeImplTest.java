package com.app;

import org.junit.Test;

import static org.junit.Assert.*;

public class DayTimeImplTest {
    @Test
    public void testMorningTime(){
        DayTime dayTime = new DayTimeImpl();
        boolean actual = dayTime.isMorning("07:30");
        boolean expect = true;
        assertEquals(actual, expect);
    }
    @Test
    public void testDayTime(){
        DayTime dayTime = new DayTimeImpl();
        boolean actual = dayTime.isMorning("12:30");
        boolean expect = true;
        assertEquals(actual, expect);
    }
    @Test
    public void testEveningTime(){
        DayTime dayTime = new DayTimeImpl();
        boolean actual = dayTime.isMorning("19:30");
        boolean expect = true;
        assertEquals(actual, expect);
    }
    @Test
    public void testNightTime(){
        DayTime dayTime = new DayTimeImpl();
        boolean actual = dayTime.isMorning("00:30");
        boolean expect = true;
        assertEquals(actual, expect);
    }
}