package net.codejava.controller;

import net.codejava.dto.PropertyDto;
import net.codejava.dto.UserDto;
import net.codejava.repository.PropertyRepository;
import net.codejava.repository.UserRepository;
import net.codejava.service.PropertyService;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public UserDto getUsersByUserId(@PathVariable Integer userId) {
       UserDto user = userService.getUserByUserId(userId);
       return user;
    }

    @PostMapping("/adduser")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

}
