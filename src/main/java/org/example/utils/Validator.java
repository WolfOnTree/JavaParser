package org.example.utils;

import java.net.URI;

public class Validator {
    public static boolean isValidURL(String URL){
        try {
            URI uri = URI.create(URL);
            return uri.getScheme() != null && uri.getScheme().equals("https") && uri.getHost() != null;
        }catch (IllegalArgumentException e){
            return false;
        }
    }
}
