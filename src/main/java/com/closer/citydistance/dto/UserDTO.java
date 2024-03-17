package com.closer.citydistance.dto;

import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String surname;
    private String nickname;
    private String email;
}
