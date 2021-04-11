package com.vilin.http.weather;

import java.util.Optional;

public interface WeatherService {
    Optional<Integer> getTemperature(String province, String city, String county);
}
