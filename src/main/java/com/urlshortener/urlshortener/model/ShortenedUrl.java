package com.urlshortener.urlshortener.model;

import lombok.Data;


class ShortenedUrl {
    private final String shortUrl;
    private final String longUrl;
    private Integer accessCount=0;

    public ShortenedUrl(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.accessCount=0;
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

    public void incrementAccessCount() {
        accessCount++;
    }
}
