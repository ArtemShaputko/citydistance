package com.closer.citydistance.repository;

import com.closer.citydistance.model.City;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class CitiesInMemoryDAO {
    public City findCityByName(String cityName) {
        Random myRandom = new Random();
        return City.builder().name(cityName).lat(myRandom.nextDouble()).lon(myRandom.nextDouble()).build();

    }
}
