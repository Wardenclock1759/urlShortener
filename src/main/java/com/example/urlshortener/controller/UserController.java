package com.example.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortener.Service.UserService;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.utils.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService = new UserService();

  @Autowired
  private JwtUtil jwtUtil;

  @GetMapping(value = "/whoami", produces = "application/json")
  public ResponseEntity<?> getUser(HttpServletRequest request) {
    String result = userService.handleWhoami(request.getCookies());
    if (result != "") {
      return ResponseEntity.ok(result);
    }
    return (ResponseEntity<?>) ResponseEntity.notFound();
  }

  @PostMapping(value = "/register", produces = "application/json")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    User createdUser = userService.handleUserCreate(user);
    String token = jwtUtil.generateToken(createdUser);
    HttpHeaders headers = new HttpHeaders();
    headers.add("Set-Cookie", String.format("jwt=%s;Path=/;Max-Age=360000;HttpOnly", token));
    return new ResponseEntity<>(headers, CREATED);
  }
}
