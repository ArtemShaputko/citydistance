package com.closer.citydistance.service;

import com.closer.citydistance.cache.CacheMap;
import com.closer.citydistance.mapper.Mapper;
import com.closer.citydistance.model.City;
import com.closer.citydistance.model.User;
import com.closer.citydistance.dto.CityDTO;
import com.closer.citydistance.dto.UserDTO;
import com.closer.citydistance.repository.CityRepository;
import com.closer.citydistance.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final CacheMap<Long, User> userCache;
    private static final String USER_NOT_FOUND = "User not found";
    private static final String CITY_NOT_FOUND = "City not found";

    public List<UserDTO> getAll(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest).getContent().stream().map(Mapper::userToDTO).toList();
    }

    public User add(User usr) {
        return userRepository.save(usr);
    }

    public void remove(Long userId) {
        userCache.remove(userId);
        userRepository.deleteById(userId);
    }

    public UserDTO findById(Long userId) {
        User user = userCache.get(userId);
        if (user != null) return Mapper.userToDTO(user);
        user = userRepository.findById(userId).orElse(null);
        if(user != null ) userCache.put(userId, user);
        return Mapper.userToDTO(user);
    }

    public List<CityDTO> getLikedCities(Long userId, @NonNull PageRequest pageRequest) {
        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new DataIntegrityViolationException(USER_NOT_FOUND));
        int firstElement = (int) pageRequest.getOffset();
        int lastElement = firstElement + pageRequest.getPageSize();
        if(firstElement >= user.getLikedCities().size()) return List.of();
        if(lastElement >= user.getLikedCities().size()) lastElement = user.getLikedCities().size();
        return user.getLikedCities()
                .subList(firstElement, lastElement)
                .stream().map(Mapper::cityToDTO).collect(Collectors.toList());
    }

    public void update(Long userId, User user) {
        if (!userRepository.existsById(userId)) throw new DataIntegrityViolationException(USER_NOT_FOUND);
        user.setId(userId);
        userRepository.save(user);
        userCache.replace(userId, user);
    }

    public List<UserDTO> getUsersLikedCitiesWithCountry(String countryName, PageRequest pageRequest) {
        return userRepository.findUsersLikedCitiesWithCountry(countryName, pageRequest).getContent()
                .stream().map(Mapper::userToDTO).toList();
    }

    public void setCityLiked(Long userId, Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException(CITY_NOT_FOUND));
        User user = userRepository
                .findById(userId).orElseThrow(() -> new DataIntegrityViolationException(USER_NOT_FOUND));
        if (!user.getLikedCities().contains(city)) {
            user.getLikedCities().add(city);
        }
        userRepository.save(user);
    }

    public void removeLike(Long userId, Long cityId) {
        City city = cityRepository.findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException(CITY_NOT_FOUND));
        User user = userRepository
                .findById(userId).orElseThrow(() -> new DataIntegrityViolationException(USER_NOT_FOUND));
        if (user.getLikedCities().contains(city)) {
            user.getLikedCities().remove(city);
        } else throw new DataIntegrityViolationException("City not liked");
        userRepository.save(user);
    }
}
