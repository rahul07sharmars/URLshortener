package com.urlshortener.urlshortener.controller;

import com.urlshortener.urlshortener.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("This is a test");
    }

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String longUrl,
                                             @RequestParam(defaultValue = "0") int ttl) {
        try {
            String shortUrl = urlShortenerService.shortenUrl(longUrl, ttl);
            return ResponseEntity.ok(shortUrl);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<String> getUrl(@PathVariable String shortCode) {
        String longUrl = null;
        try {
            longUrl = urlShortenerService.getLongUrl(shortCode);
            return ResponseEntity.ok(longUrl);
        }
        catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("accessCount/{shortCode}")
    public ResponseEntity<String> accessCount(@PathVariable String shortCode) {
        try {
            int accessCount = urlShortenerService.getAccessCount(shortCode);
            return ResponseEntity.ok(Integer.toString(accessCount));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
