package com.example.urlshortener.repository;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.example.urlshortener.entity.Link;
import com.example.urlshortener.entity.User;

@Repository
public class LinkRepository {
  private HashMap<String, Link> linkRepository;

  public LinkRepository() {
    linkRepository = new HashMap<String, Link>();
  }

  public Link CreateLink(User user, Link link) {
    String linkIdStrting = user.getUsername() + link.getOriginalUrl();
    if (user.getUserLinks() != null && linkRepository.containsKey(linkIdStrting)) {
      return null;
    };
    user.getUserLinks().add(link);
    link.setOwnerUsername(user.getUsername());
    linkRepository.put(linkIdStrting, link);
    return link;
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
