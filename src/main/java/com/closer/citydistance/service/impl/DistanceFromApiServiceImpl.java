package com.closer.citydistance.service.impl;

import com.closer.citydistance.model.City;
import com.closer.citydistance.service.DistanceService;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DistanceFromApiServiceImpl implements DistanceService {
    private final static double  earthRadius = 6371.01;
    private final WebClient.Builder builder =  WebClient.builder();
    private final static String geocodingApi = "http://api.openweathermap.org/geo/1.0/direct?";
    private final static String geocodingApiKey = "2598379fd45365cefd517bd84023d353";
    @Override
    public double findDistance(String city1Name, String city2Name) {

        String url1 = geocodingApi+"q="+city1Name+"&limit=1&appid="+ geocodingApiKey;
        String url2 = geocodingApi+"q="+city2Name+"&limit=1&appid="+ geocodingApiKey;

        City city1 = builder.build()
                .get()
                .uri(url1)
                .retrieve()
                .bodyToMono(City.class)
                .block();
        City city2 = builder.build()
                .get()
                .uri(url2)
                .retrieve().bodyToMono(City.class).block();

        if(city1 != null && city2 !=null) {

            double radLat1 = Math.toRadians(city1.getLat());
            double radLon1 = Math.toRadians(city1.getLon());
            double radLat2 = Math.toRadians(city2.getLat());
            double radLon2 = Math.toRadians(city2.getLon());

            return earthRadius * Math.acos(Math.sin(radLat1) * Math.sin(radLat2)
                    + Math.cos(radLat1) * Math.cos(radLat2) * Math.cos(radLon1 - radLon2));
        }
        return 0;
    }
}
