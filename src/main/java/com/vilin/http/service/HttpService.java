package com.vilin.http.service;

import java.util.Map;
import java.util.Optional;

public interface HttpService {

    Optional<Map<String, String>> getResponseToMap(String url);

    Optional<Map<String, Map<String, String>>> getWeatherInfoToMap(String url);

}
