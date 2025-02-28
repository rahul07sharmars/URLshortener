package com.urlshortener.urlshortener.service;

import com.urlshortener.urlshortener.factory.ShortCodeGeneratorFactory;
import com.urlshortener.urlshortener.factory.UrlEncodingStrategy;
import com.urlshortener.urlshortener.model.ShortenedUrl;
import com.urlshortener.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlShortenerService {

    private final UrlRepository urlRepository = UrlRepository.getInstance();
    private final AtomicLong counter = new AtomicLong(1000000);
    private final UrlEncodingStrategy encodingStrategy= ShortCodeGeneratorFactory.getUrlEncodingStrategy();

    private boolean isValidUrl(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getLongUrl(String shortUrl) {
        ShortenedUrl shortenedUrl = urlRepository.getByShortUrl(shortUrl);
        if (shortenedUrl == null) {
            try {
                throw new IllegalAccessException("Short URL not found");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        shortenedUrl.incrementAccessCount();
        return  shortenedUrl.getLongUrl();
    }
}
