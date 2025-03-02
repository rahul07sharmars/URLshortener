package com.urlshortener.urlshortener.factory;

import java.util.Base64;

public class Base64EncodingStrategy implements UrlEncodingStrategy {

    @Override
    public String generateShort(long id) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(String.valueOf(id).getBytes());
    }
}
