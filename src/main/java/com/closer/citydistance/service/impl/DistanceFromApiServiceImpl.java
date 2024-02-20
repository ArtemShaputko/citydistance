package com.closer.citydistance.service.impl;

import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Distance;
import com.closer.citydistance.service.DistanceService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DistanceFromApiServiceImpl implements DistanceService {
    private static final double EARTH_RADIUS = 6371.0;
    private static final String GEOCODING_API = "http://api.openweathermap.org/geo/1.0/direct?";
    private static final String GEOCODING_API_KEY = "";
    @Override
    public Distance findDistance(String city1Name, String city2Name) {

        String url1 = GEOCODING_API +"q="+city1Name+"&limit=1&appid="+ GEOCODING_API_KEY;
        String url2 = GEOCODING_API +"q="+city2Name+"&limit=1&appid="+ GEOCODING_API_KEY;


        City city1 = WebClient.builder()
                .baseUrl(url1)
                .build()
                .get()
                .uri(url1)
                .retrieve()
                .bodyToMono(City.class)
                .block();
        City city2 = WebClient.builder()
                .baseUrl(url2)
                .build()
                .get()
                .uri(url2)
                .retrieve()
                .bodyToMono(City.class)
                .block();

        if(city1 != null && city2 !=null) {

            double radLat1 = Math.toRadians(city1.getLat());
            double radLon1 = Math.toRadians(city1.getLon());
            double radLat2 = Math.toRadians(city2.getLat());
            double radLon2 = Math.toRadians(city2.getLon());

            return Distance
                    .builder()
                    .length(EARTH_RADIUS * Math.acos(Math.sin(radLat1) * Math.sin(radLat2)
                            + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon1 - radLon2)))
                    .city1Name(city1Name)
                    .city2Name(city2Name)
                    .build();
        }
        return null;
    }
}
