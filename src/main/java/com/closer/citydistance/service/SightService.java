package com.closer.citydistance.service;

import com.closer.citydistance.cache.Cache;
import com.closer.citydistance.mapper.Mapper;
import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Sight;
import com.closer.citydistance.dto.SightDTO;
import com.closer.citydistance.repository.CityRepository;
import com.closer.citydistance.repository.SightRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SightService {
    private final SightRepository sightRepository;
    private final CityRepository cityRepository;
    private final Cache cache;

    public List<SightDTO> getAll(PageRequest pageRequest) {
        return sightRepository.findAll(pageRequest).getContent()
                .stream().map(Mapper::sightToDTO).toList();
    }

    public Sight add(Long cityId, Sight sight) {
        City city = cityRepository
                .findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException("City not found"));
        sight.setCity(city);
        return sightRepository.save(sight);
    }

    public SightDTO find(Long sightId) {
        Sight sight = cache.getSightCache().get(sightId);
        if(sight != null) {
            return Mapper.sightToDTO(sight);
        }
        sight = sightRepository.findById(sightId)
                .orElseThrow(() -> new DataIntegrityViolationException("Sight not found"));
        if(sight != null) {
            cache.getSightCache().put(sightId, sight);
        }
        return Mapper.sightToDTO(sight);
    }

    public void update(Long sightId, Sight sight) {
        Sight oldSight = sightRepository.findById(sightId)
                .orElseThrow(() -> new DataIntegrityViolationException("Sight not found"));
        sight.setId(sightId);
        sight.setCity(oldSight.getCity());
        sightRepository.save(sight);
        cache.removeSight(sightId);
    }

    public void remove(Long sightId) {
        cache.removeSight(sightId);
        sightRepository.deleteById(sightId);
    }
}
