package com.vilin.http.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vilin.http.exception.LocationException;
import com.vilin.http.service.HttpService;
import com.vilin.http.service.HttpServiceImpl;

import java.util.Map;
import java.util.Optional;

public class WeatherServiceImpl implements WeatherService {

    private final static String URL_CHINA = "http://www.weather.com.cn/data/city3jdata/china.html";

    private final static String URL_CITY_SUFFIX = "http://www.weather.com.cn/data/city3jdata/provshi/";

    private final static String URL_COUNTRY_SUFFIX = "http://www.weather.com.cn/data/city3jdata/station/";

    private final static String URL_WEATHER_INFO_SUFFIX = "http://www.weather.com.cn/data/sk/";

    private final static ThreadLocal threadLocal = new ThreadLocal();

    private Optional<Map<String, String>> provinces;

    private Optional<Map<String, String>> cities;

    private Optional<Map<String, String>> countries;

    private Optional<Map<String, Map<String, String>>> weatherInfo;

    private HttpService httpService = new HttpServiceImpl();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public synchronized Optional<Integer> getTemperature(String province, String city, String county) {

        provinces = httpService.getResponseToMap(URL_CHINA);

        Optional<String> prov = provinces.get().
                entrySet().
                stream().
                filter(entry -> province.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();

        if (!prov.isPresent()) {
            throw new LocationException("输入省份名称错误，请重新输入");
        }

        cities = httpService.getResponseToMap(URL_CITY_SUFFIX + prov.get() + ".html");

        Optional<String> cit = cities.get().
                entrySet().
                stream().
                filter(entry -> city.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();

        if (!cit.isPresent()) {
            throw new LocationException("输入城市名称错误，请重新输入");
        }

        countries = httpService.getResponseToMap(URL_COUNTRY_SUFFIX + prov.get() + cit.get() + ".html");

        Optional<String> count = countries.get().
                entrySet().
                stream().
                filter(entry -> county.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findFirst();

        if (!cit.isPresent()) {
            throw new LocationException("输入区县名称错误，请重新输入");
        }

        weatherInfo = httpService.getWeatherInfoToMap(URL_WEATHER_INFO_SUFFIX + prov.get() + cit.get() + count.get() + ".html");

        String temperature = weatherInfo.get().get("weatherinfo").get("temp");


        float temp = Float.parseFloat(temperature);

        Optional<Integer> result = Optional.ofNullable(Math.round(temp));

        return result;
    }
}
