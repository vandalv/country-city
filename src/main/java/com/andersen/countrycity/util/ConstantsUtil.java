package com.andersen.countrycity.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantsUtil {

    public static final int PASSWORD_STRENGTH = 11;
    public static final int TOKEN_EXPIRATION_TIME = 1000 * 60;
    public static final int TOKEN_BEGIN_INDEX = 7;
    public static final String TOKEN_IDENTIFIER = "Bearer ";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String[] PUBLIC_REQUEST_MATCHERS = {
            "/users/**", "/swagger-ui**", "/openapi/**", "/cities/country", "/cities/unique", "/cities/search**","/cities","/swagger-ui.html", "/swagger-ui/**"
    };
}
