package net.codejava.service;





import net.codejava.dto.PropertyDto;
import net.codejava.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface UserService {


    UserDto getUserByUserId(Integer userId);

    UserDto updateUserByUserId(UserDto userDto, Integer userId);

    boolean deleteUserByUserId(Integer propertyId);
    List<UserDto> getUsers();
    UserDto addUserByUserId(Integer userId,
                                    String username,
                                    String fullname,
                                    String password,
                                    String phoneNumber,
                                    boolean enabled);

    UserDto addUser(UserDto userDto);
}
