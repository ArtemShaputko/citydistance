package com.closer.citydistance.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    int id;
    String name;
    String surname;
    String nickname;
    List<City> favourites;
}
