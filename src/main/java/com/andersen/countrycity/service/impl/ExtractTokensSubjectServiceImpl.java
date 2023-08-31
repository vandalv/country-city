package com.andersen.countrycity.service.impl;

import com.andersen.countrycity.service.ExtractTokenSubjectService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service
public class ExtractTokensSubjectServiceImpl implements ExtractTokenSubjectService {

    @Value("${secrets.jwt-key}")
    private String key;

    @Override
    public Set<SimpleGrantedAuthority> extractAuthoritiesFromToken(String token) {
        String jwtToken = token.replace("Bearer ", "");

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();

        List<String> authorities = (List<String>) claims.get("authorities");
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
