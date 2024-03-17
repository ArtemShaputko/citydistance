package com.closer.citydistance.repository;

import com.closer.citydistance.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional(readOnly = true)
    @Query(nativeQuery = true, value = "select users.* from users " +
            "join city_likes on users.id = city_likes.user_id " +
            "join cities on cities.id = city_likes.city_id " +
            "where cities.country = :countryName")
    Page<User> findUsersLikedCitiesWithCountry(String countryName, Pageable pageable);
}
