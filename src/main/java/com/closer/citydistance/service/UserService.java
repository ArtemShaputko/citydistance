package com.closer.citydistance.service;

import com.closer.citydistance.entity.CityEntity;
import com.closer.citydistance.entity.UserEntity;
import com.closer.citydistance.model.City;
import com.closer.citydistance.model.User;
import com.closer.citydistance.repository.CityRepository;
import com.closer.citydistance.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    private final CityRepository cityRepository;
    public List<User> getAll(){
        return usersRepository.findAll()
                .stream().map(User::toModel).toList();
    }
    @Transactional
    public UserEntity add(UserEntity usr){
        return usersRepository.save(usr);
    }
    @Transactional
    public void remove(Long id){
        usersRepository.deleteById(id);
    }
    public User findById(Long userId){
        return User.toModel(usersRepository.findById(userId).orElse(null));
    }
    @Transactional
    public List<City> getLikedCities(Long userId){
        UserEntity user = usersRepository
                .findById(userId)
                .orElseThrow(() -> new DataIntegrityViolationException("User " + userId + " not found"));
        return user.getLikedCities().stream().map(City::toModel).collect(Collectors.toList());
    }

    @Transactional
    public UserEntity update(Long userId, UserEntity user){
        if(!usersRepository.existsById(userId)) throw new DataIntegrityViolationException("User " + userId + " not found");
        user.setId(userId);
        return usersRepository.save(user);
    }
    public void setLikeCity(Long userId, Long cityId){
        CityEntity city = cityRepository.findById(cityId)
                .orElseThrow(() -> new DataIntegrityViolationException("City " + cityId + " not found"));
        UserEntity user = usersRepository
                .findById(userId).orElseThrow(() -> new DataIntegrityViolationException("User " + userId + " not found"));
        if(!user.getLikedCities().contains(city)) {
            user.getLikedCities().add(city);
        }
        usersRepository.save(user);
    }
}
