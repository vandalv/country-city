package com.andersen.countrycity.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.andersen.countrycity.dto.CityDTO;
import com.andersen.countrycity.entity.City;
import com.andersen.countrycity.exception.CityNotFoundException;
import com.andersen.countrycity.mapper.CityMapper;
import com.andersen.countrycity.repository.CityRepository;
import com.andersen.countrycity.data.TestDataFactory;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class CityServiceImplTest {

    private Pageable pageable;
    private Page<City> cityPage;
    private List<City> cities;
    private City city1;
    private City city2;
    private City city3;
    private City city4;
    private List<String> distinctCityNames;
    private Long cityId;
    private String newName;
    private String newLogo;
    private String countryName;
    private String existingCity;

    @BeforeEach
    public void setUp() {
        pageable = TestDataFactory.pageable();
        city1 = TestDataFactory.city1();
        city2 = TestDataFactory.city2();
        city3 = TestDataFactory.city3();
        city4 = TestDataFactory.city4();
        cities = List.of(city1, city2, city3, city4);
        cityPage = TestDataFactory.cityPage();
        distinctCityNames = TestDataFactory.distinctCityNames();
        cityId = TestDataFactory.cityId();
        newName = TestDataFactory.newName();
        newLogo = TestDataFactory.newLogo();
        countryName = TestDataFactory.countryName();
        existingCity = TestDataFactory.existingCity();
    }

    @InjectMocks
    private CityServiceImpl cityService;

    @Mock
    private CityRepository cityRepository;

    @Mock
    private CityMapper cityMapper;

    @Test
    void getCitiesPage_success() {

        when(cityRepository.findAll(pageable)).thenReturn(cityPage);

        Page<CityDTO> resultPage = cityService.getCitiesPage(pageable);

        assertNotNull(resultPage);
        assertEquals(cities.size(), resultPage.getContent().size());

        verify(cityRepository).findAll(pageable);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    void getCitiesPage_emptyPage() {

        when(cityRepository.findAll(pageable)).thenReturn(Page.empty(pageable));

        Page<CityDTO> resultPage = cityService.getCitiesPage(pageable);

        assertNotNull(resultPage);
        assertTrue(resultPage.getContent().isEmpty());

        verify(cityRepository).findAll(pageable);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    void getDistinctCityNames_success() {

        when(cityRepository.findDistinctCityNames()).thenReturn(distinctCityNames);

        List<String> resultCityNames = cityService.getDistinctCityNames();

        assertNotNull(resultCityNames);
        assertEquals(distinctCityNames.size(), resultCityNames.size());

        verify(cityRepository).findDistinctCityNames();
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    void updateCityNameAndLogo_success() {

        City existingCity = TestDataFactory.city1();

        when(cityRepository.findById(cityId)).thenReturn(java.util.Optional.of(existingCity));

        cityService.updateCityNameAndLogo(cityId, newName, newLogo);

        verify(cityRepository).findById(cityId);
        verify(cityRepository).save(existingCity);
        verifyNoMoreInteractions(cityRepository);

        assertEquals(newName, existingCity.getName());
        assertEquals(newLogo, existingCity.getLogo());
    }

    @Test
    void updateCityNameAndLogo_cityNotFound() {

        when(cityRepository.findById(cityId)).thenReturn(java.util.Optional.empty());

        assertThrows(CityNotFoundException.class,
                () -> cityService.updateCityNameAndLogo(cityId, newName, newLogo));

        verify(cityRepository).findById(cityId);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    void getCitiesByCountry_success() {

        List<City> citiesForCountry = TestDataFactory.cities();

        when(cityRepository.findByCountryName(anyString())).thenReturn(citiesForCountry);

        List<CityDTO> resultCityDTOs = cityService.getCitiesByCountry(countryName);

        assertNotNull(resultCityDTOs);
        assertEquals(citiesForCountry.size(), resultCityDTOs.size());

        verify(cityRepository).findByCountryName(countryName);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    void getCitiesByName_success() {

        List<City> citiesByName = TestDataFactory.cities();

        when(cityRepository.findByName(anyString())).thenReturn(citiesByName);

        List<CityDTO> resultCityDTOs = cityService.getCitiesByName(existingCity);

        assertNotNull(resultCityDTOs);
        assertEquals(citiesByName.size(), resultCityDTOs.size());

        verify(cityRepository).findByName(existingCity);
        verifyNoMoreInteractions(cityRepository);
    }
}