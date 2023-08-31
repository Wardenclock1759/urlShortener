package com.example.urlshortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.urlshortener.Service.LinkService;
import com.example.urlshortener.Service.UserService;
import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;
import com.google.gson.Gson;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/links")
public class LinkController {
  @Autowired
  LinkService linkService;
  
  @Autowired
  UserService userService;

  public Gson jsonParser = new Gson();
  
  private String checkPermission(Cookie[] cookies) {
    String result = userService.handleWhoami(cookies);
    return result;
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<?> createLink(@RequestBody Link link, HttpServletRequest request) {
    String userJson = checkPermission(request.getCookies());
    
    if (userJson != "") {
      User user = jsonParser.fromJson(userJson, User.class);
      Link newLink = linkService.createLink(user, link);
      if (newLink != null) {
        return ResponseEntity.ok(newLink);
      }
      return (ResponseEntity<?>) ResponseEntity.status(409).body("{error: Conflict on creating link}");
    }

    return (ResponseEntity<?>) ResponseEntity.status(403).body("{error: Forbiden}");
  }
}
