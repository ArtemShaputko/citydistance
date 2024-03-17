package com.closer.citydistance.mapper;

import com.closer.citydistance.dto.CityDTO;
import com.closer.citydistance.dto.SightDTO;
import com.closer.citydistance.dto.UserDTO;
import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Sight;
import com.closer.citydistance.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private Mapper() {
    }

    public static CityDTO cityToDTO(City model) {
        if (model == null) return null;
        CityDTO dto = new CityDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCountry(model.getCountry());
        dto.setLon(model.getLon());
        dto.setLat(model.getLat());
        return dto;
    }

    public static SightDTO sightToDTO(Sight model) {
        if (model == null) return null;
        SightDTO dto = new SightDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setCityName(model.getCity().getName());
        return dto;
    }

    public static UserDTO userToDTO(User model) {
        if (model == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setNickname(model.getNickname());
        dto.setEmail(model.getEmail());
        return dto;
    }
}
