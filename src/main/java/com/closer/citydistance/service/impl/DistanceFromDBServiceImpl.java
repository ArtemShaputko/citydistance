package com.closer.citydistance.service.impl;

import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Distance;
import com.closer.citydistance.repository.CitiesInMemoryDAO;
import com.closer.citydistance.service.CalculatorService;
import com.closer.citydistance.service.DistanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DistanceFromDBServiceImpl implements DistanceService {
    private final CitiesInMemoryDAO repository;
    private final CalculatorService distanceCalculator;
    @Override
    public Distance findDistance(String firstCityName, String secondCityName) {
        City firstCity = repository.findCityByName(firstCityName);
        City secondCity = repository.findCityByName(secondCityName);

        if(firstCity != null && secondCity !=null) {
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
        return null;
    }
}
