package com.vilin.http.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Optional;

public class HttpServiceTest {

    private HttpServiceImpl httpService;

    @Before
    public void init(){
        httpService = new HttpServiceImpl();
    }

    @Test
    public void TestGetProvinceMap(){
        String url = "http://www.weather.com.cn/data/city3jdata/china.html";
        Optional<Map<String, String>> provinceOptional = httpService.getResponseToMap(url);
        Assert.assertNotNull(provinceOptional.get());
        Assert.assertEquals(provinceOptional.get().size(), 34);
        Assert.assertEquals(provinceOptional.get().get("10101"),"北京");
        Assert.assertEquals(provinceOptional.get().get("10119"),"江苏");
        Assert.assertEquals(provinceOptional.get().get("10134"),"台湾");
        Assert.assertNull(provinceOptional.get().get("10100"));
        Assert.assertNull(provinceOptional.get().get("10135"));
    }

    @Test
    public void TestGetCityMap(){
        String url = "http://www.weather.com.cn/data/city3jdata/provshi/10119.html";
        Optional<Map<String, String>> cityOptional = httpService.getResponseToMap(url);
        Assert.assertNotNull(cityOptional.get());
        Assert.assertEquals(cityOptional.get().size(), 13);
        Assert.assertEquals(cityOptional.get().get("01"),"南京");
        Assert.assertEquals(cityOptional.get().get("04"),"苏州");
        Assert.assertEquals(cityOptional.get().get("13"),"宿迁");
        Assert.assertNull(cityOptional.get().get("0"));
        Assert.assertNull(cityOptional.get().get("14"));
    }

    @Test
    public void TestGetCountryMap(){
        String url = "http://www.weather.com.cn/data/city3jdata/station/1011904.html";
        Optional<Map<String, String>> countryOptional = httpService.getResponseToMap(url);
        Assert.assertNotNull(countryOptional.get());
        Assert.assertEquals(countryOptional.get().size(), 7);
        Assert.assertEquals(countryOptional.get().get("01"),"苏州");
        Assert.assertEquals(countryOptional.get().get("05"),"吴中");
        Assert.assertEquals(countryOptional.get().get("08"),"太仓");
        Assert.assertNull(countryOptional.get().get("0"));
        Assert.assertNull(countryOptional.get().get("09"));
    }
}
