package com.urlshortener.urlshortener.model;

import lombok.Data;

import java.time.Instant;


public class ShortenedUrl {
    private final String shortUrl;
    private final String longUrl;
    private Integer accessCount=0;
    private final Instant expirationTime;

    public ShortenedUrl(String shortUrl, String longUrl, Instant expirationTime) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.accessCount=0;
        this.expirationTime = expirationTime;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public Integer getAccessCount() {
        return accessCount;
    }

    public Instant getExpirationTime() {
        return expirationTime;
    }

    public void incrementAccessCount() {
        accessCount++;
    }
}
