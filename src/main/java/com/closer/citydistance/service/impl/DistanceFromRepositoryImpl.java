package com.closer.citydistance.service.impl;

import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Distance;
import com.closer.citydistance.repository.CityRepository;
import com.closer.citydistance.service.CalculatorService;
import com.closer.citydistance.service.DistanceService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistanceFromRepositoryImpl implements DistanceService {
    private final CalculatorService distanceCalculator;
    private final CityRepository cityRepository;
    @Override
    public Distance findDistance(String firstCityName, String secondCityName) {

        City firstCity = new City();
        City secondCity = new City();


        return Distance
                .builder()
                .length(distanceCalculator.calculateDistance(Math.toRadians(firstCity.getLat()),
                        Math.toRadians(firstCity.getLon()),
                        Math.toRadians(secondCity.getLat()),
                        Math.toRadians(secondCity.getLon())))
                .firstCityName(firstCityName)
                .secondCityName(secondCityName)
                .build();
    }
}