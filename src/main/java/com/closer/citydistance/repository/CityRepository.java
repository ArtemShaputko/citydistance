package com.closer.citydistance.repository;

import com.closer.citydistance.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findCityByNameAndCountry(String name, String country, PageRequest pageRequest);

    @Query("select c from City c join Sight s on s.city = c where s.name = :sightName")
    Page<City> findCityBySightName(String sightName, Pageable pageable);
}
