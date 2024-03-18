package com.closer.citydistance.cache;

import com.closer.citydistance.model.City;
import com.closer.citydistance.model.Sight;
import com.closer.citydistance.model.User;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@Value
public class Cache {
    CacheMap<Long, User> userCache;
    CacheMap<Long, City> cityCache;
    CacheMap<Long, Sight> sightCache;

    public void removeUser(Long id) {
        User user = userCache.get(id);
        if(user == null) return;
        user.getLikedCities().forEach((City city) -> cityCache.remove(city.getId()));
    }

    public void removeCity(Long id) {
        City city = cityCache.get(id);
        if(city == null) return;
        city.getUserLikes().forEach((User user) -> userCache.remove(user.getId()));
        city.getSights().forEach((Sight sight) -> sightCache.remove(sight.getId()));
    }
    public void removeSight(Long id) {
        Sight sight = sightCache.get(id);
        if(sight == null) return;
        cityCache.remove(sight.getCity().getId());
    }
}
