package com.closer.citydistance.dto;

import com.closer.citydistance.model.User;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private String nickname;
    private String email;
    private List<CityDTO> likedCities;

    public static UserDTO toDTO(User model) {
        if (model == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(model.getId());
        dto.setName(model.getName());
        dto.setSurname(model.getSurname());
        dto.setNickname(model.getNickname());
        dto.setEmail(model.getEmail());
        dto.setLikedCities(model.getLikedCities().stream().map(CityDTO::toDTO).toList());
        return dto;
    }
}
