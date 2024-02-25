package com.closer.citydistance.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Distance {
    private double length;
    private String firstCityName;
    private String secondCityName;
}
