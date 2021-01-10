package net.codejava.service;





import net.codejava.dto.PropertyDto;
import net.codejava.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface UserService {


    UserDto getUserByUserId(Integer userId);
    UserDto updateUserByUserId(UserDto userDto, Integer userId);
    boolean deleteUserByUserId(Integer userId);
    List<UserDto> getUsers();
    UserDto addUser(UserDto userDto);

}
