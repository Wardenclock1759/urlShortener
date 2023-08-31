package com.example.urlshortener.utils;

import org.springframework.stereotype.Service;

@Service
public class UrlShortener { 

  public String idToShortURL(int n) 
  { 
    char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray(); 
  
    StringBuffer shorturl = new StringBuffer(); 
  
    while (n > 0) 
    { 
      shorturl.append(map[n % 62]);
      n = n / 62; 
    } 
    
    return shorturl.reverse().toString(); 
  }
}
