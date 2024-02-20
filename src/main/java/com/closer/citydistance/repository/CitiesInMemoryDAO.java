package com.closer.citydistance.repository;

import com.closer.citydistance.model.City;
import org.springframework.stereotype.Repository;

@Repository
public class CitiesInMemoryDAO {
    public City findCityByName(String cityName) {
        return City.builder()
                .name(cityName)
                .lat((double) System.currentTimeMillis())
                .lon((double) System.currentTimeMillis())
                .build();

    }
}
