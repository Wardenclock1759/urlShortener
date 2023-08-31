package com.example.urlshortener.repository;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;
import com.example.urlshortener.utils.UrlShortener;

@Repository
public class LinkRepository {
  @Autowired
  UrlShortener urlShortener;

  private HashMap<String, Link> linkRepository;

  public LinkRepository() {
    linkRepository = new HashMap<String, Link>();
  }

  public Link CreateLink(User user, Link link) {
    String linkIdStrting = user.getUsername() + link.getOriginalUrl();
    if (linkRepository.containsKey(linkIdStrting)) {
      return null;
    };

    Link newLink = new Link();
    newLink.setOriginalUrl(link.getOriginalUrl());
    newLink.setOwnerUsername(link.getOwnerUsername());
    user.getUserLinks().add(0, newLink);
    newLink.setShortenedUrl(urlShortener.idToShortURL(user.getUserLinks().size()));
    newLink.setOwnerUsername(user.getUsername());
    
    linkRepository.put(linkIdStrting, newLink);
    
    return newLink;
  }

  public Link GetLinkByUsername(Link link) {
    String linkIdStrting = link.getOwnerUsername() + link.getOriginalUrl();
    return linkRepository.get(linkIdStrting);
  }

  public Boolean DeleteLinkById(Link link) {
    String linkIdStrting = link.getOwnerUsername() + link.getOriginalUrl();
    if (linkRepository.containsKey(linkIdStrting)) {
      linkRepository.remove(linkIdStrting);
      return true;
    }
    return false;
  }
}
