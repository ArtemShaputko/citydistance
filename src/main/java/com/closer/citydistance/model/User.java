package com.closer.citydistance.model;

import com.closer.citydistance.entity.UserEntity;
import lombok.Data;

@Data
public class User {
    private long id;
    private String name;
    private String surname;
    private String nickname;
    private String email;
    public static User toModel(UserEntity entity){
        if(entity==null) return null;
        User model = new User();
        model.setId(entity.getId());
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setNickname(entity.getNickname());
        model.setEmail(entity.getEmail());
        return model;
    }
}
