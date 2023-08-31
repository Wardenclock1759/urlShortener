package com.example.urlshortener.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.repository.LinkRepository;

@Service
public class LinkService {
  @Autowired
  LinkRepository linkRepository;

  public Link createLink(User user, Link link) {
    Link newLink = linkRepository.CreateLink(user, link);
    if (newLink != null) {
      return newLink;
    }
    return null;
  }
}
