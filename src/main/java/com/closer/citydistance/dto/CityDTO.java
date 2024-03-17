package com.closer.citydistance.dto;

import lombok.Data;

@Data
public class CityDTO {
    private Long id;
    private String name;
    private String country;
    private double lon;
    private double lat;
}
