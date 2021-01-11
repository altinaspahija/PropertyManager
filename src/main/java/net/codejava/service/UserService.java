package net.codejava.service;

import net.codejava.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto getUserByUserId(Integer userId);
    UserDto updateUser(UserDto userDto);
    boolean deleteUserByUserId(Integer userId);
    List<UserDto> getUsers();
    UserDto addUser(UserDto userDto);
}
