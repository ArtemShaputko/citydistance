package com.closer.citydistance.service;

import com.closer.citydistance.entity.CityEntity;
import com.closer.citydistance.entity.SightEntity;
import com.closer.citydistance.model.Sight;
import com.closer.citydistance.repository.CityRepository;
import com.closer.citydistance.repository.SightRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SightService {
    private final SightRepository sightRepository;
    private final CityRepository cityRepository;

    @Transactional
    public SightEntity add(Long cityId, SightEntity sight){
        CityEntity city = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException("City not found"));
        sight.setCity(city);
        return sightRepository.save(sight);
    }

    public Sight find(Long sightId){
        return Sight.toModel(sightRepository.findById(sightId)
                .orElseThrow(() -> new DataIntegrityViolationException("Sight not found")));
    }
    @Transactional
    public void update(Long sightId, SightEntity sight){
        if(!sightRepository.existsById(sightId)) throw new DataIntegrityViolationException("Sight not found");
        sight.setId(sightId);
        sightRepository.save(sight);
    }
    @Transactional
    public void remove(Long sightId){
        sightRepository.deleteById(sightId);
    }
}
