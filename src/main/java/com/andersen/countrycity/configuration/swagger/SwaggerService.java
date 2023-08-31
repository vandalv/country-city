package com.andersen.countrycity.configuration.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponse(responseCode = "200",
        description = "The request was processed successfully", content = @Content)
@ApiResponse(responseCode = "401",
        description = "Indicates that the request requires authentication or the provided credentials are invalid.", content = @Content)
@ApiResponse(responseCode = "500",
        description = "Internal server error", content = @Content)
public @interface SwaggerService {
}
