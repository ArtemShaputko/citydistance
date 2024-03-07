package com.closer.citydistance.service.impl;

import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Distance;
import com.closer.citydistance.service.CalculatorService;
import com.closer.citydistance.service.DistanceService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Primary
@RequiredArgsConstructor
public class DistanceFromApiServiceImpl implements DistanceService {
    private static final String GEOCODING_API = "http://api.openweathermap.org/geo/1.0/direct?q=";
    private final CalculatorService distanceCalculator;

    @Value("${apiKey}")
    private  String apiKey;

    @Value("${spring.datasource.password}")
    @Override
    public Distance findDistance(String firstCityName, String secondCityName) {

        String firstCityRequestURL = GEOCODING_API + firstCityName + "&limit=1&appid=" + apiKey;
        String secondCityRequestURL = GEOCODING_API + secondCityName + "&limit=1&appid=" + apiKey;
        City firstCity;
        City secondCity;

        City[] firstCityResponse = WebClient.builder()
                .build()
                .get()
                .uri(firstCityRequestURL)
                .retrieve()
                .bodyToMono(City[].class)
                .block();
        City[] secondCityResponse = WebClient.builder()
                .build()
                .get()
                .uri(secondCityRequestURL)
                .retrieve()
                .bodyToMono(City[].class)
                .block();

        assert firstCityResponse != null;
        firstCity = firstCityResponse[0];
        assert secondCityResponse != null;
        secondCity = secondCityResponse[0];

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