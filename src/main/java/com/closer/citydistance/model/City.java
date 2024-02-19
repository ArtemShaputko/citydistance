package com.closer.citydistance.model;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class City {
    @NonNull
    private String name;
    private String country;
    private double lon;
    private double lat;
}
