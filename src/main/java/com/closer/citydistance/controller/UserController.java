package com.closer.citydistance.controller;

import com.closer.citydistance.model.User;
import com.closer.citydistance.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    final UserService userService;
    @PostMapping("/add")
    public User add(@RequestBody User usr){
        return userService.add(usr);
    }
    @DeleteMapping("/remove")
    public void remove(@RequestParam String nickname){
        userService.remove(nickname);
    }
    @GetMapping("/find")
    public User findByNickname(@RequestParam String nickname){
        return userService.findByNickname(nickname);
    }
    @PutMapping("/update")
    public User update(@RequestBody User usr){
        return userService.update(usr);
    }
}
