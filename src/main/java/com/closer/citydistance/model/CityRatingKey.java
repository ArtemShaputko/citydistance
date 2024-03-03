package com.closer.citydistance.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;
@Data
@Embeddable
public class CityRatingKey implements Serializable {
    @Column(name="user_id")
    private Long userId;
    @Column(name="city_id")
    private Long cityId;
}
