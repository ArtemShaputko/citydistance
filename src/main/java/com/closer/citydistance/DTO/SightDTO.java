package com.closer.citydistance.DTO;

import com.closer.citydistance.model.Sight;
import lombok.Data;

@Data
public class SightDTO {
    private long id;
    private String name;
    private CityDTO cityDTO;

    public static SightDTO toDTO(Sight model) {
        if (model == null) return null;
        SightDTO dto = new SightDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCityDTO(CityDTO.toDTO(model.getCity()));
        return dto;
    }
}