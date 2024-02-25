package com.closer.citydistance.service;


import com.closer.citydistance.model.Distance;

public interface DistanceService {
    Distance findDistance(String firstCityName, String secondCityName);
}
