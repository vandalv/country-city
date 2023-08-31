package com.andersen.countrycity.service;

import java.util.Set;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public interface ExtractTokenSubjectService {

    Set<SimpleGrantedAuthority> extractAuthoritiesFromToken(String token);
}
