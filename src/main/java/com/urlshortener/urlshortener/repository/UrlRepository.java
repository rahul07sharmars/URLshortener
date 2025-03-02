package com.urlshortener.urlshortener.repository;

import com.urlshortener.urlshortener.model.ShortenedUrl;

import java.util.concurrent.ConcurrentHashMap;

public class UrlRepository {
    private static UrlRepository instance;
    private final ConcurrentHashMap<String, ShortenedUrl> shortToLongUrl = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> longToShortUrl = new ConcurrentHashMap<>();

    private UrlRepository() {}

    //singleton pattern
    public static synchronized UrlRepository getInstance() {
        if (instance == null) {
            instance = new UrlRepository();
        }
        return instance;
    }

    public String getShortUrlByLongUrl(String url) {
        return longToShortUrl.get(url);
    }
    public void saveShortUrl(ShortenedUrl shortenedUrl, String longUrl, String shortUrl) {
        shortToLongUrl.put(shortUrl, shortenedUrl);
        longToShortUrl.put(longUrl, shortUrl);
    }

    public ShortenedUrl getByShortUrl(String shortUrl) {
        return shortToLongUrl.get(shortUrl);
    }

    public void removeUrl(ShortenedUrl shortenedUrl) {
        shortToLongUrl.remove(shortenedUrl.getShortUrl());
        longToShortUrl.remove(shortenedUrl.getLongUrl());
    }
}
