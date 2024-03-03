package com.closer.citydistance.repository;

import com.closer.citydistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Long> {
    void deleteUserByNickname(String nickname);
    User findUserByNickname(String nickname);
}
