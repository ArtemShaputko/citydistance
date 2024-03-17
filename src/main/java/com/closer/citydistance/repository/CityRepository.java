package com.closer.citydistance.repository;

import com.closer.citydistance.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findCityByNameAndCountry(String name, String country);
    @Query("select c from City c join Sight s on s.city = c where s.name = :sightName")
    List<City> findCityBySightName(String sightName);

}
