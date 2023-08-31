package com.example.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.UserRepository;
import com.example.urlshortener.utils.JwtUtil;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping(value = "/whoami", produces = "application/json")
  public ResponseEntity<?> getUser(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("jwt")) {
          String token = cookie.getValue();
          return ResponseEntity.ok(jwtUtil.extractUsername(token));
         }
       }
     }
    return null;
  }

  @PostMapping(value = "/register", produces = "application/json")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    User createdUser = userRepository.CreateUser(user);
    String token = jwtUtil.generateToken(createdUser);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Set-Cookie", String.format("jwt=%s;Path=/;Max-Age=360000;HttpOnly", token));
    return new ResponseEntity<>(headers, CREATED);
  }
}
