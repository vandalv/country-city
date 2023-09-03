package com.andersen.countrycity.controller.impl;

import com.andersen.countrycity.data.TestDataFactory;
import com.andersen.countrycity.dto.CityDTO;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@ActiveProfiles("test")
class CityControllerImplTest {

    private String baseUrl;
    private String countryNameParameterValue;

    @Autowired
    private TestRestTemplate restTemplate;


    @BeforeEach
    public void setUp(){

        baseUrl = TestDataFactory.baseUrl();
        countryNameParameterValue = TestDataFactory.countryNameParameterValue();
    }

    @Test
    void getCitiesByCountry() {

        String url = baseUrl + "8737" + "/cities/country?name=" + countryNameParameterValue;

        ResponseEntity<List<CityDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CityDTO>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<CityDTO> citiesByCountry = response.getBody();
        assertNotNull(citiesByCountry);

        List<String> expectedCityNames = Arrays.asList("Brest", "Lille", "Marselle", "Paris");
        List<String> actualCityNames = citiesByCountry.stream()
                .map(CityDTO::getName)
                .toList();

        Assertions.assertIterableEquals(expectedCityNames, actualCityNames);
    }

    @Test
    void getUniqueCityNames() {

        ResponseEntity<List> response = restTemplate.getForEntity(
                baseUrl + "8737" + "/cities/unique",
                List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<String> uniqueCityNames = response.getBody();
        assertNotNull(uniqueCityNames);
    }
}