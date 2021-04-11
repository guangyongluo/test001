package com.vilin.http.util;

import com.vilin.http.exception.ConnException;

import java.util.Optional;

public interface HttpClient {

    Optional<String> retryGetRequest(String url) throws ConnException;
}
