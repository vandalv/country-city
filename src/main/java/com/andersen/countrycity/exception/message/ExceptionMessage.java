package com.andersen.countrycity.exception.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    CITY_NOT_FOUND("City Not Found."),
    COUNTRY_NOT_FOUND("Country Not Found."),
    USER_NOT_FOUND("User Not Found."),
    NO_CONTENT_PROVIDED("No Content Provided");

    private final String message;
}
