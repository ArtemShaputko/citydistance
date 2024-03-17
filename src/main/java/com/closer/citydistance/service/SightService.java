package com.closer.citydistance.service;

import com.closer.citydistance.cache.CacheMap;
import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Sight;
import com.closer.citydistance.dto.SightDTO;
import com.closer.citydistance.repository.CityRepository;
import com.closer.citydistance.repository.SightRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SightService {
    private final SightRepository sightRepository;
    private final CityRepository cityRepository;
    private final CacheMap<Long, Sight> sightCache;

    public List<SightDTO> getAll() {
        return sightRepository.findAll()
                .stream().map(SightDTO::toDTO).toList();
    }

    public Sight add(Long cityId, Sight sight) {
        City city = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException("City not found"));
        sight.setCity(city);
        return sightRepository.save(sight);
    }

    public SightDTO find(Long sightId) {
        Sight sight = sightCache.get(sightId);
        if(sight != null) return  SightDTO.toDTO(sight);
        return SightDTO.toDTO(sightRepository.findById(sightId)
                .orElseThrow(() -> new DataIntegrityViolationException("Sight not found")));
    }

    public void update(Long sightId, Sight sight) {
        Sight oldSight = sightRepository.findById(sightId)
                .orElseThrow(() -> new DataIntegrityViolationException("Sight not found"));
        sight.setId(sightId);
        sight.setCity(oldSight.getCity());
        sightRepository.save(sight);
        sightCache.replace(sightId, sight);
    }

    public void remove(Long sightId) {
        sightCache.remove(sightId);
        sightRepository.deleteById(sightId);
    }
}
