package com.closer.citydistance.controller;

import com.closer.citydistance.model.Distance;
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

    @GetMapping("/{city1Name}&{city1Country}+{city2Name}&{city2Country}")
    public Distance findDistance(@PathVariable String city1Name,
                                 @PathVariable String city1Country,
                                 @PathVariable String city2Name,
                                 @PathVariable String city2Country) {
        return service.findDistance(city1Name, city1Country, city2Name, city2Country );
    }
}
