package com.closer.citydistance.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private String nickname;
    private String email;
    private List<CityDTO> cities;
}
