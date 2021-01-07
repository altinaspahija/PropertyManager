package net.codejava.dto;

import lombok.Data;
import net.codejava.model.User;

@Data
public class UserDto {
    private Integer userId;
    private String username;
    private String fullname;
    private String password;
    private String phoneNumber;
    private String roleDescription;
    private boolean enabled;

    public static User getUser(UserDto userDto){
        if (userDto==null) return null;
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUsername(userDto.getUsername());
        user.setFullname(userDto.getFullname());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setRoleDescription(userDto.getRoleDescription());
        user.enabled = userDto.enabled;

        return user;
    }

    public static UserDto getUserDto(User user){
        if (user==null) return null;
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setFullname(user.getFullname());
        userDto.setPassword(user.getPassword());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRoleDescription(user.getRoleDescription());
        userDto.enabled = user.enabled;

        return userDto;
    }
}

