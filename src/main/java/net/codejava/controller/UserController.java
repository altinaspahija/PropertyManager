package net.codejava.controller;

import net.codejava.dto.UserDto;
import net.codejava.exception.UserNotFoundException;
import net.codejava.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //http://localhost:9090/users GET
    @GetMapping("users")
    public List<UserDto> getUsers() throws UserNotFoundException {
        return userService.getUsers();
    }

    //http://localhost:9090/user/userId GET
    @GetMapping("user{userId}")
    public UserDto getUsersByUserId(@RequestParam Integer userId) throws UserNotFoundException {
        return userService.getUserByUserId(userId);
    }

    //http://localhost:9090/user POST
    @PostMapping("user")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    //http://localhost:9090/user PUT
    @PutMapping("user")
    public UserDto updateUserByUserId (@RequestParam int userId,@RequestBody UserDto userDto) throws UserNotFoundException {
        return userService.updateUser(userId, userDto);
    }

}
