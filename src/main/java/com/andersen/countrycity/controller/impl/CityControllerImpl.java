package com.andersen.countrycity.controller.impl;

import com.andersen.countrycity.annotation.RequiredRoles;
import com.andersen.countrycity.controller.CityController;
import com.andersen.countrycity.dto.CityDTO;
import com.andersen.countrycity.service.impl.CityServiceImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cities")
public class CityControllerImpl implements CityController {

    private final CityServiceImpl cityServiceImpl;

    @GetMapping
    public ResponseEntity<Page<CityDTO>> getCitiesPage(
            @RequestParam int page,
            @RequestParam int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        return ResponseEntity.ok(cityServiceImpl.getCitiesPage(pageable));
    }

    @GetMapping("/unique")
    public ResponseEntity<List<String>> getUniqueCityNames() {

        return ResponseEntity.ok(cityServiceImpl.getDistinctCityNames());
    }

    @GetMapping("/country")
    public ResponseEntity<List<CityDTO>> getCitiesByCountry(
            @RequestParam String name) {

        return ResponseEntity.ok(cityServiceImpl.getCitiesByCountry(name));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CityDTO>> getCitiesByName(
            @RequestParam String name) {

        return ResponseEntity.ok(cityServiceImpl.getCitiesByName(name));
    }

    @PutMapping("/{id}/edit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequiredRoles("EDITOR")
    public void updateCityNameAndLogo(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String logo) {

        cityServiceImpl.updateCityNameAndLogo(id, name, logo);
    }
}
