package com.closer.citydistance.service;


import com.closer.citydistance.DTO.DistanceDTO;

public interface DistanceService {
    DistanceDTO findDistance(String firstCityName, String secondCityName);
}
