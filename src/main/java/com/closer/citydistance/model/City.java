package com.closer.citydistance.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@Table(name = "cities")
@NoArgsConstructor
@AllArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String country;
    private double lon;
    private double lat;

    @ManyToMany(mappedBy = "likedCities", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<User> userLikes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "city")
    @JsonIgnore
    private List<Sight> sights;
}
