package com.andersen.countrycity.data;

import com.andersen.countrycity.dto.CityDTO;
import com.andersen.countrycity.entity.City;
import com.andersen.countrycity.entity.Country;
import com.andersen.countrycity.entity.Role;
import com.andersen.countrycity.entity.User;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class TestDataFactory {

    public static Validator validator() { return Validation.buildDefaultValidatorFactory().getValidator(); }

    public static String baseUrl() { return "http://localhost:"; }
    public static String countryNameParameterValue() { return "France"; }

    public static Page<City> cityPage() { return new PageImpl<>(cities(), pageable(), cities().size()); }
    public static Pageable pageable() { return PageRequest.of(0, 10) ;}
    public static List<String> distinctCityNames() { return Arrays.asList("City 1", "City 2", "City 3"); }
    public static List<City> cities() { return List.of(city1(), city2(), city3(), city4()) ;}
    public static Long cityId() { return 1L ;}
    public static String newName() { return "New city name"; }
    public static String newLogo() { return "new_logo.png"; }
    public static String countryName() { return "Country 1"; }
    public static String existingCity() { return "City 1"; }
    public static String email() { return "user@example.com"; }
    public static String password() { return "password"; }
    public static String encodedPassword() { return "encodedPassword"; }

    public static CityDTO cityDTO1() {
        return CityDTO.builder()
                .id(1L)
                .name("city")
                .logo("city_logo.png")
                .build();
    }

    public static City city1() {
        return City.builder()
                .id(1L)
                .name("City 1")
                .logo("city1_logo.png")
                .country(country1())
                .build();
    }

    public static City city2() {
        return City.builder()
                .id(2L)
                .name("City 2")
                .logo("city2_logo.png")
                .country(country1())
                .build();
    }

    public static City city3() {
        return City.builder()
                .id(1L)
                .name("City 1")
                .logo("city3_logo.png")
                .country(country2())
                .build();
    }

    public static City city4() {
        return City.builder()
                .id(2L)
                .name("City 2")
                .logo("city4_logo.png")
                .country(country2())
                .build();
    }

    public static Country country1() {
        return Country.builder()
                .id(1L)
                .name("Country 1")
                .build();
    }

    public static Country country2() {
        return Country.builder()
                .id(2L)
                .name("Country 2")
                .build();
    }

    public static User user1() {
        return User.builder()
                .id(1L)
                .email("user@example.com")
                .password("password")
                .role(Role.USER)
                .build();
    }
}
