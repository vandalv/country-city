package com.andersen.countrycity.service.impl;

import com.andersen.countrycity.dto.CityDTO;
import com.andersen.countrycity.entity.City;
import com.andersen.countrycity.exception.CityNotFoundException;
import com.andersen.countrycity.exception.message.ExceptionMessage;
import com.andersen.countrycity.mapper.CityMapper;
import com.andersen.countrycity.repository.CityRepository;
import com.andersen.countrycity.service.CityService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    @Value("${image.server}")
    private String imageServer;

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public Page<CityDTO> getCitiesPage(Pageable pageable) {

        Page<City> cityPage = cityRepository.findAll(pageable);
        List<CityDTO> cityDTOs = cityPage.getContent().stream()
                .map(city -> cityMapper.mapToDTO(city, imageServer))
                .toList();

        return new PageImpl<>(cityDTOs, pageable, cityPage.getTotalElements());
    }

    @Override
    public List<String> getDistinctCityNames() {

        return cityRepository.findDistinctCityNames();
    }

    @Override
    @Transactional
    public void updateCityNameAndLogo(Long id, String name, String logo) {

        City city = cityRepository.findById(id)
                .orElseThrow(() -> new CityNotFoundException(
                        String.format(ExceptionMessage.CITY_NOT_FOUND.getMessage())));

        city.setName(formatName(name));
        city.setLogo(logo);

        cityRepository.save(city);
    }

    @Override
    public List<CityDTO> getCitiesByCountry(String name) {

        return cityRepository.findByCountryName(formatName(name)).stream()
                .map(city -> cityMapper.mapToDTO(city, imageServer))
                .toList();
    }

    @Override
    public List<CityDTO> getCitiesByName(String name) {

        List<City> cities = cityRepository.findByName(formatName(name));

        if (cities.isEmpty()) {
            throw new CityNotFoundException(
                    String.format(ExceptionMessage.CITY_NOT_FOUND.getMessage()));
        }

        return cities.stream()
                .map(city -> cityMapper.mapToDTO(city, imageServer))
                .toList();
    }

    private String formatName(String name) {

        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
