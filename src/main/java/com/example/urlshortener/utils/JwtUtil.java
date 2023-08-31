package com.example.urlshortener.utils;

import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.urlshortener.entity.User;

@Service
public class JwtUtil {
  private final String secret = "secret";

  Algorithm algorithm = Algorithm.HMAC256(secret);
  JWTVerifier verifier = JWT.require(algorithm)
  .build();

  public JwtUtil() {

  }

  public String generateToken(User user) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + 86400000);

    String token = JWT.create()
    .withClaim("email", user.getEmail())
    .withClaim("username", user.getUsername())
    .withIssuedAt(now)
    .withExpiresAt(expiryDate)
    .sign(algorithm);

    return token;
  }

  public String extractUsername(String token) {
    Base64.Decoder decoder = Base64.getUrlDecoder();
    String[] chunks = token.split("\\.");

    String payload = new String(decoder.decode(chunks[1]));

    return payload;
  }
}