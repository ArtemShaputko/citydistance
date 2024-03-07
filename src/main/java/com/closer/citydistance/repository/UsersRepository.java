package com.closer.citydistance.repository;

import com.closer.citydistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    void deleteUserEntityByNickname(String nickname);
    UserEntity findUserEntityByNickname(String nickname);
}
