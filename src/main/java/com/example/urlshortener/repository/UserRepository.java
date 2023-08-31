package com.example.urlshortener.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;

@Repository
public class UserRepository {
  private HashMap<String, User> userRepository;

  public UserRepository() {
    userRepository = new HashMap<String, User>();
  }

  public User CreateUser(User user) {
    userRepository.put(user.getUsername(), user);
    return user;
  }

  public User GetUserByUsername(String username) {
    return userRepository.get(username);
  }

  public ArrayList<Link> getUserLinks(String username) {
    User user = GetUserByUsername(username);
    return user.getUserLinks();
  }
}