package com.example.urlshortener.entity;

import java.util.ArrayList;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class User {
  private String email;

  private String username;

  private ArrayList<Link> userLinks;

  public User() {
    userLinks = new ArrayList<>();
  }
}
