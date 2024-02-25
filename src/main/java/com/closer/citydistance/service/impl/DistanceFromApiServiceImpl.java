package com.closer.citydistance.service.impl;

import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Distance;
import com.closer.citydistance.service.CalculatorService;
import com.closer.citydistance.service.DistanceService;
import io.github.cdimascio.dotenv.Dotenv;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Primary
@RequiredArgsConstructor
public class DistanceFromApiServiceImpl implements DistanceService {
    private static final String GEOCODING_API = "http://api.openweathermap.org/geo/1.0/direct?q=";
    private final CalculatorService distanceCalculator;

    private final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    private final String apiKey = dotenv.get("API_KEY");
    @Override
    public Distance findDistance(String firstCityName, String secondCityName) {

        String firstCityRequestURL = GEOCODING_API + firstCityName + "&limit=1&appid=" + apiKey;
        String secondCityRequestURL = GEOCODING_API + secondCityName + "&limit=1&appid=" + apiKey;
        City firstCity, secondCity;
        try {

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
        }
        catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            return null;
        }

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