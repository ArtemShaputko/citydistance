package com.closer.citydistance.model;

import com.closer.citydistance.entity.SightEntity;
import lombok.Data;

@Data
public class Sight {
    private long id;
    private String name;
    private City city;

    public static Sight toModel(SightEntity entity)
    {
        if(entity==null) return null;
        Sight model = new Sight();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setCity(City.toModel(entity.getCity()));
        return model;
    }
}