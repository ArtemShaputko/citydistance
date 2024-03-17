package com.closer.citydistance.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DistanceDTO {
    private double length;
    private String firstCityName;
    private String secondCityName;
}
