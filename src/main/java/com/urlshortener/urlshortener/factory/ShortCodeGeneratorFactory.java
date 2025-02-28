package com.urlshortener.urlshortener.factory;

public class ShortCodeGeneratorFactory {
    public static UrlEncodingStrategy getUrlEncodingStrategy() {
        return new Base62EncodingStrategy();
    }
}
