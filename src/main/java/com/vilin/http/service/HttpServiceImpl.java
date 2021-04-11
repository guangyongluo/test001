package com.vilin.http.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vilin.http.exception.ConnException;
import com.vilin.http.util.HttpClientImpl;

import java.util.Map;
import java.util.Optional;

public class HttpServiceImpl implements HttpService{

    private HttpClientImpl httpClient = new HttpClientImpl();

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Optional<Map<String, String>> getResponseToMap(String url) {
        Optional<Map<String, String>> result = Optional.empty();

        try {
            result = Optional.ofNullable(mapper.readValue(httpClient.retryGetRequest(url).get(), Map.class));
        } catch (ConnException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Optional<Map<String, Map<String, String>>> getWeatherInfoToMap(String url) {
        Optional<Map<String, Map<String, String>>> result = Optional.empty();

        try {
            result = Optional.ofNullable(mapper.readValue(httpClient.retryGetRequest(url).get(), Map.class));
        } catch (ConnException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
