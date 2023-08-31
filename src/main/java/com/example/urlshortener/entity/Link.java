package com.example.urlshortener.entity;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Link {
  private String originalUrl;
  private String shortenedUrl;
  private Date createdAt;
  private ArrayList<Date> redirects;
  private long redirectCounter;
  private String ownerUsername;

  public Link() {
    createdAt = new Date();
    redirects = new ArrayList<>();
  }

  @Override
  public boolean equals(Object o) {

    if (o == this) {
      return true;
    }

    if (!(o instanceof Link)) {
      return false;
    }

    Link c = (Link) o;
      
    return (c.ownerUsername + c.originalUrl).equals(this.ownerUsername + this.originalUrl);
  }
}
