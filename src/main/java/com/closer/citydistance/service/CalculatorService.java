package com.closer.citydistance.service;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private static final double EARTH_RADIUS = 6371.0;
    public double calculateDistance(double firstLat, double firstLon, double secondLat, double secondLon)
    {
        return EARTH_RADIUS * Math.acos(Math.sin(firstLat) * Math.sin(secondLat)
                + Math.cos(firstLat) * Math.cos(secondLat) * Math.cos(firstLon - secondLon));
    }
}
