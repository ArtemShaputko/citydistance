package com.closer.citydistance.repository;

import com.closer.citydistance.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<CityEntity, Long> {
    List<CityEntity> findCityEntityByName(String name);
    List<CityEntity> findCityEntityByNameAndCountry(String name, String country);
    void deleteCityEntityByNameAndCountry(String name, String country);
}
