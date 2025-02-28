package com.urlshortener.urlshortener.factory;

public interface UrlEncodingStrategy {
    String generateShort(long id);
}
