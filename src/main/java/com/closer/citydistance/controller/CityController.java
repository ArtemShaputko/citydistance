package com.closer.citydistance.controller;

import com.closer.citydistance.service.DistanceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city_distance")
@AllArgsConstructor
public class CityController {
    private final DistanceService service;

    @GetMapping("/{city1Name}+{city2Name}")
    public double findDistance(@PathVariable String city1Name, @PathVariable String city2Name) {
        return service.findDistance(city1Name, city2Name);
    }
}
