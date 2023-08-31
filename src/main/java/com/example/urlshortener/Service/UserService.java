package com.example.urlshortener.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import com.example.urlshortener.utils.JwtUtil;

import jakarta.servlet.http.Cookie;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtUtil jwtUtil;
  
  public String handleWhoami(Cookie[] cookies) {
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("jwt")) {
          String token = cookie.getValue();
          return jwtUtil.extractUsername(token);
         }
       }
     }
    return "";
  }

  public User handleUserCreate(User user) {
    return userRepository.CreateUser(user);
  }
}
