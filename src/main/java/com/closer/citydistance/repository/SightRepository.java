package com.closer.citydistance.repository;

import com.closer.citydistance.entity.SightEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightRepository extends JpaRepository<SightEntity, Long> {
}
