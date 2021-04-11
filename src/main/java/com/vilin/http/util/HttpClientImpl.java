package com.vilin.http.util;

import com.vilin.http.exception.ConnException;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;
import java.util.Optional;

public class HttpClientImpl implements HttpClient{

    private final static String ERROR_CODE = "404";

    private final static int RETRY_NUM = 3;

    private final OkHttpClient httpClient = new OkHttpClient();

    public String getRequest(String url) throws IOException {
        final Request request = new Request.Builder().url(url).build();
        final Call call = httpClient.newCall(request).clone();
        return call.execute().body().string();
    }

    @Override
    public Optional<String> retryGetRequest(String url) {
        Optional<String> result = Optional.empty();

        for(int i = 0; i < RETRY_NUM; i++){
            try {
                result = Optional.ofNullable(getRequest(url));
                if(!result.get().contains(ERROR_CODE)){
                    break;
                }else{
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }

        if(null == result.get() || result.get().contains(ERROR_CODE)){
            throw new ConnException("retry " + RETRY_NUM + " times, connection failed");
        }else {
            return result;
        }
    }
}
