package com.closer.citydistance.service;


import com.closer.citydistance.model.Distance;

public interface DistanceService {
    Distance findDistance(String city1Name, String city2Name);
}
