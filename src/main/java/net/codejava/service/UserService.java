package net.codejava.service;

import net.codejava.dto.UserDto;
import net.codejava.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDto getUserByUserId(Integer userId) throws UserNotFoundException;
    UserDto updateUser(int userId, UserDto userDto) throws UserNotFoundException;
    boolean deleteUserByUserId(Integer userId) throws UserNotFoundException;
    List<UserDto> getUsers() throws UserNotFoundException;
    UserDto addUser(UserDto userDto);
}
