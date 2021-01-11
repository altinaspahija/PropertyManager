package net.codejava.controller;

import net.codejava.dto.UserDto;
import net.codejava.exception.UserNotFoundException;
import net.codejava.repository.UserRepository;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("users")
    public List<UserDto> getUsers() throws UserNotFoundException {
        return userService.getUsers();
    }

    @GetMapping("userByUserId")
    public UserDto getUsersByUserId(@RequestParam Integer userId) throws UserNotFoundException {
        return userService.getUserByUserId(userId);
    }

    @PostMapping("user")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }


    @PutMapping("user")
    public UserDto updateUserByUserId (@RequestParam int userId,@RequestBody UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(userId, userDto);
    }

    @DeleteMapping("user")
    public boolean deleteUserByUserId(@RequestParam Integer userId) throws UserNotFoundException {
        return userService.deleteUserByUserId(userId);
    }
}
