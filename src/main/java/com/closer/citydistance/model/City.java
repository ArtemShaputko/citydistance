package com.closer.citydistance.model;

import com.closer.citydistance.entity.CityEntity;
import lombok.Data;

@Data
public class City {
    private Long id;
    private String name;
    private String country;
    private double lon;
    private double lat;

    public static City toModel(CityEntity entity){
        if(entity==null) return null;
        City model = new City();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCountry(entity.getCountry());
        model.setLon(entity.getLon());
        model.setLat(entity.getLat());
        return model;
    }
}
