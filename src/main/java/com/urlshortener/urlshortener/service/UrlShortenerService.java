package com.urlshortener.urlshortener.service;

import com.urlshortener.urlshortener.factory.ShortCodeGeneratorFactory;
import com.urlshortener.urlshortener.factory.UrlEncodingStrategy;
import com.urlshortener.urlshortener.model.ShortenedUrl;
import com.urlshortener.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.time.Instant;
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
    public String shortenUrl(String longUrl, int ttlInSeconds) throws IllegalAccessException {
        if(!isValidUrl(longUrl)) {
            throw new IllegalAccessException("Invalid URL!");
        }
        String existingShortUrl = urlRepository.getShortUrlByLongUrl(longUrl);
        if(existingShortUrl != null) {
            return existingShortUrl; //Returning short code;
        }

        String shortCode= encodingStrategy.generateShort(counter.incrementAndGet());
        Instant expiration = Instant.now().plusSeconds(ttlInSeconds);
        ShortenedUrl shortenedUrl = new ShortenedUrl(shortCode, longUrl);

        urlRepository.saveShortUrl(shortenedUrl, longUrl, shortCode);
        return shortenedUrl.getShortUrl();
    }
}
