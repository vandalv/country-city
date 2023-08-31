package com.andersen.countrycity.service;

import com.andersen.countrycity.dto.CityDTO;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {

    Page<CityDTO> getCitiesPage(Pageable pageable);

    List<String> getDistinctCityNames();

    void updateCityNameAndLogo(Long id, String name, String logo);

    List<CityDTO> getCitiesByCountry(String name);

    List<CityDTO> getCitiesByName(String name);
}
