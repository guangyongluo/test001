package com.vilin.http.util;

import com.vilin.http.exception.ConnException;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class HttpClientTest {

    private HttpClient httpClient;

    @Before
    public void init(){
        httpClient = new HttpClientImpl();
    }

    @Test
    public void testGetRequest(){
        String url = "http://www.weather.com.cn/data/city3jdata/china.html";
        HttpClientImpl httpClient = new HttpClientImpl();
        String result = "";
        try {
            result = httpClient.getRequest(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

    @Test(expected = ConnException.class)
    public void TestRequestException() {
        String url = "http://www.weather.com.cn/data/city3jdata/provshi/10135.html";
        HttpClientImpl httpClient = new HttpClientImpl();
        httpClient.retryGetRequest(url);
    }
}
