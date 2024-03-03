package com.closer.citydistance.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String nickname;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    Set<CityRating> ratings;
}
