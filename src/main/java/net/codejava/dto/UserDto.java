package net.codejava.dto;

import lombok.Data;
import net.codejava.model.User;

@Data
public class UserDto {
    private Integer user_id;
    private String username;
    private String fullname;
    private String password;
    private String phoneNumber;
    private boolean enabled;

    public static User getUser(UserDto userDto){
        if (userDto==null) return null;
        User user = new User();
        user.setUser_id(userDto.getUser_id());
        user.setUsername(userDto.getUsername());
        user.setFullname(userDto.getFullname());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.enabled = userDto.enabled;

        return user;
    }

    public static UserDto getUserDto(User user){
        if (user==null) return null;
        UserDto userDto = new UserDto();
        userDto.setUser_id(user.getUser_id());
        userDto.setUsername(user.getUsername());
        userDto.setFullname(user.getFullname());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.enabled = user.enabled;


        return userDto;
    }
}

