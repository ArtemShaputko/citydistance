package com.closer.citydistance.service;

import com.closer.citydistance.dto.CityDTO;
import com.closer.citydistance.dto.DistanceDTO;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Primary
@RequiredArgsConstructor
public class DistanceService {
    private static final String GEOCODING_API = "http://api.openweathermap.org/geo/1.0/direct?q=";
    private final CalculatorService distanceCalculator;

    @Value("${apiKey}")
    private String apiKey;

    public DistanceDTO findDistance(String firstCityName, String secondCityName) {

        String firstCityRequestURL = GEOCODING_API + firstCityName + "&limit=1&appid=" + apiKey;
        String secondCityRequestURL = GEOCODING_API + secondCityName + "&limit=1&appid=" + apiKey;
        CityDTO firstCityDTO;
        CityDTO secondCityDTO;

        CityDTO[] firstCityResponseDTO = WebClient.builder()
                .build()
                .get()
                .uri(firstCityRequestURL)
                .retrieve()
                .bodyToMono(CityDTO[].class)
                .block();
        CityDTO[] secondCityResponseDTO = WebClient.builder()
                .build()
                .get()
                .uri(secondCityRequestURL)
                .retrieve()
                .bodyToMono(CityDTO[].class)
                .block();

        assert firstCityResponseDTO != null;
        firstCityDTO = firstCityResponseDTO[0];
        assert secondCityResponseDTO != null;
        secondCityDTO = secondCityResponseDTO[0];

        return DistanceDTO
                .builder()
                .length(distanceCalculator.calculateDistance(Math.toRadians(firstCityDTO.getLat()),
                        Math.toRadians(firstCityDTO.getLon()),
                        Math.toRadians(secondCityDTO.getLat()),
                        Math.toRadians(secondCityDTO.getLon())))
                .firstCityName(firstCityName)
                .secondCityName(secondCityName)
                .build();
    }
}