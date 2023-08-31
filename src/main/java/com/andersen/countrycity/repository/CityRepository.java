package com.andersen.countrycity.repository;

import com.andersen.countrycity.entity.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT DISTINCT c.name FROM City c")
    List<String> findDistinctCityNames();

    List<City> findByCountryName(String name);

    List<City> findByName(String name);
}
