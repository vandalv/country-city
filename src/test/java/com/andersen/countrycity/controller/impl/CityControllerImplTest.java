package com.andersen.countrycity.controller.impl;

import com.andersen.countrycity.configuration.TestContainerConfiguration;
import com.andersen.countrycity.data.TestDataFactory;
import com.andersen.countrycity.dto.AuthenticationRequestDTO;
import com.andersen.countrycity.dto.AuthenticationResponseDTO;
import com.andersen.countrycity.dto.CityDTO;
import com.andersen.countrycity.dto.RegistrationRequestDTO;
import com.andersen.countrycity.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@ContextConfiguration(initializers = TestContainerConfiguration.Initializer.class)
class CityControllerImplTest {

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private WebTestClient webTestClient;

    private String baseUrl;
    private String countryNameParameterValue;

    @BeforeEach
    public void setUp() {
        baseUrl = "http://localhost:" + randomServerPort;
        countryNameParameterValue = TestDataFactory.countryNameParameterValue();
    }

    @Test
    void getCitiesByCountry() {
        String url = baseUrl + "/cities/country?name=" + countryNameParameterValue;

        List<CityDTO> citiesByCountry = webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CityDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(citiesByCountry);

        List<String> expectedCityNames = Arrays.asList("Brest", "Lille", "Marselle", "Paris");
        List<String> actualCityNames = citiesByCountry.stream()
                .map(CityDTO::getName)
                .toList();

        assertEquals(expectedCityNames, actualCityNames);
    }

    @Test
    void getUniqueCityNames() {
        String url = baseUrl + "/cities/unique";

        List<String> uniqueCityNames = webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(uniqueCityNames);
    }

    @Test
    void getCitiesByCountry_ValidCountryName_ShouldReturnCityList() {

        String validCountryName = "France";
        String url = baseUrl + "/cities/country?name=" + validCountryName;

        List<CityDTO> citiesByCountry = webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CityDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(citiesByCountry);
        assertFalse(citiesByCountry.isEmpty());
    }

    @Test
    void getCitiesByCountry_InvalidCountryName_ShouldReturnEmptyList() {

        String invalidCountryName = "NonExistentCountry";
        String url = baseUrl + "/cities/country?name=" + invalidCountryName;

        List<CityDTO> citiesByCountry = webTestClient
                .get()
                .uri(url)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(CityDTO.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(citiesByCountry);
        assertTrue(citiesByCountry.isEmpty());
    }

    private String authenticateAndGetBearerToken(String email, String password) {
        AuthenticationRequestDTO authRequest = new AuthenticationRequestDTO();
        authRequest.setEmail(email);
        authRequest.setPassword(password);

        EntityExchangeResult<AuthenticationResponseDTO> authenticationResponse = webTestClient
                .post()
                .uri("users/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(authRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthenticationResponseDTO.class)
                .returnResult();

        String bearerToken = authenticationResponse.getResponseBody().getToken();

        return "Bearer " + bearerToken;
    }

    private void testUpdateCityWithToken(String email, String password, Long cityId, String newName,
            String newLogo, HttpStatus expectedStatus) {

        String jwtToken = authenticateAndGetBearerToken(email, password);

        webTestClient
                .put()
                .uri("/cities/" + cityId + "/edit?name=" + newName + "&logo=" + newLogo)
                .header("Authorization", jwtToken)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus);
    }

    @Test
    void updateCityNameAndLogo_WithValidBearerToken_ShouldReturnNoContent() {
        testUpdateCityWithToken("2@example.com", "12345", 1L, "Paris", "qBw9snD/Szczecin-PL.png",
                HttpStatus.NO_CONTENT);
    }

    @Test
    void updateCityNameAndLogo_WithInvalidBearerToken_ShouldReturnForbidden() {
        testUpdateCityWithToken("1@example.com", "12345", 1L, "Paris", "qBw9snD/Szczecin-PL.png",
                HttpStatus.FORBIDDEN);
    }

    @Test
    void testRegisterUserWithInvalidInput() {

        String url = baseUrl + "/users/register";

        RegistrationRequestDTO invalidRequest = RegistrationRequestDTO.builder()
                .email("invalid-email")
                .password("123")
                .build();

        webTestClient
                .post()
                .uri(url)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(invalidRequest)
                .exchange()
                .expectStatus().isBadRequest();
    }
}