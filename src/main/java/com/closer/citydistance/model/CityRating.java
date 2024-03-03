package com.closer.citydistance.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "city_ratings")
public class CityRating {

    @EmbeddedId
    private CityRatingKey id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("cityId")
    private CityDB city;

    int rating;
}
