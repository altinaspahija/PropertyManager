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

    @GetMapping("/getUsers")
    public List<UserDto> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getUserByUserId")
    public UserDto getUsersByUserId(@RequestParam Integer userId) {
       UserDto user = userService.getUserByUserId(userId);
       return user;
    }

    @PostMapping("/addUser")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }


    @PutMapping("updateUserByUserId")
    public UserDto updateUserbyUserId (@RequestParam Integer userId, @RequestBody UserDto userDto){
        return userService.updateUserByUserId(userDto,userId);
    }

    @DeleteMapping("deleteUserByUserId")
    public boolean deleteUserByUserId(@RequestParam Integer userId) {
        return userService.deleteUserByUserId(userId);
    }







}
