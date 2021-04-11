package com.vilin.http.weather;

import com.vilin.http.exception.LocationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class WeatherServiceTest {

    private WeatherService weatherService;

    @Before
    public void init(){
        weatherService = new WeatherServiceImpl();
    }

    @Test
    public void testGetWeatherInfo(){
        Optional<Integer> wInfo = weatherService.getTemperature("江苏", "苏州", "吴中");
        System.out.println(wInfo);
        Assert.assertNotNull(wInfo.get());
    }

    @Test(expected = LocationException.class)
    public void testGetWeatherInfoWithErrorName(){
        Optional<Integer> wInfo = weatherService.getTemperature("江苏", "你好", "吴中");
    }

    @Test
    public void testTempGap(){
        Optional<Integer> wInfo = weatherService.getTemperature("江苏", "苏州", "吴中");
        Assert.assertTrue(wInfo.get() > -60 && wInfo.get() < 50);
    }
}
