package com.closer.citydistance.service;

import com.closer.citydistance.model.User;
import com.closer.citydistance.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UsersRepository usersRepository;
    public User add(User usr){
        return usersRepository.save(usr);
    }
    public void remove(String nickname){
        usersRepository.deleteUserByNickname(nickname);
    }
    public User findByNickname(String nickname){
        return usersRepository.findUserByNickname(nickname);
    }
    public User update(User usr){
        return usersRepository.save(usr);
    }
}
