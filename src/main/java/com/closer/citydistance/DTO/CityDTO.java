package com.closer.citydistance.DTO;

import com.closer.citydistance.model.City;
import lombok.Data;

@Data
public class CityDTO {
    private Long id;
    private String name;
    private String country;
    private double lon;
    private double lat;

    public static CityDTO toDTO(City model) {
        if (model == null) return null;
        CityDTO dto = new CityDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCountry(model.getCountry());
        dto.setLon(model.getLon());
        dto.setLat(model.getLat());
        return dto;
    }
}
