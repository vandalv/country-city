package com.andersen.countrycity.controller;

import com.andersen.countrycity.annotation.RequiredRoles;
import com.andersen.countrycity.dto.CityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Cities Controller", description = "Controller For Managing City Information")
public interface CityController {

    @GetMapping
    @Operation(
            summary = "Get Cities Page",
            description = "Retrieve a paginated list of cities."
    )
            Page<CityDTO> getCitiesPage(
            @Parameter(description = "Page number") @RequestParam int page,
            @Parameter(description = "Number of items per page") @RequestParam int size
    );

    @GetMapping("/unique")
    @Operation(
            summary = "Get Unique City Names",
            description = "Retrieve a list of unique city names."
    )
    ResponseEntity<List<String>> getUniqueCityNames();

    @GetMapping("/country")
    @Operation(
            summary = "Get Cities By Country",
            description = "Retrieve a list of cities based on the country name."
    )
    ResponseEntity<List<CityDTO>> getCitiesByCountry(
            @Parameter(description = "Country name") @RequestParam String name
    );

    @GetMapping("/search")
    @Operation(
            summary = "Search Cities By Name",
            description = "Search for cities by name."
    )
    ResponseEntity<List<CityDTO>> getCitiesByName(
            @Parameter(description = "City name") @RequestParam String name
    );

    @PutMapping("/{id}/edit")
    @Operation(
            summary = "Update City Name and Logo",
            description = "Update the name and logo of a city."
    )
    @ResponseStatus(HttpStatus.OK)
    @RequiredRoles("EDITOR")
    void updateCityNameAndLogo(
            @Parameter(description = "City ID") @PathVariable Long id,
            @Parameter(description = "City name") @RequestParam String name,
            @Parameter(description = "City logo") @RequestParam String logo
    );
}
