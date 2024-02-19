package com.closer.citydistance.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Distance {
    private double distance;
    private String city1Name;
    private String city2Name;
}
