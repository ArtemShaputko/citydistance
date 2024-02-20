package com.closer.citydistance.service.impl;

import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Distance;
import com.closer.citydistance.repository.CitiesInMemoryDAO;
import com.closer.citydistance.service.DistanceService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
public class DistanceFromDBServiceImpl implements DistanceService {
    private final CitiesInMemoryDAO repository;
    private static final double EARTH_RADIUS = 6371.01;
    @Override
    public Distance findDistance(String city1Name, String city2Name) {
        City city1 = repository.findCityByName(city1Name);
        City city2 = repository.findCityByName(city2Name);

        if(city1 != null && city2 !=null) {

            double radLat1 = Math.toRadians(city1.getLat());
            double radLon1 = Math.toRadians(city1.getLon());
            double radLat2 = Math.toRadians(city2.getLat());
            double radLon2 = Math.toRadians(city2.getLon());

            return Distance
                    .builder()
                    .length(EARTH_RADIUS * Math.acos(Math.sin(radLat1) * Math.sin(radLat2)
                            + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon1 - radLon2)))
                    .city1Name(city1Name)
                    .city2Name(city2Name)
                    .build();
        }
        return null;
    }
}
